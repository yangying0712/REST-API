# 学生管理系统使用指南

## 目录
1. [快速开始](#快速开始)
2. [数据库配置](#数据库配置)
3. [应用启动](#应用启动)
4. [API测试](#api测试)
5. [Postman测试步骤](#postman测试步骤)
6. [常见问题解决](#常见问题解决)

## 快速开始

### 前置条件
- Java 1.8 或更高版本
- Maven 3.6+
- MySQL 8.0+
- Postman（用于API测试）

### 环境检查
```bash
# 检查Java版本
java -version

# 检查Maven版本
mvn -version

# 检查MySQL是否运行
mysql --version
```

## 数据库配置

### 1. 启动MySQL服务

**Windows:**
```bash
# 启动MySQL服务
net start mysql

# 或者通过服务管理器启动
services.msc
```

**Linux/Mac:**
```bash
# 启动MySQL服务
sudo systemctl start mysql
# 或
sudo service mysql start
```

### 2. 执行初始化脚本

方法一：使用命令行
```bash
mysql -u root -p < sql/init.sql
```

方法二：使用MySQL Workbench或其他客户端工具
1. 打开MySQL Workbench
2. 连接到本地MySQL服务器
3. 打开 `sql/init.sql` 文件
4. 执行脚本

### 3. 验证数据库

```sql
-- 连接到数据库
USE student_db;

-- 查看学生表
SELECT * FROM student;

-- 应该看到3条测试数据
```

### 4. 修改数据库配置（如需要）

编辑 `src/main/resources/application.yml`:
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/student_db?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai
    username: root
    password: your_password  # 修改为你的MySQL密码
```

## 应用启动

### 方法一：使用Maven命令

```bash
# 清理并编译
mvn clean compile

# 启动应用
mvn spring-boot:run
```

启动成功后，你会看到类似输出：
```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::               (v2.7.18)

...
Tomcat started on port(s): 8080 (http)
Started StudentManagementApplication in x.xxx seconds
```

### 方法二：使用打包的JAR文件

```bash
# 打包应用
mvn clean package -DskipTests

# 运行JAR文件
java -jar target/student-management-1.0.0.jar
```

### 方法三：使用IntelliJ IDEA

1. 打开IntelliJ IDEA
2. 导入项目（File -> Open -> 选择项目目录）
3. 等待Maven依赖下载完成
4. 找到 `StudentManagementApplication.java`
5. 右键点击该文件，选择 "Run 'StudentManagementApplication'"

## API测试

### 使用浏览器测试GET请求

在浏览器中访问：
```
http://localhost:8080/students
```

应该看到JSON格式的学生列表。

### 使用curl命令测试

#### 1. 获取所有学生
```bash
curl -X GET http://localhost:8080/students
```

#### 2. 根据ID获取学生
```bash
curl -X GET http://localhost:8080/students/1
```

#### 3. 创建新学生
```bash
curl -X POST http://localhost:8080/students \
  -H "Content-Type: application/json" \
  -d '{
    "name": "赵六",
    "gender": "男",
    "age": 20,
    "major": "计算机科学与技术",
    "className": "计科2023-1班",
    "phone": "13800138004",
    "email": "zhaoliu@example.com",
    "enrollmentDate": "2023-09-01"
  }'
```

#### 4. 更新学生信息
```bash
curl -X PUT http://localhost:8080/students/1 \
  -H "Content-Type: application/json" \
  -d '{
    "name": "张三",
    "gender": "男",
    "age": 21,
    "major": "计算机科学与技术",
    "className": "计科2021-1班",
    "phone": "13800138001",
    "email": "zhangsan_new@example.com",
    "enrollmentDate": "2021-09-01"
  }'
```

#### 5. 删除学生
```bash
curl -X DELETE http://localhost:8080/students/1
```

## Postman测试步骤

### 1. 导入Postman集合

1. 打开Postman
2. 点击左上角 "Import" 按钮
3. 选择 "File" 标签
4. 浏览并选择 `postman/Student-Management-API.postman_collection.json`
5. 点击 "Import"

### 2. 测试流程

确保Spring Boot应用正在运行，然后按以下顺序测试：

#### 测试1：获取所有学生（验证初始数据）
1. 在Postman集合中选择 "1. 获取所有学生"
2. 点击 "Send"
3. 应该看到3条初始数据，状态码200

**预期响应：**
```json
{
  "code": 200,
  "message": "查询成功",
  "data": [
    {
      "id": 1,
      "name": "张三",
      "gender": "男",
      "age": 20,
      ...
    },
    ...
  ]
}
```

#### 测试2：根据ID获取学生
1. 选择 "2. 根据ID获取学生"
2. 点击 "Send"
3. 应该看到ID为1的学生信息

**预期响应：**
```json
{
  "code": 200,
  "message": "查询成功",
  "data": {
    "id": 1,
    "name": "张三",
    ...
  }
}
```

#### 测试3：创建新学生
1. 选择 "3. 创建新学生"
2. 检查请求体中的数据
3. 点击 "Send"
4. 应该看到新创建的学生信息，包含自动生成的ID

**预期响应：**
```json
{
  "code": 200,
  "message": "创建成功",
  "data": {
    "id": 4,
    "name": "赵六",
    ...
  }
}
```

#### 测试4：更新学生信息
1. 选择 "4. 更新学生信息"
2. 修改请求体中需要更新的字段（如年龄、邮箱等）
3. 点击 "Send"
4. 应该看到更新后的学生信息

**预期响应：**
```json
{
  "code": 200,
  "message": "更新成功",
  "data": {
    "id": 1,
    "name": "张三",
    "age": 21,
    "email": "zhangsan_new@example.com",
    ...
  }
}
```

#### 测试5：删除学生
1. 选择 "5. 删除学生"
2. 点击 "Send"
3. 应该看到删除成功的消息

**预期响应：**
```json
{
  "code": 200,
  "message": "删除成功",
  "data": null
}
```

#### 验证删除：再次获取所有学生
1. 选择 "1. 获取所有学生"
2. 点击 "Send"
3. 验证之前删除的学生不再存在

### 3. 错误场景测试

#### 测试不存在的学生ID
```
GET http://localhost:8080/students/999
```
**预期响应：**
```json
{
  "code": 404,
  "message": "学生不存在",
  "data": null
}
```

#### 测试更新不存在的学生
```
PUT http://localhost:8080/students/999
```
**预期响应：**
```json
{
  "code": 404,
  "message": "学生不存在",
  "data": null
}
```

#### 测试删除不存在的学生
```
DELETE http://localhost:8080/students/999
```
**预期响应：**
```json
{
  "code": 404,
  "message": "学生不存在",
  "data": null
}
```

## 常见问题解决

### 1. 端口8080被占用

**问题：** `Port 8080 is already in use`

**解决方案：**
- 方法一：修改 `application.yml` 中的端口号
  ```yaml
  server:
    port: 8081
  ```
- 方法二：关闭占用8080端口的程序
  ```bash
  # Windows
  netstat -ano | findstr :8080
  taskkill /PID <PID> /F
  
  # Linux/Mac
  lsof -i :8080
  kill -9 <PID>
  ```

### 2. 数据库连接失败

**问题：** `Communications link failure`

**解决方案：**
1. 检查MySQL服务是否启动
2. 检查数据库名、用户名、密码是否正确
3. 检查MySQL是否监听3306端口
4. 尝试手动连接测试：
   ```bash
   mysql -u root -p -h localhost -P 3306
   ```

### 3. Maven依赖下载失败

**问题：** 依赖下载慢或失败

**解决方案：**
配置Maven使用阿里云镜像，编辑 `~/.m2/settings.xml`：
```xml
<mirrors>
  <mirror>
    <id>aliyun</id>
    <mirrorOf>central</mirrorOf>
    <name>Aliyun Maven</name>
    <url>https://maven.aliyun.com/repository/public</url>
  </mirror>
</mirrors>
```

### 4. Lombok注解不生效

**问题：** IDE提示找不到getter/setter方法

**解决方案（IntelliJ IDEA）：**
1. 安装Lombok插件：File -> Settings -> Plugins -> 搜索"Lombok"
2. 启用注解处理：
   - File -> Settings -> Build, Execution, Deployment -> Compiler -> Annotation Processors
   - 勾选 "Enable annotation processing"
3. 重启IDE

### 5. 中文乱码问题

**问题：** 数据库中文显示乱码

**解决方案：**
确保MySQL配置使用UTF-8：
```sql
-- 查看字符集
SHOW VARIABLES LIKE 'character%';

-- 设置字符集
SET NAMES utf8mb4;
```

在 `application.yml` 中确保URL包含编码参数：
```yaml
url: jdbc:mysql://localhost:3306/student_db?useUnicode=true&characterEncoding=utf-8
```

### 6. 应用启动成功但无法访问

**问题：** 启动成功，但浏览器访问超时

**解决方案：**
1. 检查防火墙设置
2. 确认应用监听正确的IP和端口
3. 尝试使用 `127.0.0.1` 而不是 `localhost`
4. 检查控制台是否有错误日志

## 项目目录结构说明

```
REST-API/
├── src/main/java/               # Java源代码
│   └── com/example/student/
│       ├── StudentManagementApplication.java  # 主启动类
│       ├── common/              # 公共类
│       │   └── Result.java      # 统一返回结果
│       ├── controller/          # 控制器层（处理HTTP请求）
│       │   └── StudentController.java
│       ├── entity/              # 实体类（数据模型）
│       │   └── Student.java
│       ├── mapper/              # MyBatis Mapper接口
│       │   └── StudentMapper.java
│       └── service/             # 业务逻辑层
│           └── StudentService.java
├── src/main/resources/          # 资源文件
│   ├── application.yml          # 应用配置
│   └── mapper/                  # MyBatis XML映射文件
│       └── StudentMapper.xml
├── sql/                         # 数据库脚本
│   └── init.sql
├── postman/                     # Postman测试集合
│   └── Student-Management-API.postman_collection.json
├── pom.xml                      # Maven配置
└── README.md                    # 项目说明
```

## 技术栈版本信息

- Spring Boot: 2.7.18
- MyBatis Spring Boot Starter: 2.2.2
- MySQL Connector: 8.0.33
- Java: 1.8+
- Maven: 3.6+

## 学习资源

- [Spring Boot官方文档](https://spring.io/projects/spring-boot)
- [MyBatis官方文档](https://mybatis.org/mybatis-3/)
- [RESTful API设计指南](https://restfulapi.net/)
- [Postman官方文档](https://learning.postman.com/)

## 技术支持

如遇到问题，请检查：
1. 控制台日志输出
2. 数据库连接状态
3. 端口占用情况
4. 依赖是否完整下载

祝使用愉快！

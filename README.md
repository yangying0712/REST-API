# 学生管理系统 RESTful API

## 项目简介

这是一个基于 Spring Boot + MyBatis + MySQL 的学生管理系统 RESTful API。该项目实现了学生信息的增删改查功能，使用标准的 RESTful 风格进行设计。

## 技术栈

- **Spring Boot 2.7.18** - 应用框架
- **Spring MVC** - Web框架
- **MyBatis 2.2.2** - 持久层框架
- **MySQL 8.0** - 数据库
- **Lombok** - 简化Java代码
- **Maven** - 项目管理工具

## 项目结构

```
REST-API/
├── src/
│   ├── main/
│   │   ├── java/com/example/student/
│   │   │   ├── StudentManagementApplication.java  # 主启动类
│   │   │   ├── common/
│   │   │   │   └── Result.java                    # 统一返回结果类
│   │   │   ├── controller/
│   │   │   │   └── StudentController.java         # REST控制器
│   │   │   ├── entity/
│   │   │   │   └── Student.java                   # 学生实体类
│   │   │   ├── mapper/
│   │   │   │   └── StudentMapper.java             # MyBatis Mapper接口
│   │   │   └── service/
│   │   │       └── StudentService.java            # 业务逻辑层
│   │   └── resources/
│   │       ├── application.yml                     # 应用配置文件
│   │       └── mapper/
│   │           └── StudentMapper.xml               # MyBatis SQL映射文件
│   └── test/
├── sql/
│   └── init.sql                                    # 数据库初始化脚本
├── pom.xml                                         # Maven配置文件
└── README.md                                       # 项目文档
```

## 数据库设计

### 学生表 (student)

| 字段名 | 类型 | 说明 |
|--------|------|------|
| id | BIGINT | 学号（主键，自增） |
| name | VARCHAR(50) | 学生姓名 |
| gender | VARCHAR(10) | 性别 |
| age | INT | 年龄 |
| major | VARCHAR(100) | 专业 |
| class_name | VARCHAR(50) | 班级 |
| phone | VARCHAR(20) | 联系电话 |
| email | VARCHAR(100) | 邮箱 |
| enrollment_date | DATE | 入学日期 |
| create_time | DATETIME | 创建时间 |
| update_time | DATETIME | 更新时间 |

## API 接口文档

### 统一返回格式

所有接口均使用统一的 Result 类返回数据：

```json
{
  "code": 200,
  "message": "操作成功",
  "data": {}
}
```

- `code`: 状态码（200表示成功，404表示未找到，500表示错误）
- `message`: 返回消息
- `data`: 返回数据

### 1. 获取所有学生列表

**请求**
```
GET /students
```

**响应示例**
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
      "major": "计算机科学与技术",
      "className": "计科2021-1班",
      "phone": "13800138001",
      "email": "zhangsan@example.com",
      "enrollmentDate": "2021-09-01",
      "createTime": "2024-01-01 10:00:00",
      "updateTime": "2024-01-01 10:00:00"
    }
  ]
}
```

### 2. 根据学号获取学生

**请求**
```
GET /students/{id}
```

**路径参数**
- `id`: 学号

**响应示例**
```json
{
  "code": 200,
  "message": "查询成功",
  "data": {
    "id": 1,
    "name": "张三",
    "gender": "男",
    "age": 20,
    "major": "计算机科学与技术",
    "className": "计科2021-1班",
    "phone": "13800138001",
    "email": "zhangsan@example.com",
    "enrollmentDate": "2021-09-01",
    "createTime": "2024-01-01 10:00:00",
    "updateTime": "2024-01-01 10:00:00"
  }
}
```

### 3. 创建新学生

**请求**
```
POST /students
Content-Type: application/json
```

**请求体**
```json
{
  "name": "赵六",
  "gender": "男",
  "age": 20,
  "major": "计算机科学与技术",
  "className": "计科2023-1班",
  "phone": "13800138004",
  "email": "zhaoliu@example.com",
  "enrollmentDate": "2023-09-01"
}
```

**响应示例**
```json
{
  "code": 200,
  "message": "创建成功",
  "data": {
    "id": 4,
    "name": "赵六",
    "gender": "男",
    "age": 20,
    "major": "计算机科学与技术",
    "className": "计科2023-1班",
    "phone": "13800138004",
    "email": "zhaoliu@example.com",
    "enrollmentDate": "2023-09-01"
  }
}
```

### 4. 更新学生信息

**请求**
```
PUT /students/{id}
Content-Type: application/json
```

**路径参数**
- `id`: 学号

**请求体**
```json
{
  "name": "张三",
  "gender": "男",
  "age": 21,
  "major": "计算机科学与技术",
  "className": "计科2021-1班",
  "phone": "13800138001",
  "email": "zhangsan_new@example.com",
  "enrollmentDate": "2021-09-01"
}
```

**响应示例**
```json
{
  "code": 200,
  "message": "更新成功",
  "data": {
    "id": 1,
    "name": "张三",
    "gender": "男",
    "age": 21,
    "major": "计算机科学与技术",
    "className": "计科2021-1班",
    "phone": "13800138001",
    "email": "zhangsan_new@example.com",
    "enrollmentDate": "2021-09-01",
    "createTime": "2024-01-01 10:00:00",
    "updateTime": "2024-01-01 11:00:00"
  }
}
```

### 5. 删除学生

**请求**
```
DELETE /students/{id}
```

**路径参数**
- `id`: 学号

**响应示例**
```json
{
  "code": 200,
  "message": "删除成功",
  "data": null
}
```

## 快速开始

### 1. 环境要求

- JDK 1.8 或更高版本
- Maven 3.6+
- MySQL 8.0+
- IntelliJ IDEA（推荐）或其他Java IDE

### 2. 数据库配置

1. 确保 MySQL 服务已启动
2. 执行 `sql/init.sql` 脚本创建数据库和表

```bash
mysql -u root -p < sql/init.sql
```

3. 修改 `src/main/resources/application.yml` 中的数据库连接信息（如需要）

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/student_db?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai
    username: root
    password: root  # 修改为你的MySQL密码
```

### 3. 构建和运行

#### 使用 Maven 命令行

```bash
# 清理并编译
mvn clean compile

# 运行应用
mvn spring-boot:run
```

#### 使用 IntelliJ IDEA

1. 打开 IntelliJ IDEA
2. File -> Open -> 选择项目根目录
3. 等待 Maven 依赖下载完成
4. 找到 `StudentManagementApplication.java`
5. 右键 -> Run 'StudentManagementApplication'

### 4. 验证服务

服务启动成功后，访问：
```
http://localhost:8080/students
```

应该能看到返回的学生列表数据。

## 使用 Postman 测试

### 1. 获取所有学生

- **方法**: GET
- **URL**: `http://localhost:8080/students`

### 2. 根据ID获取学生

- **方法**: GET
- **URL**: `http://localhost:8080/students/1`

### 3. 创建学生

- **方法**: POST
- **URL**: `http://localhost:8080/students`
- **Headers**: `Content-Type: application/json`
- **Body (raw JSON)**:
```json
{
  "name": "赵六",
  "gender": "男",
  "age": 20,
  "major": "计算机科学与技术",
  "className": "计科2023-1班",
  "phone": "13800138004",
  "email": "zhaoliu@example.com",
  "enrollmentDate": "2023-09-01"
}
```

### 4. 更新学生信息

- **方法**: PUT
- **URL**: `http://localhost:8080/students/1`
- **Headers**: `Content-Type: application/json`
- **Body (raw JSON)**:
```json
{
  "name": "张三",
  "gender": "男",
  "age": 21,
  "major": "计算机科学与技术",
  "className": "计科2021-1班",
  "phone": "13800138001",
  "email": "zhangsan_new@example.com",
  "enrollmentDate": "2021-09-01"
}
```

### 5. 删除学生

- **方法**: DELETE
- **URL**: `http://localhost:8080/students/1`

## 项目特点

1. **RESTful 风格设计**: 严格遵循 RESTful API 设计规范
2. **统一返回格式**: 使用自定义 Result 类统一所有接口返回格式
3. **SSM 框架整合**: Spring + Spring MVC + MyBatis 完美整合
4. **Lombok 简化代码**: 使用 Lombok 减少样板代码
5. **MyBatis 配置**: 采用 XML 方式配置 SQL，便于维护和优化
6. **完整的 CRUD 操作**: 实现了完整的增删改查功能
7. **异常处理**: 完善的异常捕获和错误提示

## 常见问题

### 1. 数据库连接失败

- 检查 MySQL 服务是否启动
- 检查数据库用户名和密码是否正确
- 检查数据库是否已创建

### 2. 端口被占用

如果 8080 端口被占用，可以在 `application.yml` 中修改端口：

```yaml
server:
  port: 8081  # 修改为其他端口
```

### 3. Maven 依赖下载失败

- 检查网络连接
- 配置 Maven 镜像源（如阿里云镜像）
- 删除本地 Maven 仓库的错误缓存，重新下载

## 作者

yangying0712

## 许可证

本项目仅用于学习和研究目的。
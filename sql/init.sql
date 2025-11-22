-- 创建数据库
CREATE DATABASE IF NOT EXISTS student_db DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE student_db;

-- 创建学生表
CREATE TABLE IF NOT EXISTS student (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '学号（主键）',
    name VARCHAR(50) NOT NULL COMMENT '学生姓名',
    gender VARCHAR(10) COMMENT '性别',
    age INT COMMENT '年龄',
    major VARCHAR(100) COMMENT '专业',
    class_name VARCHAR(50) COMMENT '班级',
    phone VARCHAR(20) COMMENT '联系电话',
    email VARCHAR(100) COMMENT '邮箱',
    enrollment_date DATE COMMENT '入学日期',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='学生信息表';

-- 插入测试数据
INSERT INTO student (name, gender, age, major, class_name, phone, email, enrollment_date) VALUES
('张三', '男', 20, '计算机科学与技术', '计科2021-1班', '13800138001', 'zhangsan@example.com', '2021-09-01'),
('李四', '女', 19, '软件工程', '软工2022-1班', '13800138002', 'lisi@example.com', '2022-09-01'),
('王五', '男', 21, '网络工程', '网工2020-1班', '13800138003', 'wangwu@example.com', '2020-09-01');

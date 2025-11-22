package com.example.student.entity;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 学生实体类
 */
@Data
public class Student implements Serializable {
    /**
     * 学号（主键）
     */
    private Long id;

    /**
     * 学生姓名
     */
    private String name;

    /**
     * 性别
     */
    private String gender;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 专业
     */
    private String major;

    /**
     * 班级
     */
    private String className;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 入学日期
     */
    private Date enrollmentDate;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}

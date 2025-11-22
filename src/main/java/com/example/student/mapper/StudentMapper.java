package com.example.student.mapper;

import com.example.student.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 学生数据访问层接口
 */
@Mapper
public interface StudentMapper {

    /**
     * 查询所有学生
     */
    List<Student> selectAll();

    /**
     * 根据ID查询学生
     */
    Student selectById(@Param("id") Long id);

    /**
     * 新增学生
     */
    int insert(Student student);

    /**
     * 更新学生信息
     */
    int update(Student student);

    /**
     * 删除学生
     */
    int deleteById(@Param("id") Long id);
}

package com.example.student.service;

import com.example.student.entity.Student;
import com.example.student.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 学生业务逻辑层
 */
@Service
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;

    /**
     * 获取所有学生列表
     */
    public List<Student> getAllStudents() {
        return studentMapper.selectAll();
    }

    /**
     * 根据ID获取学生
     */
    public Student getStudentById(Long id) {
        return studentMapper.selectById(id);
    }

    /**
     * 创建新学生
     */
    public int createStudent(Student student) {
        return studentMapper.insert(student);
    }

    /**
     * 更新学生信息
     */
    public int updateStudent(Student student) {
        return studentMapper.update(student);
    }

    /**
     * 删除学生
     */
    public int deleteStudent(Long id) {
        return studentMapper.deleteById(id);
    }

    /**
     * 检查学生是否存在
     */
    public boolean exists(Long id) {
        return studentMapper.selectById(id) != null;
    }
}

package com.example.student.controller;

import com.example.student.common.Result;
import com.example.student.entity.Student;
import com.example.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 学生REST控制器
 */
@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * 获取所有学生列表
     * GET /students
     */
    @GetMapping
    public Result<List<Student>> getAllStudents() {
        try {
            List<Student> students = studentService.getAllStudents();
            return Result.success("查询成功", students);
        } catch (Exception e) {
            return Result.error("查询失败：" + e.getMessage());
        }
    }

    /**
     * 根据学号获取学生
     * GET /students/{id}
     */
    @GetMapping("/{id}")
    public Result<Student> getStudentById(@PathVariable Long id) {
        try {
            Student student = studentService.getStudentById(id);
            if (student != null) {
                return Result.success("查询成功", student);
            } else {
                return Result.error(404, "学生不存在");
            }
        } catch (Exception e) {
            return Result.error("查询失败：" + e.getMessage());
        }
    }

    /**
     * 创建新学生
     * POST /students
     */
    @PostMapping
    public Result<Student> createStudent(@RequestBody Student student) {
        try {
            int rows = studentService.createStudent(student);
            if (rows > 0) {
                return Result.success("创建成功", student);
            } else {
                return Result.error("创建失败");
            }
        } catch (Exception e) {
            return Result.error("创建失败：" + e.getMessage());
        }
    }

    /**
     * 更新学生信息
     * PUT /students/{id}
     */
    @PutMapping("/{id}")
    public Result<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        try {
            // 检查学生是否存在
            if (!studentService.exists(id)) {
                return Result.error(404, "学生不存在");
            }
            
            student.setId(id);
            int rows = studentService.updateStudent(student);
            if (rows > 0) {
                Student updatedStudent = studentService.getStudentById(id);
                return Result.success("更新成功", updatedStudent);
            } else {
                return Result.error("更新失败");
            }
        } catch (Exception e) {
            return Result.error("更新失败：" + e.getMessage());
        }
    }

    /**
     * 删除学生
     * DELETE /students/{id}
     */
    @DeleteMapping("/{id}")
    public Result<Void> deleteStudent(@PathVariable Long id) {
        try {
            // 检查学生是否存在
            if (!studentService.exists(id)) {
                return Result.error(404, "学生不存在");
            }
            
            int rows = studentService.deleteStudent(id);
            if (rows > 0) {
                return Result.success("删除成功", null);
            } else {
                return Result.error("删除失败");
            }
        } catch (Exception e) {
            return Result.error("删除失败：" + e.getMessage());
        }
    }
}

package com.yajie.springboot.learn.service;

import com.yajie.springboot.learn.entity.Student;
import com.yajie.springboot.learn.entity.StudentCriteria;

import java.util.List;

/**
 * 〈说明〉<br>
 * 〈〉
 *
 * @author mao
 * @Date: 2019/1/24 0024
 * @Description:
 * @since 1.0.0
 */
public interface IStudentService {
    List<Student> selectStudents(StudentCriteria criteria);
}

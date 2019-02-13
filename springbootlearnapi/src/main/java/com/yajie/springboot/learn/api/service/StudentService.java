package com.yajie.springboot.learn.api.service;

import com.yajie.springboot.learn.api.model.StudentDTO;

import java.util.List;

/**
 * 〈说明〉<br>
 * 〈〉
 *
 * @author mao
 * @Date: 2019/1/23 0023
 * @Description:
 * @since 1.0.0
 */
public interface StudentService {
    /**
     * 查询Id
     * @param id
     * @return
     */
    List<StudentDTO> selectStudents(Long id);
}

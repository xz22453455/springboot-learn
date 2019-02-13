/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: DubboStudentServiceImpl
 * Author:   Administrator
 * Date:     2019/1/24 0024 10:55
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yajie.springboot.learn.dubbo.service;

import com.google.common.collect.Lists;
import com.yajie.springboot.learn.api.model.StudentDTO;
import com.yajie.springboot.learn.api.service.StudentService;
import com.yajie.springboot.learn.common.util.BeanCopierUtil;
import com.yajie.springboot.learn.dao.StudentMapper;
import com.yajie.springboot.learn.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * @create 2019/1/24 0024
 */
@Service("dubboStudentService")
public class DubboStudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<StudentDTO> selectStudents(Long id) {
        Student student = studentMapper.selectByPrimaryKey(id);
        StudentDTO studentDTO = new StudentDTO();
        //转换为数据传输对象DTO
        BeanCopierUtil.copy(student, studentDTO);
        return Lists.newArrayList(studentDTO);
    }
}

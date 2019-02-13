/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: StudentServiceImpl
 * Author:   Administrator
 * Date:     2019/1/24 0024 14:16
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yajie.springboot.learn.service.impl;

import com.yajie.springboot.learn.dao.StudentMapper;
import com.yajie.springboot.learn.entity.Student;
import com.yajie.springboot.learn.entity.StudentCriteria;
import com.yajie.springboot.learn.service.IStudentService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2019/1/24 0024
 * @since 1.0.0
 */
@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    StudentMapper studentMapper;
    @Override
    public List<Student> selectStudents(StudentCriteria criteria) {
        criteria.createCriteria().andNameLike("%ya%");
        // RowBounds:在mapper.java中的方法中传入RowBounds对象。offset起始行,limit是当前页显示多少条数据
        return studentMapper.selectByExampleWithRowbounds(criteria,new RowBounds(0,3));
    }
}

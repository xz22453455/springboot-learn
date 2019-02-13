/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: StudentDubboController
 * Author:   Administrator
 * Date:     2019/1/24 0024 10:34
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yajie.springboot.learn.web;

import com.yajie.springboot.learn.api.service.StudentService;
import com.yajie.springboot.learn.entity.Student;
import com.yajie.springboot.learn.entity.StudentCriteria;
import com.yajie.springboot.learn.service.IStudentService;
import com.yajie.springboot.learn.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.RequestWrapper;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Administrator
 * @create 2019/1/24 0024
 * @since 1.0.0
 */
@Controller
public class StudentDubboController {
    @Autowired
    IStudentService studentService;
    @Autowired
    @Qualifier(value = "studentService")
    private StudentService dubboStudentService;

    @RequestMapping("/dubbo")
    @ResponseBody
    public Object testDubbo(@RequestParam("id") Long id) {
        return dubboStudentService.selectStudents(id);
    }

    @ResponseBody
    @RequestMapping("/studentList")
    public List<Student> studentList() {
        StudentCriteria criteria = new StudentCriteria();
        //criteria.createCriteria().
        List<Student> studentList = studentService.selectStudents(criteria);
        return studentList;
    }


}

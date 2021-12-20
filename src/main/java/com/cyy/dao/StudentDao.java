package com.cyy.dao;


import com.cyy.entity.Student;

import java.util.List;

public interface StudentDao {

    //用动态sql时，传入参数要用对象
    //if
    List<Student> selectStudentIf(Student student);

    //where
    List<Student> selectStudentWhere(Student student);

    //foreach用法1
    List<Student> selectForeachOne(List<Integer> idList);

    //foreach用法2
    List<Student> selectForeachTwo(List<Student> stuList);

    //使用PageHelper分页数据
    List<Student> selectAll();
}

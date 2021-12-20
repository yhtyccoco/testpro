package com.cyy;

import com.cyy.dao.StudentDao;
import com.cyy.entity.Student;
import com.cyy.util.MyBatisUtils;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class TestMybatis {

    @Test
    public void testSelectStudentIf(){

        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        Student student = new Student();
        student.setName("李四");
        student.setAge(11);
        List<Student> students = dao.selectStudentIf(student);
        for (Student stu:students){
            System.out.println("if=====" + stu);
        }
    }

    @Test
    public void testSelectStudentWhere(){

        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        Student student = new Student();
        //student.setName("李四");
        //student.setAge(11);

        List<Student> students = dao.selectStudentWhere(student);
        for (Student stu:students){
            System.out.println("if=====" + stu);
        }
    }


    @Test
    public void testfor(){
        List<Integer> list = new ArrayList<>();
        list.add(1001);
        list.add(1002);
        list.add(1003);

        //期望得到的结果：String sql = "select * from student where id in (1001,1002,1003)";
        String sql = "select * from student where id in";
        StringBuilder builder = new StringBuilder();
        int init = 0;
        int len = list.size();

        //添加开始的（
        builder.append("(");
        for (Integer i :list){
            builder.append(i).append(",");
        }
        builder.deleteCharAt(builder.length()-1);
        builder.append(")");
        sql = sql + builder;
        System.out.println("sql == " + sql);
    }

    //foreach用法1
    @Test
    public void testSelectForeachOne(){

        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        List<Integer> list = new ArrayList<>();
        list.add(1001);
        list.add(1002);
        list.add(1003);
        List<Student> students = dao.selectForeachOne(list);
        for (Student stu:students){
            System.out.println("foreach=====" + stu);
        }

    }

    //foreach用法2
    @Test
    public void testSelectForeachTwo(){

        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        List<Student> stuList = new ArrayList<>();
        Student s1 = new Student();
        s1.setId(1002);
        stuList.add(s1);

        s1 = new Student();
        s1.setId(1005);
        stuList.add(s1);

        List<Student> students = dao.selectForeachTwo(stuList);
        for (Student stu:students){
            System.out.println("foreach222222==" + stu);
        }
    }

    @Test
    public void testSelectAll(){

        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        //加入PageHelper的方法，分页功能
        PageHelper.startPage(3,3);

        List<Student> students = dao.selectAll();
        for (Student stu:students){
            System.out.println("=====" + stu);
        }

    }



}

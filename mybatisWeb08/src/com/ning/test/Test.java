package com.ning.test;

import com.ning.mapper.StudentMapper;
import com.ning.mapper.TeacherMapper;
import com.ning.pojo.Student;
import com.ning.pojo.Teacher;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

//使用resultMap实现单表映射

//使用resultMap实现关联单个对象(n+1方式) n+1查询方式 先查询出某个表的全部信息
//根据这个表的信息查询另一个表的信息

//与业务装配的区别 在service里面写的代码，由mybatis完成装配
//在student实现类包含了一个teacher对象  在TeacherMapper中进行查询
//在StudentMapper中进行调用 property属性：对象在类中的属性名 association装配一个对象时使用
//column把当前表的哪个列的值传递给另一个查询作为参数

//大前提使用的是n+1方式时，如果列名和属性名相同时可以不配置，使用Auto Mapping特性
//但是mybatis只会给作为参数传递的列装配一次，所以需要自己在配一次

//在teacher中添加list<student>实体类 在StudentMapper.xml中添加通过tid查询
//在TeacherMapper.xml中添加查询全部 connection标签当属性是集合类型的时候使用的标签

//手机用resultMap实现加载集合数据(联合查询方式)
//mybatis可以通过主键判断对象是否被加载过
//不需要担心创建重复Teacher


//mybatis注解 为了简化配置文件  mybatis的注解简化的是mapper.xml文件
//如果涉及动态sql 依然使用mapper.xml  mapper.xml和注解可以共存
//使用注解时mybatis.xml中<mappers>使用<package/> <mapper class=""/>

public class Test {
    public static void main(String[] args) throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = factory.openSession();
//        List<Teacher> list = session.selectList("com.ning.mapper.TeacherMapper.selAll");
//        System.out.println(list);


//        List<Teacher> list = session.selectList("com.ning.mapper.TeacherMapper.selAll1");
//        for (Teacher teacher: list) {
//            System.out.println(teacher);
//        }

//        TeacherMapper mapper = session.getMapper(TeacherMapper.class);
//        List<Teacher> teachers = mapper.selAll();
//        System.out.println(teachers);

//        TeacherMapper mapper = session.getMapper(TeacherMapper.class);
//        Teacher teacher = new Teacher();
//        teacher.setName("老师3");
//        int index = mapper.insTeacher(teacher);
//        System.out.println(index);

//        TeacherMapper mapper = session.getMapper(TeacherMapper.class);
//        Teacher teacher = new Teacher();
//        teacher.setId(4);
//        teacher.setName("老师4");
//        int index = mapper.updTeacher(teacher);
//        System.out.println(index);

//        TeacherMapper mapper = session.getMapper(TeacherMapper.class);
//        int index = mapper.delById(4);
//        System.out.println(index);

//        StudentMapper mapper = session.getMapper(StudentMapper.class);
//        List<Student> students = mapper.selAll();
//        System.out.println(students);
//        TeacherMapper mapper = session.getMapper(TeacherMapper.class);
//        List<Teacher> teachers = mapper.selTeacher();
//        System.out.println(teachers);

        TeacherMapper mapper = session.getMapper(TeacherMapper.class);
        List<Teacher> teachers = mapper.selAll2();
        System.out.println(teachers);
        session.commit();
        session.close();
        System.out.println("程序执行结束");
    }
}

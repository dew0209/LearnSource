package mapperconfig;

import lombok.SneakyThrows;
import mapperconfig.dto.QueryUserDTO;
import mapperconfig.entity.MybatisTest;
import mapperconfig.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.*;

public class MapperConfigTest {

    @SneakyThrows
    @Test
    public void run01(){
        System.out.println("测试select基本查询");
        String resource = "mapperconfig/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        System.out.println("查询结果：" + mapper.selectById("1"));
    }


    @SneakyThrows
    @Test
    public void run02(){
        System.out.println("测试select的属性parameterType");
        String resource = "mapperconfig/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        QueryUserDTO queryUserDTO = new QueryUserDTO();
        queryUserDTO.setName("张三");
        queryUserDTO.setMail("111");

        System.out.println("不使用parameterType查询结果：" + mapper.selectByDTO(queryUserDTO));

        System.out.println("使用parameterType查询结果：" + mapper.selectByDTOUseParamType(queryUserDTO));
    }

    @SneakyThrows
    @Test
    public void run03(){
        System.out.println("测试select：传递多个查询入参");
        String resource = "mapperconfig/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        QueryUserDTO queryUserDTO = new QueryUserDTO();
        queryUserDTO.setName("张三");
        queryUserDTO.setMail("111");
        System.out.println("使用实体类查询结果：" + mapper.selectByDTO(queryUserDTO));

        Map<String, String> map = new HashMap<>();
        map.put("name","张三");
        map.put("mail","111");
        System.out.println("使用map查询结果：" + mapper.selectByDTOUseMap(map));
        System.out.println("使用多个参数直接传参查询结果：" + mapper.selectByDTOBase("张三","111",queryUserDTO));
    }

    @SneakyThrows
    @Test
    public void run04(){
        System.out.println("测试resultMap的子元素constructor");
        String resource = "mapperconfig/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        System.out.println("结果：" + mapper.selectByIdUserResultMapArg("1"));
    }

    @SneakyThrows
    @Test
    public void run05(){
        System.out.println("测试insert（不回填主键）");
        String resource = "mapperconfig/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        MybatisTest testInfo = new MybatisTest();
        testInfo.setDescM("测试数据");
        System.out.println("结果：" + mapper.inseret0(testInfo));
        sqlSession.commit();
        System.out.println("id结果：" + testInfo.getId());
    }


    @SneakyThrows
    @Test
    public void run06(){
        System.out.println("测试insert（回填主键）");
        String resource = "mapperconfig/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        MybatisTest testInfo = new MybatisTest();
        testInfo.setDescM("测试数据");
        System.out.println("结果：" + mapper.inseret1(testInfo));
        sqlSession.commit();
        System.out.println("id结果：" + testInfo.getId());
    }

    @SneakyThrows
    @Test
    public void run07(){
        System.out.println("测试insert（selectKey）");
        String resource = "mapperconfig/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        MybatisTest testInfo = new MybatisTest();
        testInfo.setDescM("测试数据");
        System.out.println("结果：" + mapper.inseret3(testInfo));
        sqlSession.commit();
        System.out.println("id结果：" + testInfo.getId());
    }

    @SneakyThrows
    @Test
    public void run08(){
        System.out.println("测试insert（回填主键多个）");
        String resource = "mapperconfig/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        MybatisTest testInfo = new MybatisTest();
        //testInfo.setDescM("测试数据");
        System.out.println("结果：" + mapper.inseret4(testInfo));
        sqlSession.commit();
        System.out.println("id结果：" + testInfo.getId());
    }


    @SneakyThrows
    @Test
    public void run09(){
        System.out.println("测试&和#");
        String resource = "mapperconfig/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        String column = "id, name, nikename, psw";
        String table = "user";

        System.out.println("正确结果：" + mapper.selectDy(column,table,"1"));
        System.out.println("错误结果：" + mapper.selectDyError(column,table,"1 or 1 = 1"));
    }

    @SneakyThrows
    @Test
    public void run10(){
        System.out.println("测试if,where");
        String resource = "mapperconfig/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //System.out.println("正确结果：" + mapper.selectByCondition("","109"));
        //System.out.println("正确结果：" + mapper.selectByCondition2("",""));
        System.out.println("正确结果：" + mapper.selectByCondition3("",""));
    }

    @SneakyThrows
    @Test
    public void run11(){
        System.out.println("测试if,set");
        String resource = "mapperconfig/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//        System.out.println("结果：" + mapper.updateNameAndEmail("21089","","1"));
//        System.out.println("结果：" + mapper.updateNameAndEmail1("21089","","1"));
        System.out.println("结果：" + mapper.updateNameAndEmail2("210222289","","1"));
        sqlSession.commit();
    }


    @SneakyThrows
    @Test
    public void run12(){
        System.out.println("测试foreach");
        String resource = "mapperconfig/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//        System.out.println("结果：" + mapper.updateNameAndEmail("21089","","1"));
//        System.out.println("结果：" + mapper.updateNameAndEmail1("21089","","1"));

        List<MybatisTest> infoList = new ArrayList<>();

        MybatisTest test1 = new MybatisTest();
        test1.setId(2000);
        test1.setDescM("你好呀");

        infoList.add(test1);
        MybatisTest test2 = new MybatisTest();
        test2.setId(2001);
        test2.setDescM("你好呀");
        infoList.add(test2);
        System.out.println("结果：" + mapper.insertForeach(infoList));
        sqlSession.commit();
    }


    @SneakyThrows
    @Test
    public void run13(){
        System.out.println("测试foreach");
        String resource = "mapperconfig/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH,true);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//        System.out.println("结果：" + mapper.updateNameAndEmail("21089","","1"));
//        System.out.println("结果：" + mapper.updateNameAndEmail1("21089","","1"));

        List<MybatisTest> infoList = new ArrayList<>();

        MybatisTest test1 = new MybatisTest();
        test1.setId(2003);
        test1.setDescM("你好呀111");

        infoList.add(test1);
        MybatisTest test2 = new MybatisTest();
        test2.setId(2004);
        test2.setDescM("你好呀222");
        infoList.add(test2);
        System.out.println("结果：" + mapper.insertForeach(infoList));



        List<MybatisTest> infoList2 = new ArrayList<>();

        MybatisTest test3 = new MybatisTest();
        test3.setId(2005);
        test3.setDescM("你好呀3333");

        infoList2.add(test3);
        MybatisTest test4 = new MybatisTest();
        test4.setId(2006);
        test4.setDescM("你好呀4444");
        infoList2.add(test4);
        System.out.println("结果：" + mapper.insertForeach(infoList2));

        //ExecutorType.BATCH需要手动提交commit
        sqlSession.commit();
    }



    @SneakyThrows
    @Test
    public void run14(){
        System.out.println("测试choose when otherwise");
        String resource = "mapperconfig/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //System.out.println("正确结果：" + mapper.selectByCondition("","109"));
        //System.out.println("正确结果：" + mapper.selectByCondition2("",""));
        System.out.println("正确结果：" + mapper.selectByCondition4("ly"));
    }



}

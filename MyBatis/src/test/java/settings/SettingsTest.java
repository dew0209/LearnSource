package settings;

import lombok.SneakyThrows;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import settings.entity.User;
import settings.entity.UserTypeHandleEnum;
import settings.enumpojo.Source;
import settings.mapper.UserMapper;
import settings.mapper.UserMapperTypeHandle;
import settings.mapper.UserMapperTypeHandleEnum;

import java.io.InputStream;
import java.util.Properties;

public class SettingsTest {

    @SneakyThrows
    @Test
    public void run01(){
        System.out.println("测试properties属性");
        String resource = "settings/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        System.out.println("查询结果：" + mapper.selectById("1"));
    }

    @SneakyThrows
    @Test
    public void run02(){
        System.out.println("测试properties对配置加载顺序的影响");
        String resource = "settings/mybatis-config2.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);

        Properties properties = new Properties();
        //参数里面的配置，优先级最高
        properties.setProperty("jdbc_url","jdbc:mysql://198.0.0.111:3306/abtest");

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream,properties);
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        System.out.println("查询结果：" + mapper.selectById("1"));

    }

    @SneakyThrows
    @Test
    public void run03(){
        System.out.println("测试typeAliases");
        String resource = "settings/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        System.out.println("查询结果：" + mapper.selectById("1"));
    }

    @SneakyThrows
    @Test
    public void run04(){
        System.out.println("测试typeHandle处理ResultSet");
        String resource = "settings/mybatis-config3.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapperTypeHandle mapper = sqlSession.getMapper(UserMapperTypeHandle.class);
        System.out.println("查询结果：" + mapper.selectById2("1"));
    }


    @SneakyThrows
    @Test
    public void run05(){
        System.out.println("测试typeHandle处理Enum类型,存的是枚举类的名字");
        String resource = "settings/mybatis-config4.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapperTypeHandleEnum userMapperTypeHandleEnum = sqlSession.getMapper(UserMapperTypeHandleEnum.class);

//        UserTypeHandleEnum userTypeHandleEnum = new UserTypeHandleEnum();
//
//        userTypeHandleEnum.setId("999");
//        userTypeHandleEnum.setName("张三");
//        userTypeHandleEnum.setNikename("三");
//        userTypeHandleEnum.setPsw("123456");
//        userTypeHandleEnum.setStatus("4");
//        userTypeHandleEnum.setMail("1111@qq.com");
//        userTypeHandleEnum.setType("4");
//        userTypeHandleEnum.setSalt("43248973");
//        userTypeHandleEnum.setSource(Source.HADN);
//        int res = userMapperTypeHandleEnum.insert1(userTypeHandleEnum);
//        System.out.println("插入结果：" + res);
//        //执行一次就行了，发现数据库存的是HAND
//        sqlSession.commit();
//        UserTypeHandleEnum handleEnumById = userMapperTypeHandleEnum.getById("999");
//        System.out.println(handleEnumById);
//        System.out.println(handleEnumById.getSource().getDesc());

        //如果引入了EnumOrdinalTypeHandler的类型处理器，还想存名字的话，需要手动指定处理


        UserTypeHandleEnum userTypeHandleEnum = new UserTypeHandleEnum();

        userTypeHandleEnum.setId("999");
        userTypeHandleEnum.setName("张三");
        userTypeHandleEnum.setNikename("三");
        userTypeHandleEnum.setPsw("123456");
        userTypeHandleEnum.setStatus("4");
        userTypeHandleEnum.setMail("1111@qq.com");
        userTypeHandleEnum.setType("4");
        userTypeHandleEnum.setSalt("43248973");
        userTypeHandleEnum.setSource(Source.HADN);
        //int res = userMapperTypeHandleEnum.insert2(userTypeHandleEnum);
        //System.out.println("插入结果：" + res);
        //执行一次就行了，发现数据库存的是HAND
        //sqlSession.commit();
        UserTypeHandleEnum handleEnumById = userMapperTypeHandleEnum.getById2("999");
        System.out.println(handleEnumById);
        System.out.println(handleEnumById.getSource().getDesc());



    }

    @SneakyThrows
    @Test
    public void run06(){
        System.out.println("测试typeHandle处理Enum类型,存的是枚举类的序号");
        String resource = "settings/mybatis-config4.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapperTypeHandleEnum userMapperTypeHandleEnum = sqlSession.getMapper(UserMapperTypeHandleEnum.class);

        UserTypeHandleEnum userTypeHandleEnum = new UserTypeHandleEnum();

//        userTypeHandleEnum.setId("88");
//        userTypeHandleEnum.setName("张三");
//        userTypeHandleEnum.setNikename("三");
//        userTypeHandleEnum.setPsw("123456");
//        userTypeHandleEnum.setStatus("4");
//        userTypeHandleEnum.setMail("1111@qq.com");
//        userTypeHandleEnum.setType("4");
//        userTypeHandleEnum.setSalt("43248973");
//        userTypeHandleEnum.setSource(Source.HADN);
//        int res = userMapperTypeHandleEnum.insert1(userTypeHandleEnum);
//        System.out.println("插入结果：" + res);
//        //执行一次就行了，发现数据库存的是HAND
//        sqlSession.commit();
        UserTypeHandleEnum handleEnumById = userMapperTypeHandleEnum.getById("88");
        System.out.println(handleEnumById);
        System.out.println(handleEnumById.getSource());

    }


    @SneakyThrows
    @Test
    public void run07(){
        System.out.println("测试objectFactory");
        String resource = "settings/mybatis-config5.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.selectById("1");
        System.out.println("结果：" + user);

    }


    @SneakyThrows
    @Test
    public void run08(){
        System.out.println("测试mapper");
        String resource = "settings/mybatis-config6.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.selectById("1");
        System.out.println("结果：" + user);
    }

}

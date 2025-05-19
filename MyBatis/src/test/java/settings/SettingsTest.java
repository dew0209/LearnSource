package settings;

import lombok.SneakyThrows;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import settings.mapper.UserMapper;
import settings.mapper.UserMapperTypeHandle;

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

}

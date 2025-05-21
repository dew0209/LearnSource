package mapperconfig;

import lombok.SneakyThrows;
import mapperconfig.dto.QueryUserDTO;
import mapperconfig.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

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

}

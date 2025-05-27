package association;

import association.entity.User;
import association.mapper.UserMapper;
import lombok.SneakyThrows;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class AssociationTest {

    @SneakyThrows
    @Test
    public void run01(){
        System.out.println("测试一对一association嵌套结果");
        String resource = "association/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> list = mapper.selectAllOper1();
        for(int i = 0;i < list.size();i++){
            System.out.println(list.get(i));
        }

    }

    @SneakyThrows
    @Test
    public void run02(){
        System.out.println("测试一对一association嵌套查询");
        String resource = "association/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> list = mapper.selectAllOper2();
        for(int i = 0;i < list.size();i++){
            System.out.println(list.get(i).getId());
        }

    }

}

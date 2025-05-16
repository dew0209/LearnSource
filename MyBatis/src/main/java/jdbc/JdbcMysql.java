package jdbc;

import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.Properties;

@Slf4j
public class JdbcMysql {

    //jdbc:mysql://ip:端口/库名
    //jdbc:oracle:thin:@//192.168.202.154/zhfptest

    public static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/abtest";
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "020922";

    public static void main(String[] args)  {
        Properties properties = new Properties();
        properties.setProperty("user",DB_USER);
        properties.setProperty("password",DB_PASSWORD);


        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet resultSet = null;
        try {
            //1.加载数据库驱动
            Class.forName("com.mysql.jdbc.Driver");

            //2.建立数据库连接
            connection = DriverManager.getConnection(DB_URL,properties);
            //3.创建Statement或PreparedStatement
            prepareStatement = connection.prepareStatement("select * from user where id = ?");
            prepareStatement.setString(1,"1");

            //4.执行SQL语句
            resultSet = prepareStatement.executeQuery();

            //5.处理结果集
            while (resultSet.next()){
                String phone = resultSet.getString("phone");
                String mail = resultSet.getString("mail");
                System.out.println(mail);
                System.out.println(phone);
            }
        }catch (ClassNotFoundException e) {
            log.error("注册驱动失败：", e);
        } catch (SQLException ex) {
            log.error("获取数据库链接异常：",ex);
        }finally {

            //6.关闭连接和释放资源
            if(resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException throwables) {
                    log.error("关闭资源异常：",throwables);
                }
            }

            if(prepareStatement != null){
                try {
                    prepareStatement.close();
                } catch (SQLException throwables) {
                    log.error("关闭资源异常：",throwables);
                }
            }

            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    log.error("关闭资源异常：",throwables);
                }
            }
        }


    }
}

package jdbc;

import lombok.extern.slf4j.Slf4j;


import java.sql.*;
import java.util.Properties;

@Slf4j
public class JdbcOracle {

    //jdbc:oracle:thin:@ip:端口/数据库

    public static final String DB_URL = "jdbc:oracle:thin:@192.168.202.154:1521/zhfptestzh";
    public static final String DB_USER = "qrhy_linux";
    public static final String DB_PASSWORD = "abc123";


    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.setProperty("user",DB_USER);
        properties.setProperty("password",DB_PASSWORD);

        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet resultSet = null;

        try {
            //1.加载数据库驱动
            Class.forName("oracle.jdbc.driver.OracleDriver");
            //2.建立数据库连接
            connection = DriverManager.getConnection(DB_URL, properties);
            //3.创建Statement或PreparedStatement
            prepareStatement = connection.prepareStatement("select * from BT_GOODS where id = ?");
            prepareStatement.setString(1,"304663");
            //4.执行SQL语句
            resultSet = prepareStatement.executeQuery();

            //5.处理结果集（如果有需要）
            while (resultSet.next()){
                String standards = resultSet.getString("STANDARDS");
                System.out.println(standards);
            }
        } catch (ClassNotFoundException e) {
            log.error("驱动注册失败：",e);
        } catch (SQLException e) {
            log.error("获得链接失败：",e);
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

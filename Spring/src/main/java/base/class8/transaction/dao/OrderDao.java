package base.class8.transaction.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 *
 *
 * @author LvLu
 * @className OrderDao
 * @date 2024-02-20 11:39
 * @description
 */
@Repository
public class OrderDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;


	@Transactional
	public void insert(){
		String sql = "insert into `order`(id,name) values(?,?)";
		jdbcTemplate.update(sql,"3","3");
		System.out.println("操作完成");
		//配置类需要增加 @EnableTransactionManagement //开启事务管理功能
		int i =  1/ 0;
	}


}

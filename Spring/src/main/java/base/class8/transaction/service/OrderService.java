package base.class8.transaction.service;

import base.class8.transaction.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 *
 * @author LvLu
 * @className OrderService
 * @date 2024-02-20 11:39
 * @description
 */
@Service
public class OrderService {

	@Autowired
	private OrderDao orderDao;

	@Transactional
	public void insert(){
		orderDao.insert();
	}

}

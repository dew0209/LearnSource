package base.class8.transaction.service;

import base.class8.transaction.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright (c) by [安徽航天信息]
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

	public void insert(){
		orderDao.insert();
	}

}

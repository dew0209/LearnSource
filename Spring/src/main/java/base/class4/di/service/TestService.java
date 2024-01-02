package base.class4.di.service;

import base.class4.di.dao.TestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.annotation.Resources;
import javax.inject.Inject;

/**
 * Copyright (c) by [安徽航天信息]
 *
 * @author LvLu
 * @className TestService
 * @date 2024-01-02 16:39
 * @description
 */
@Service
public class TestService {

//	@Qualifier("testDao2")
	//@Autowired
	//和@Autowired一样可以状态，一个是spring的，一个是jdk的 1.不支持primary功能 2.不支持@Autowired(require=false)功能
	//@Resource(name =  "testDao")
	//需要额外引用jar包 和Autowired功能差不多，还支持Primary  没有Autowired的require=false功能
	@Inject
	private TestDao testDao;

	public void println(){
		System.out.println(testDao);
	}
}

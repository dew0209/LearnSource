package base.class5.aop.service;

import org.springframework.stereotype.Service;

/**
 *
 *
 * @author LvLu
 * @className CalcService
 * @date 2024-01-08 19:41
 * @description
 */
@Service
public class CalcService {

	public int div(int i,int j){

		return i / j;
	}

}

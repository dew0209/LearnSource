import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Copyright (c) by [安徽航天信息]
 *
 * @author LvLu
 * @className Main
 * @date 2023-02-27 14:20
 * @description
 */
public class Main {

	/**
	 * @Title: getRandomLen
	 * @Description: 初始化自定义长度
	 * @Author: LvLu
	 * @Date: 2023-12-01 14:17
	 * @param len 需要指定的长度
	 * @param containChar 是否包含字母，true包含 false 不包含
	 * @param containNumber 是否包含数字，true 包含 false 不包含
	 * @param includeUpper 是否大写英文字母，true 包含 false 不包含
	 * @return: java.lang.String
	 * @throws:
	 **/
	public static String getRandomGeneration(int len,boolean containChar,boolean containNumber,boolean includeUpper){
		if(!containChar && !containNumber && !includeUpper)return "";
		//初始化数组;
		List charArr = new ArrayList();
		if(containChar){
			for(int i = 0;i < 26;i++){
				charArr.add((char)('a' + i));
				if(includeUpper){
					charArr.add((char)('A' + i));
				}
			}
		}
		if(containNumber){
			for(char i = '0';i <= '9';i++){
				charArr.add(i);
			}
		}
		StringBuilder res = new StringBuilder();
		int m = charArr.size();
		Random random = new Random();
		for(int i = 0;i < len;i++){
			int index = random.nextInt(m);
			res.append(charArr.get(index));
		}
		return res.toString();
	}

	public static void main(String[] args) {
		String randomGeneration = getRandomGeneration(32,true, false,false);
		System.out.println(randomGeneration);
	}



}

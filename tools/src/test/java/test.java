/**
 * Copyright (c) by [安徽航天信息]
 *
 * @author LvLu
 * @className test
 * @date 2022-08-22 21:15
 * @description
 */
public class test {
	public static void main(String[] args) {
		String s = "色母粒 REMAFIN(TM) PL00446088~~千克~~C2-GFE0085~~500.000~~23455.75~~0.13~~1507~~0.00~~3049.25~~ ~~ ~~~~1";
		String[] split = s.split("~~");
		System.out.println("长度：" + split.length);
		for(int i = 0;i < split.length;i++){
			System.out.println("第 " + (i + 1) + "列，数据为：" + split[i]);
		}
	}
}

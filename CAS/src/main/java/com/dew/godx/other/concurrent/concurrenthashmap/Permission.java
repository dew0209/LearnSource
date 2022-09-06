package com.dew.godx.other.concurrent.concurrenthashmap;

/**
 * 位运算进行权限的控制
 */
public class Permission {

	//用boolean去控制状态，新增一个权限就十分的麻烦,推荐使用位运算
	boolean select = false;
	boolean update = false;

	// 是否允许查询 二进制第1位 0 表示否 1 表示是
	public static final int ALLOW_SELECT = 1 << 0;//001

	// 是否允许新增，二进制第2位 0 表示否 1 表示是
	public static final int ALLOW_INSERT = 1 << 1;//010

	// 是否允许修改，二进制第3位，0 表示否 1 表示是
	public static final int ALLOW_UPDATE = 1 << 2;//100

	// 是否允许删除，二进制第4位，0 表示否 1 表示是
	public static final int ALLOW_DELETE = 1 << 3;//1000

	//目前的状态
	private int flag;

	public void setPer(int per){
		flag = per;
	}

	//增加用户的权限
	public void enable(int per){
		flag = flag | per;
	}

	//删除用户的权限
	public void disable(int per){
		flag = flag & ~per;
	}

	//判定用户的权限
	public boolean isAllow(int per){
		return (flag & per) == per;
	}

	//判定用户没有的权限
	public boolean isNotAllow(int per){
		return (flag & per) == 0;
	}

	public static void main(String[] args){
		int flag = 15;
		Permission permission = new Permission();
		permission.setPer(flag);
		permission.disable(ALLOW_DELETE);
		permission.disable(ALLOW_INSERT);
		System.out.println(permission.isAllow(ALLOW_SELECT));//true
		System.out.println(permission.isAllow(ALLOW_UPDATE));//true
		System.out.println(permission.isAllow(ALLOW_INSERT));//false
		System.out.println(permission.isAllow(ALLOW_DELETE));//false
	}

}

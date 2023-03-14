package com.dew.godl.increase.cas.cas.acreference;

import java.util.concurrent.atomic.AtomicReference;

/**
 *
 */
public class Base {
	public static void main(String[] args) {
		AtomicReference<User> user = new AtomicReference<>();
		User z3 = new User("张三",18);
		User l4 = new User("李四",20);
		user.set(z3);
		System.out.println(user.compareAndSet(z3,l4) + "\t" + user.get().toString());
		System.out.println(user.compareAndSet(z3,l4) + "\t" + user.get().toString());
	}
}


class User{
	String userName;
	int age;

	public User(String userName, int age) {
		this.userName = userName;
		this.age = age;
	}

	/**
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age
	 */
	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("User{");
		sb.append("userName='").append(userName).append('\'');
		sb.append(", age=").append(age);
		sb.append('}');
		return sb.toString();
	}
}
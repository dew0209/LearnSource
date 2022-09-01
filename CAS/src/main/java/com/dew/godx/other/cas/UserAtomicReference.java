package com.dew.godx.other.cas;

import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 *
 */
public class UserAtomicReference {
	static AtomicReference<UserInfo> userRef = new AtomicReference<>();
	public static void main(String[] args) {
		UserInfo user = new UserInfo("Mark", 15);
		userRef.set(user);
		UserInfo updateUser = new UserInfo("Bill", 17);
		userRef.compareAndSet(user,updateUser);
		System.out.println(userRef.get().getName());
		System.out.println(userRef.get().getAge());
		System.out.println(user.getName());
		System.out.println(user.getAge());
	}
	static class UserInfo {
		private String name;
		private int age;

		public UserInfo(String name, int age) {
			this.name = name;
			this.age = age;
		}

		/**
		 * @return name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @return age
		 */
		public int getAge() {
			return age;
		}
	}
}

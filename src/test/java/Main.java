import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Copyright (c) by [安徽航天信息]
 *
 * @author LvLu
 * @className Main
 * @date 2023-03-13 15:20
 * @description
 */
public class Main {
	public static void main(String[] args) throws Exception {
		FileOutputStream fs = new FileOutputStream("D:\\ds\\能用的票\\test001.txt");
		fs.write(97);
		fs.write(98);
		fs.write(99);
		fs.close();
	}
}

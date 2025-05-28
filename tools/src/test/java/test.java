import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 *
 * @author LvLu
 * @className test
 * @date 2022-08-22 21:15
 * @description
 */
public class test {
	public static void main(String[] args) throws IOException {
//		String ans = "开具红字增值税专用发票信息表编号 340566730822431033";
//		String res = ans.replaceAll("开具红字增值税专用发票信息表编号([\\s]+)", "开具红字增值税专用发票信息表编号");
//		System.out.println(String.valueOf(res));
//		String r1 = null;
//		System.out.println(r1+"");
//		ServerSocket serverSocket = new ServerSocket(80);
//		Socket con = serverSocket.accept();
//		InputStream in = con.getInputStream();
//		byte[] data = new byte[1024];
//		in.read(data);
//		System.out.println(new String(data,"UTF-8"));
		ArrayList<Object> objects = new ArrayList<>();
		objects.add(0,1);
		objects.add(0,2);
		System.out.println(objects.size());
		String res = "0000";
		byte[] bytes = res.getBytes();
	}
}

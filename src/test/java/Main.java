import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.stream.Collectors;

/**
 *
 *
 * @author LvLu
 * @className Main
 * @date 2023-03-13 15:20
 * @description
 */
public class Main {
	public static void main(String[] args) throws Exception {
		ScheduledExecutorService pool = Executors.newScheduledThreadPool(3);
		ArrayList<Object> objects = new ArrayList<>();
		objects.add("111");
		objects.add("2");
		objects.add("3");

		CompletableFuture<Void> voidCompletableFuture = CompletableFuture.allOf(objects.stream().map(e -> {
			return CompletableFuture.supplyAsync(() -> {
				System.out.println(e);
				try {
					System.out.println("sleep====");
					Thread.currentThread().sleep(10000);
					System.out.println("唤醒====");
					//int i = 1 / 0;
				} catch (InterruptedException interruptedException) {
					interruptedException.printStackTrace();
				}
				return e;
			}).whenComplete((a,b)->{
				System.out.println(a);
				System.out.println(b);
			}).exceptionally(r->{
				System.out.println("异常");
				return null;
			});
		}).toArray(CompletableFuture[]::new));
		voidCompletableFuture.join();
		System.out.println("===== end ======" + voidCompletableFuture);

	}
}

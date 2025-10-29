package class6.other;

public class Test {
    public static void main(String[] args) {

        long st = System.currentTimeMillis();

        //使用long sum = 0L; 和 Long sum = 0L;差别很大，不要创建不必要的对象
        long sum = 0L;

        for (long i = 1;i <= Integer.MAX_VALUE;i++){

            sum = sum + i;

        }
        System.out.println("耗时:" + (System.currentTimeMillis() - st) + " ms");
    }
}

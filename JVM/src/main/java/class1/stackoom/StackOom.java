package class1.stackoom;

public class StackOom {

    private static int count = 1;

    //-Xss256k

    //Exception in thread "main" java.lang.StackOverflowError
    public static void main(String[] args) {

        try {

            dfs();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(count);

        }

    }
    private static void dfs(){
        count++;
        dfs();
    }
}

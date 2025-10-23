package class1.stackalloc;

public class StackAlloc {

    public static class User{
        private int id = 0;
        public String name = "";
    }

    public static void alloc(){
        User u = new User();

        u.id = 5;
        u.name = "mark";
    }

    //-server -Xmx10m -Xms10m -XX:+DoEscapeAnalysis -XX:+PrintGC -XX:+EliminateAllocations -XX:-UseTLAB
    public static void main(String[] args) {
        long b = System.currentTimeMillis();
        for(int i = 0;i < 1000000000;i++){
            alloc();
        }
        System.out.println(System.currentTimeMillis() - b);
    }


}

package class3;

import java.util.Map;

public class JinfoTest {

    //-Xmx20m -Xms20m -Xmn2m -XX:+PrintGC
    //jinfo -flag +PrintGCDetails 18884
    public static void main(String[] args) {
        while (true){
            byte[] b = null;
            for(int i = 0;i < 10;i++){

                b = new byte[1 * 1024 * 1024];
                try {
                    Thread.sleep(500);
                }catch (Exception e){
                    e.printStackTrace();
                }

                Map<Thread, StackTraceElement[]> allStackTraces =
                        Thread.getAllStackTraces();
                for (Map.Entry<Thread, StackTraceElement[]> threadEntry : allStackTraces.entrySet()) {
                    Thread key = threadEntry.getKey();
                    StackTraceElement[] value = threadEntry.getValue();
                    System.out.println(key.getName() + " --- " + key.getId());
                    for (StackTraceElement stackTraceElement : value) {
                        System.out.println(stackTraceElement + " --- ");
                    }
                    System.out.println();
                }

            }




        }
    }

}

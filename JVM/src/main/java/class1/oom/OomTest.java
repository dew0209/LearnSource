package class1.oom;

import java.util.ArrayList;
import java.util.List;

public class OomTest {

    //-Xmx5m -Xms5m -XX:+PrintGC
    //堆溢出
    //java.lang.OutOfMemoryError: GC overhead limit exceeded
    //java.lang.OutOfMemoryError: Java heap space
    public static void main(String[] args) {
        //java.lang.OutOfMemoryError: GC overhead limit exceeded GC次数过多，一般证明有循环在不停创建对象，把内存撑爆了
//        List<Object> list = new ArrayList<>();
//
//        int i = 0;
//        while (true){
//            i++;
//            if(i % 10000 == 0){
//                System.out.println("i=" + i);
//            }
//            list.add(new Object());
//        }

        //java.lang.OutOfMemoryError: Java heap space  在分配的时候，有巨型对象，超出了堆的大小
        String[] strings = new String[100000000];

    }

}

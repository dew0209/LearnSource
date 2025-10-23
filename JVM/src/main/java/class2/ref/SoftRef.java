package class2.ref;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

public class SoftRef {

    /**
     *
     * User{name='mark', id=22}
     * [GC (System.gc())  1682K->760K(9728K), 0.0007383 secs]
     * [Full GC (System.gc())  760K->625K(9728K), 0.0046964 secs]
     * After Gc
     * User{name='mark', id=22}
     * *********************User{name='mark', id=22}
     * *********************User{name='mark', id=22}
     * [GC (Allocation Failure)  4757K->4881K(9728K), 0.0002352 secs]
     * [GC (Allocation Failure)  4881K->4849K(9728K), 0.0002105 secs]
     * [Full GC (Allocation Failure)  4849K->4717K(9728K), 0.0062050 secs]
     * [GC (Allocation Failure)  4717K->4717K(9728K), 0.0002253 secs]
     * [Full GC (Allocation Failure)  4717K->4699K(9728K), 0.0059970 secs]
     * error：null
     * end：null
     * java.lang.OutOfMemoryError: Java heap space
     * 	at class2.ref.SoftRef.main(SoftRef.java:30)
     *
     */

    //-Xmx10M -Xms10M -XX:+PrintGC
    public static void main(String[] args) {

        User u =  new User("mark",22);

        SoftReference<User> softReference = new SoftReference(u);

        u = null;

        System.out.println(softReference.get());

        System.gc();

        System.out.println("After Gc");

        System.out.println(softReference.get());


        List<Byte[]> list = new ArrayList<>();

        try {
            for(int i = 0;i < 100;i++){
                System.out.println("*********************" + softReference.get());
                list.add(new Byte[1024 * 1024 * 1]);
            }
        }catch (Throwable e){
            e.printStackTrace();
            System.out.println("error：" + softReference.get());
        }

        System.out.println("end：" +     softReference.get());


    }


}


class User{
    public User(String name, int id) {
        this.name = name;
        this.id = id;
    }

    private String name = "";
    private int id = 0;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
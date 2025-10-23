package class2.ref;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class WeakRef {


    /**
     * User{name='mark', id=22}
     * After Gc
     * null
     */
    //java -XX:+PrintCommandLineFlags -version查看本地gc
    public static void main(String[] args) {

        User u =  new User("mark",22);

        WeakReference<User> weakReference = new WeakReference(u);

        u = null;

        System.out.println(weakReference.get());

        System.gc();

        System.out.println("After Gc");

        System.out.println(weakReference.get());



    }

}

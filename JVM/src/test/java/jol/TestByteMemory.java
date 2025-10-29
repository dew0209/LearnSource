package jol;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

import java.util.TreeSet;


public class TestByteMemory {

    public static void main(String[] args) {

//        String[] str = new String[] {"aaa", "bbb", "ccc"};
//        List<String> list=new ArrayList<String>();
//        for (String string : str) {
//            list.add(string);
//        }
//
//        List<String> list2 = new ArrayList<String>(Arrays.asList(str));
//        List<String> list3 = Arrays.asList(str);
//        System.out.println(list3.size());
//        System.out.println(list3.add("222"));
//        List<String> list4 = new ArrayList<String>();
//        Collections.addAll(list4, str);

//        String str1 = "hello";
//        String str2 = "he" + new String("llo");
//        System.err.println(str1 == str2);

//        TreeSet<treeSetTest> set = new TreeSet<>();
//        treeSetTest treeSetTest1 = new treeSetTest();
//        treeSetTest1.a = 1;
//
//        treeSetTest treeSetTest2 = new treeSetTest();
//        treeSetTest2.a = 2;
//        set.add(treeSetTest1);
//        set.add(treeSetTest2);
//        System.out.println(VM.current().details());
//        System.out.println(ClassLayout.parseClass(arr.class).toPrintable());
//
//        System.out.println("===数组大小===");
//        byte[] arr =  new byte[100];
//
//        System.out.println(ClassLayout.parseInstance(arr).toPrintable());
//
//
//        int a = 10;
//        long b = 10L;
//
//        System.out.println(a == b);
//
        MyClass myClass = new MyClass();
        System.out.println(myClass);
        myClass.writer();
        myClass.reader();

    }

}

class arr{
    Byte[] arr = new Byte[100];

}

 class MyClass {
    private int value; // 普通域
    private static MyClass instance;

    public MyClass() {
        this.value = 42; // 1. 在构造函数内写入
    }

    public static void writer() {
        instance = new MyClass(); // 2. 赋值引用
    }

    public static void reader() {
        if (instance != null) {
            System.out.println(instance.value); // 可能看到 0！
        }
    }
}


class treeSetTest implements Comparable<treeSetTest> {

    int a;


    @Override
    public int compareTo(treeSetTest o) {
        return a - o.a;
    }
}

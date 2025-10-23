package class5.classinit;

public class Test {
    public static void main(String[] args) {

    }


    /**
     *
     *
     *  System.out.println(SubClazz.WHAT);
     *
     *  输出：
     *      SuperClass init  //编辑器无法知道value是多少，得具体运行才知道
     *      123
     *  System.out.println(SubClazz.HELLOWORLD);
     *  输出：
     *      hello,world //父类和子类的都没打印，因为打印的是常量。有编辑器优化，实际语句为System.out.println("hello,world");
     *
     *
     *  SuperClazz[] superClazzes = new SuperClazz[10];
     *
     *  输出：
     *      无  //数组不会触发类的实例化
     *
     * public static void main(String[] args) {
     *         System.out.println(SubClazz.value);
     *     }
     *
     * 输出：
     * SuperClass init  //静态资源（除了static final的常量）只会初始化实际属于的类
     * 123
     *
     * public static void main(String[] args) {
     *         System.out.println(SubClazz.value2);
     *     }
     *
     * 输出：
     *
     * SuperClass init      //因为继承
     * subClazz init
     * 123
     */
}

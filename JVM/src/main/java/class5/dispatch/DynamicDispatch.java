package class5.dispatch;

public class DynamicDispatch {

    static abstract class Human{
        public abstract void sayHello();
    }

    static class Man extends Human {
        public void sayHello(){
            System.out.println("Hello WoMan");
        }
    }

    static class WoMan extends Human {
        public void sayHello(){
            System.out.println("Hello man");
        }
    }

    /**
     * Hello WoMan
     * Hello man
     *
     * 根据类的实际类型，调用对应的方法
     *
     */
    public static void main(String[] args) {
        Human h1 = new Man();
        Human h2 = new WoMan();
        h1.sayHello();
        h2.sayHello();
    }
}

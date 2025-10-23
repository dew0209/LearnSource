package class5.dispatch;

public class StaticDispatch {

    static abstract class Human{

    }

    static class Man extends Human{

    }

    static class WoMan extends Human{

    }

    public void sayHello(Human m){
        System.out.println("Hello guy");
    }

    public void sayHello(WoMan m){
        System.out.println("Hello woMan");
    }

    public void sayHello(Man m){
        System.out.println("Hello man");
    }

    /**
     * Hello guy
     * Hello guy
     *
     * 静态分派，方法的重载由静态类型确定
     */
    public static void main(String[] args) {
        StaticDispatch staticDispatch = new StaticDispatch();
        //静态类型       变量的实际类型
        Human h1 = new WoMan();
        Human h2 = new Man();
        staticDispatch.sayHello(h1);
        staticDispatch.sayHello(h2);
    }

}

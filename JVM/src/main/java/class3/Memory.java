package class3;

public class Memory {

    public static void main(String[] args) {
        Stack stack = new Stack();
        Object o1 = new Object();
        System.out.println("o1 = " + o1);
        stack.push(o1);
        Object pop = stack.pop();
        System.out.println("01 = " + pop);

        //如果不降eles[0] = null,会产生内存泄露
        System.out.println(stack.eles[0]);
    }

}

class Stack{

    public Object[] eles;

    private int size = 0;

    private static final int cap = 16;

    public Stack(){
        eles = new Object[cap];
    }

    public void push(Object e){
        eles[size++] = e;
    }

    public Object pop(){
        Object o = eles[--size];
        //没有的话,会产生内存泄露
        eles[size] = null;
        return o;
    }

}
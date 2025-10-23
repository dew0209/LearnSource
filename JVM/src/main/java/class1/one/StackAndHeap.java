package class1.one;

public class StackAndHeap {

    public static void main(String[] args) {
        SimpleHeap s1 = new SimpleHeap(1);
        SimpleHeap s2 = new SimpleHeap(2);
        s1.print();
        s2.print();
    }
}

class SimpleHeap{
    private int id;

    public SimpleHeap(int id){
        this.id = id;
    }
    public void print(){
        System.out.println("My id is " + id);
    }
}

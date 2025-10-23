package class1.meta;

import java.util.ArrayList;
import java.util.List;

public class MetaSpace {

    //Error occurred during initialization of VM
    //MaxMetaspaceSize is too small.

    //-XX:MaxMetaspaceSize=2m
    public static void main(String[] args) {
        List<Object> list = new ArrayList<>();

        int i = 0;
        while (true){
            i++;
            if(i % 10000 == 0){
                System.out.println("i=" + i);
            }
            list.add(new Object());
        }

    }

}

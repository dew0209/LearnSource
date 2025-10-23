package class1.directmemory;

import java.nio.ByteBuffer;

public class DirectMemory {

    //-Xmx100M -XX:MaxDirectMemorySize=10M

    //Exception in thread "main" java.lang.OutOfMemoryError: Direct buffer memory
    public static void main(String[] args) {
        ByteBuffer b = ByteBuffer.allocateDirect(1024 * 1024 * 14);
    }

}

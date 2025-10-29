package class6.ex;

public class PrintSy {

    //false
    //从字节码指令的角度分析
    //0: getstatic     #5  // Field java/lang/System.out:Ljava/io/PrintStream;
    //3: aload_0        // 加载 e1 (原始值)
    //4: aload_1        // 加载 e2
    //5: dup            // 复制 e2
    //6: astore_0       // e1 = e2 (赋值操作)
    //7: if_acmpne     14 // 比较: 原始的e1 和 e2
    //10: iconst_1      // 如果相等 -> true
    //11: goto          15
    //14: iconst_0      // 如果不相等 -> false
    //15: invokevirtual #6  // Method java/io/PrintStream.println:(Z)V
    //18: return
    public static void main(String[] args) {
        PrintSy e1 = new PrintSy();
        PrintSy e2 = new PrintSy();
        print(e1,e2);
    }

    public static void print(PrintSy e1,PrintSy e2){
        System.out.println(e1 == (e1 = e2));
    }

}

package class5.deencrpt;

import java.io.*;

public class XorEncrpt {

    public void xor(InputStream in, OutputStream out) throws IOException {
        int ch;
        while (-1 != (ch = in.read())){
            ch = ch ^ 0x3f;
            out.write(ch);
        }
    }

    public void encrypt(File src,File des) throws IOException {
        InputStream in = new FileInputStream(src);
        FileOutputStream out = new FileOutputStream(des);
        xor(in,out);
        in.close();
        out.close();

    }

    public byte[] decrypt(File src) throws Exception{

        InputStream in = new FileInputStream(src);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        xor(in,bos);
        byte[] data = bos.toByteArray();
        return data;
    }

    public static void main(String[] args) throws IOException {
        File src = new File("D:\\io\\DemoUserSrc.class");
        File desc = new File("D:\\io\\DemoUser.class");
        XorEncrpt xorEncrpt = new XorEncrpt();
        xorEncrpt.encrypt(src,desc);
        System.out.println("加密完成");
    }


}

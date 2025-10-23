package class5.deencrpt;

public class DemoRun {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        CustomClassLoader customClassLoader = new CustomClassLoader("My Class Loader");
        customClassLoader.setBasePath("D:\\io\\");
        Class<?> aClass = customClassLoader.findClass("class5.deencrpt.DemoUser");
        System.out.println(aClass.getClassLoader());
        Object o = aClass.newInstance();
        System.out.println(o);
    }

}

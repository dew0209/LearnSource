package class6.moreconstrc;

public class Food1 {

    private final String foodName;
    private final String reliang;

    private final String dbz;
    private final String tang;
    private final String wss;

    public Food1(String foodName, String reliang, String dbz, String tang, String wss) {
        this.foodName = foodName;
        this.reliang = reliang;
        this.dbz = dbz;
        this.tang = tang;
        this.wss = wss;
    }

    public Food1(String foodName, String reliang) {
        this(foodName,reliang,"","","");
    }
    //一个参数，三个参数等等，都需要搞下，很麻烦
}

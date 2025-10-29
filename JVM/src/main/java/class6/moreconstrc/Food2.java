package class6.moreconstrc;

public class Food2 {

    private final String foodName;
    private final String reliang;

    private  String dbz;
    private  String tang;
    private  String wss;

    public Food2(String foodName, String reliang) {
        this.foodName = foodName;
        this.reliang = reliang;
    }
    //get set在创建对象后，不好控制。且去掉了final，容易被篡改
    public String getDbz() {
        return dbz;
    }

    public void setDbz(String dbz) {
        this.dbz = dbz;
    }

    public String getTang() {
        return tang;
    }

    public void setTang(String tang) {
        this.tang = tang;
    }

    public String getWss() {
        return wss;
    }

    public void setWss(String wss) {
        this.wss = wss;
    }
}

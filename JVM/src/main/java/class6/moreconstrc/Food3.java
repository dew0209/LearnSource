package class6.moreconstrc;

public class Food3 {

    //五个或者五个以上，推荐使用builder模式

    private final String foodName;
    private final String reliang;

    private final String dbz;
    private final String tang;
    private final String wss;

    public static class Build{

        private final String foodName;
        private final String reliang;

        public Build(String foodName, String reliang) {
            this.foodName = foodName;
            this.reliang = reliang;
        }

        private  String dbz;
        private  String tang;
        private  String wss;

        public Build dbz(String dbz){
            this.dbz = dbz;
            return this;
        }
        //... tang和wss省略

        public Food3 builder(){
            return new Food3(this);
        }

    }

    private Food3(Build build){
        this.reliang = build.reliang;
        this.foodName = build.foodName;
        this.dbz = build.dbz;
        this.tang = build.tang;
        this.wss = build.wss;
    }

    public static void main(String[] args) {
        Food3 ceshi = new Build("ceshi", "1111").dbz("2222").builder();
        System.out.println(ceshi);
    }

    @Override
    public String toString() {
        return "Food3{" +
                "foodName='" + foodName + '\'' +
                ", reliang='" + reliang + '\'' +
                ", dbz='" + dbz + '\'' +
                ", tang='" + tang + '\'' +
                ", wss='" + wss + '\'' +
                '}';
    }
}

package settings.enumpojo;

import lombok.Data;
import lombok.Getter;

@Getter
public enum Source {

    LOGIN(1,"注册"),
    EXPORT(2,"导入"),
    HADN(3,"手动添加");


    private int code;
    private String desc;

    Source(int code,String desc){
        this.code = code;
        this.desc = desc;
    }

}

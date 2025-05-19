package settings.entity;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
public class UserForTypeHandle {
    private String id;
    private String name;
    private String nikename;
    private String psw;
    private String status;
    private Email mail;
    private String phone;
    private String type;
    private String registerTime;
    private String loginTime;
    private String lastLoginTime;
    private String bindPostion;
    private String salt;
}

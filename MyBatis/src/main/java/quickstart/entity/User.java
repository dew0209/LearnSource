package quickstart.entity;

import lombok.Data;

@Data
public class User {
    private String id;
    private String name;
    private String nikename;
    private String psw;
    private String status;
    private String mail;
    private String phone;
    private String type;
    private String registerTime;
    private String loginTime;
    private String lastLoginTime;
    private String bindPostion;
    private String salt;
}

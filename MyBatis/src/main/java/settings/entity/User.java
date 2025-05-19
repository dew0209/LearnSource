package settings.entity;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
/*
*
* 在使用
* <typeAliases>
*    <package name="settings.entity"/>
* </typeAliases>
* 的别名定义下，可以使用@Alias("UserEx") 指定具体的别名。
*
*  */
@Alias("UserEx")
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

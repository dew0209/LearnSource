package mapperconfig.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.type.Alias;

@Data
public class User {

    public User(@Param("id") String id, @Param("mail") String mail) {
        this.id = id;
        this.mail = mail;
    }

    public User(@Param("mail") String mail, @Param("type") int type) {
        this.mail = mail;
        this.type = type;
    }

    public User() {

    }


    private String id;
    private String name;
    private String nikename;
    private String psw;
    private String status;
    private String mail;
    private String phone;
    private int type;
    private String registerTime;
    private String loginTime;
    private String lastLoginTime;
    private String bindPostion;
    private String salt;
}

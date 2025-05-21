package mapperconfig.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class QueryUserDTO implements Serializable {

    private String name;

    private String mail;

}

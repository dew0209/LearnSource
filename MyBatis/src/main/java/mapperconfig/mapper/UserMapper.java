package mapperconfig.mapper;

import mapperconfig.dto.QueryUserDTO;
import mapperconfig.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {


    User selectById(@Param("id") String id);

    List<User> selectByDTO(@Param("dto") QueryUserDTO dto);

    List<User> selectByDTOUseParamType( QueryUserDTO dto);

    List<User> selectByDTOUseMap(@Param("map") Map<String,String> map);

    List<User> selectByDTOBase(String name,String mail,QueryUserDTO dto);

}

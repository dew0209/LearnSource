package mapperconfig.mapper;

import mapperconfig.dto.QueryUserDTO;
import mapperconfig.entity.MybatisTest;
import mapperconfig.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    /* select元素的使用 */
    User selectById(@Param("id") String id);

    List<User> selectByDTO(@Param("dto") QueryUserDTO dto);

    List<User> selectByDTOUseParamType( QueryUserDTO dto);

    List<User> selectByDTOUseMap(@Param("map") Map<String,String> map);

    List<User> selectByDTOBase(String name,String mail,QueryUserDTO dto);

    List<User> selectByIdUserResultMapArg(String id);


    /* insert, update 和 delete元素的使用 */
    int inseret0(MybatisTest testInfo);
    int inseret1(MybatisTest testInfo);

    int inseret3(MybatisTest testInfo);
    int inseret4(MybatisTest testInfo);

    List<User> selectDy(String column, String table, String s);
    List<User> selectDyError(String column, String table, String s);
}

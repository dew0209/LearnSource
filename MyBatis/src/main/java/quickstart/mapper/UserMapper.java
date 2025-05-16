package quickstart.mapper;

import org.apache.ibatis.annotations.Param;
import quickstart.entity.User;

public interface UserMapper {

    User selectById(@Param("id") String id);

}

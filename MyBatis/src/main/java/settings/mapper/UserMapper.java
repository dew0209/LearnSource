package settings.mapper;

import org.apache.ibatis.annotations.Param;
import settings.entity.User;
import settings.entity.UserForTypeHandle;

public interface UserMapper {

    User selectById(@Param("id") String id);

}

package settings.mapper;

import org.apache.ibatis.annotations.Param;
import settings.entity.User;
import settings.entity.UserForTypeHandle;

public interface UserMapperTypeHandle {

    User selectById(@Param("id") String id);

    /** 测试TypeHandle */
    UserForTypeHandle selectById2(@Param("id") String id);

}

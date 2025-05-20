package settings.mapper;

import org.apache.ibatis.annotations.Param;
import settings.entity.User;
import settings.entity.UserTypeHandleEnum;

public interface UserMapperTypeHandleEnum {

    int insert1(@Param("handleEnum") UserTypeHandleEnum handleEnum);

    UserTypeHandleEnum getById(@Param("id") String id);

    int insert2(@Param("handleEnum") UserTypeHandleEnum handleEnum);

    UserTypeHandleEnum getById2(@Param("id") String id);
}

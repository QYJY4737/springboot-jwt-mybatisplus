package cn.congee.api.mapper;

import cn.congee.api.entity.UserKmmy;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author: yang
 * @Date: 2020-12-10 7:15
 */
@Mapper
@Repository
public interface UserKmmyMapper extends BaseMapper<UserKmmy> {

    UserKmmy getOne(String uname);

    boolean saveUserKmmy(UserKmmy userKmmy);

    boolean updateUserKmmyLastLoginTime(UserKmmy userKmmy);

}

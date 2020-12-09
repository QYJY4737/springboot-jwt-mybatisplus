package cn.congee.api.service;

import cn.congee.api.common.OutputObject;
import cn.congee.api.common.ResultObj;
import cn.congee.api.entity.UserKmmy;

/**
 * @Author: yang
 * @Date: 2020-12-10 7:16
 */
public interface UserKmmyService {

    /**
     * 登陆
     *
     * @param userKmmy
     * @return
     */
    OutputObject login(UserKmmy userKmmy);

    /**
     * 注册
     *
     * @param userKmmy
     * @return
     */
    ResultObj addUser(UserKmmy userKmmy);

}

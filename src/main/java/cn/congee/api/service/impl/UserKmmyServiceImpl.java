package cn.congee.api.service.impl;

import cn.congee.api.common.OutputObject;
import cn.congee.api.common.ResultObj;
import cn.congee.api.common.ReturnCode;
import cn.congee.api.entity.UserKmmy;
import cn.congee.api.mapper.UserKmmyMapper;
import cn.congee.api.service.UserKmmyService;
import cn.congee.api.util.MD5Util;
import cn.congee.api.util.TokenUtil;
import cn.congee.api.util.UUIDUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;

/**
 * @Author: yang
 * @Date: 2020-12-10 7:20
 */
@Slf4j
@Service
public class UserKmmyServiceImpl implements UserKmmyService {

    @Autowired
    private UserKmmyMapper userKmmyMapper;

    /**
     * 登陆
     *
     * @param userKmmy
     * @return
     */
    @Override
    public OutputObject login(UserKmmy userKmmy){
        QueryWrapper<UserKmmy> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(userKmmy.getUname() != null, "uname", userKmmy.getUname());
        UserKmmy users = userKmmyMapper.getOne(userKmmy.getUname());
        //密码校验
        String s = MD5Util.md5(userKmmy.getPasswd() + userKmmy.getSalt());
        if(users.getPasswd().equals(s) == false){
            return new OutputObject(ReturnCode.FAIL, "密码不正确", userKmmy);
        }
        queryWrapper.in(userKmmy.getPasswd() != null, "passwd", s);
        //通过用户名从数据库查询出该用户
        if (users == null){
            return new OutputObject(ReturnCode.FAIL, "用户不存在", userKmmy);
        }
        String token = TokenUtil.sign(new UserKmmy(userKmmy.getUname(), s));
        HashMap<String, Object> hs = new HashMap<>();
        hs.put("token", token);
        hs.put("userid", users.getUserid());
        users.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
        userKmmyMapper.updateUserKmmyLastLoginTime(users);
        return new OutputObject(String.valueOf(HttpStatus.OK.value()), "成功", hs);
    }

    /**
     * 注册
     *
     * @param userKmmy
     * @return
     */
    @Override
    public ResultObj addUser(UserKmmy userKmmy){
        try {
            //查询用户名是否存在
            QueryWrapper<UserKmmy> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("uname", userKmmy.getUname());
            UserKmmy users = userKmmyMapper.getOne(userKmmy.getUname());
            if(users != null){
                return ResultObj.THE_USER_ALREADY_EXISTS;
            }
            //设置盐
            String salt = UUIDUtil.getUUID();
            userKmmy.setSalt(salt);
            //设置密码加密
            String s = MD5Util.md5(userKmmy.getPasswd() + salt);
            userKmmy.setPasswd(s);
            //设置用户默认头像
            userKmmy.setHeadImg(ReturnCode.DEFAULT_IMG_USER);
            userKmmy.setCreateTime(new Timestamp(System.currentTimeMillis()));
            userKmmy.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            userKmmyMapper.saveUserKmmy(userKmmy);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
}

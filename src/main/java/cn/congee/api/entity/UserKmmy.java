package cn.congee.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表
 *
 * @Author: yang
 * @Date: 2020-12-10 7:14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Accessors(chain = true)                    //用于配置getter和setter方法的生成结果
@EqualsAndHashCode(callSuper = false)         //自动的给model bean实现equals方法和hashcode方法。
public class UserKmmy implements Serializable {

    private static final long serialVersionUID = -1L;

    /**
     * 用户ID 绝对唯一
     */
    @TableId(value = "userid", type = IdType.AUTO)
    private Long userid;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户手机
     */
    private String phone;

    /**
     * 用户权限类型：0普通，1管理员
     */
    private Integer usertype;

    /**
     * 是否有子账户
     */
    private Integer subuser;

    /**
     * 用户名
     */
    private String uname;

    /**
     * 密码
     */
    private String passwd;

    /**
     * 是否激活
     */
    private Boolean isactive;

    /**
     * 加密盐值
     */
    private String salt;

    /**
     * 用户头像
     */
    private String headImg;

    /**
     * 禁用账号 : 0 正常 , 1 禁用
     */
    private Integer locked;

    /**
     * 账号创建时间
     */
    private Date createTime;

    /**
     * 用户最后登陆时间
     */
    private Date lastLoginTime;

    /**
     * 管理员更新用户状态的时间
     */
    private Date updateTime;

    public UserKmmy(String uname, String passwd) {
        this.uname = uname;
        this.passwd = passwd;
    }


}

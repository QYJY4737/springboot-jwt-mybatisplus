package cn.congee.api.common;

import lombok.*;

/**
 * @Author: yang
 * @Date: 2020-12-10 7:17
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ResultObj {

    private String respCode;
    private String respMessage;

    public static final ResultObj LOGIN_SUCCESS = new ResultObj(ReturnCode.SUCCESS, "登陆成功");
    public static final ResultObj LOGIN_FAIL_PASS = new ResultObj(ReturnCode.FAIL, "用户名或密码错误");
    public static final ResultObj LOGIN_FAIL_CODE = new ResultObj(ReturnCode.FAIL, "验证码错误");
    public static final ResultObj THE_USER_ALREADY_EXISTS = new ResultObj(ReturnCode.FAIL, "该用户已存在");

    public static final ResultObj ADD_SUCCESS = new ResultObj(ReturnCode.SUCCESS, "添加成功");
    public static final ResultObj  ADD_ERROR= new ResultObj(ReturnCode.FAIL, "添加失败");

    public static final ResultObj DELETE_SUCCESS = new ResultObj(ReturnCode.SUCCESS, "删除成功");
    public static final ResultObj DELETE_FAIL = new ResultObj(ReturnCode.FAIL, "删除失败");

    public static final ResultObj UPDATE_SUCCESS = new ResultObj(ReturnCode.SUCCESS, "修改成功");
    public static final ResultObj UPDATE_FAIL = new ResultObj(ReturnCode.FAIL, "修改失败");
    public static final ResultObj ILLEGAL_MOBILE_NUMBER = new ResultObj(ReturnCode.FAIL, "手机号不合法");

    public static final ResultObj RESET_SUCCESS = new ResultObj(ReturnCode.SUCCESS, "重置成功");
    public static final ResultObj RESET_FAIL = new ResultObj(ReturnCode.FAIL, "重置失败");

    public static final ResultObj DISPATCH_SUCCESS = new ResultObj(ReturnCode.SUCCESS, "分配成功");
    public static final ResultObj DISPATCH_FAIL = new ResultObj(ReturnCode.FAIL, "分配失败");

    public static final ResultObj BACKINPORT_SUCCESS = new ResultObj(ReturnCode.SUCCESS, "退货成功");
    public static final ResultObj BACKINPORT_FAIL = new ResultObj(ReturnCode.FAIL, "退货失败");
    public static final ResultObj SYNCCACHE_SUCCESS = new ResultObj(ReturnCode.SUCCESS, "同步缓存成功");

    public static final ResultObj DELETE_FAIL_NEWS = new ResultObj(ReturnCode.FAIL, "删除用户失败，该用户是其他用户的直属领导，请先修改该用户的下属的直属领导，再进行删除操作");
    public static final ResultObj DELETE_QUERY = new ResultObj();

}

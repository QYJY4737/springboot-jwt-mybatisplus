package cn.congee.api.common;

import lombok.*;

import java.io.Serializable;

/**
 * @Author: yang
 * @Date: 2020-12-10 7:16
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OutputObject implements Serializable {

    /**
     * 响应码
     */
    private String respCode;

    /**
     * 响应信息
     */
    private String respMessage;

    /**
     * 响应数据
     */
    private Object data;

}

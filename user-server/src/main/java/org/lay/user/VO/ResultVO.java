package org.lay.user.VO;

import lombok.Data;

import java.io.Serializable;

/**
 * Create by Lay
 * 2017-12-31 11:55
 */
@Data
public class ResultVO<T> implements Serializable {

    private static final long serialVersionUID = 5924700805948232244L;

    /** 状态码 */
    private Integer code;

    /** 返回信息 */
    private String msg;

    /** 数据 */
    private T data;
}

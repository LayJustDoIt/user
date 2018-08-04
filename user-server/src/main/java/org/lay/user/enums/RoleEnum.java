package org.lay.user.enums;

import lombok.Getter;

/**
 * 买家/卖家
 * Create by Lay
 * 2018-04-01 12:08
 */
@Getter
public enum RoleEnum {

    BUYER(1, "买家"),
    SELLER(2, "卖家"),
    ;

    private Integer code;

    private String message;

    RoleEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}

package org.lay.user.service;

import org.lay.user.dataobject.UserInfo;

/**
 * Create by Lay
 * 2018-04-01 10:17
 */
public interface UserService {

    /**
     * 根据用户openid查询用户信息
     * @param openid
     * @return
     */
    UserInfo findByOpenid(String openid);
}

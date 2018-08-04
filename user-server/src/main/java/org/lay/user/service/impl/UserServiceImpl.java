package org.lay.user.service.impl;

import org.lay.user.dataobject.UserInfo;
import org.lay.user.repository.UserInfoRepository;
import org.lay.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User Service
 * Create by Lay
 * 2018-04-01 10:18
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    /**
     * 查询用户信息根据openid
     * @param openid
     * @return
     */
    @Override
    public UserInfo findByOpenid(String openid) {
        return userInfoRepository.findByOpenid(openid);
    }
}

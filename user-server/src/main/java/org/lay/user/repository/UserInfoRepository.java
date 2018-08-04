package org.lay.user.repository;

import org.lay.user.dataobject.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UserInfo Repo
 * Create by Lay
 * 2018-04-01 10:10
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {

    /** 根据openid查询 */
    UserInfo findByOpenid(String openid);

}

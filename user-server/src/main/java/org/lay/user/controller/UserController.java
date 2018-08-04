package org.lay.user.controller;

import org.lay.user.VO.ResultVO;
import org.lay.user.contants.CookieConstant;
import org.lay.user.contants.RedisConstant;
import org.lay.user.dataobject.UserInfo;
import org.lay.user.enums.ResultEnum;
import org.lay.user.enums.RoleEnum;
import org.lay.user.service.UserService;
import org.lay.user.utils.CookieUtil;
import org.lay.user.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Create by Lay
 * 2018-04-01 10:23
 */
@RestController
@RequestMapping("/login")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 买家登录
     * @param openid
     * @param response
     * @return json + cookie
     */
    @GetMapping("/buyer")
    public ResultVO buyer(@RequestParam String openid,
                          HttpServletResponse response) {
        // openid和数据库中进行匹配
        UserInfo userInfo = userService.findByOpenid(openid);
        if (userInfo == null) {
            // 返回错误
            return ResultVOUtil.error(ResultEnum.LOGIN_FAIL);
        }
        // 判断角色权限
        if (RoleEnum.BUYER.getCode() != userInfo.getRole()) {
            return ResultVOUtil.error(ResultEnum.ROLE_ERROR);
        }
        // 设置cookie
        CookieUtil.setCookie(response, CookieConstant.OPENID, openid, CookieConstant.EXPIRE);
        return ResultVOUtil.success();
    }

    /**
     * 卖家登录
     * @return
     */
    @GetMapping("/seller")
    public ResultVO seller(@RequestParam String openid,
                           HttpServletResponse response,
                           HttpServletRequest request) {
        /** 验证数据库 */
        UserInfo userInfo = userService.findByOpenid(openid);
        if (userInfo == null) {
            return ResultVOUtil.error(ResultEnum.LOGIN_FAIL);
        }

        /** 角色权限验证 */
        if (RoleEnum.SELLER.getCode() != userInfo.getRole()) {
            return ResultVOUtil.error(ResultEnum.ROLE_ERROR);
        }

        /** 判断用户是否登录 */
        Cookie cookie = CookieUtil.getCookie(request, CookieConstant.TOKEN);
        if (cookie != null && !StringUtils.isEmpty(redisTemplate.opsForValue().get(
                String.format(RedisConstant.TOKEN_TEMPLATE, cookie.getValue())))) {
            return ResultVOUtil.success();
        }

        /** redis key 过期时间 */
        Integer expire = CookieConstant.EXPIRE;
        String token = UUID.randomUUID().toString();
        /** 写入redis  key=UUID, value=openid */
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_TEMPLATE, token),
                openid,
                expire,
                TimeUnit.SECONDS);

        /** 写入cookie */
        CookieUtil.setCookie(response, CookieConstant.TOKEN, token, CookieConstant.EXPIRE);
        return ResultVOUtil.success();
    }

}

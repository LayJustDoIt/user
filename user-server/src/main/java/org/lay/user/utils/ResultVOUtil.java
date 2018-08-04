package org.lay.user.utils;

import org.lay.user.VO.ResultVO;
import org.lay.user.enums.ResultEnum;

/**
 * Create by Lay
 * 2017-12-31 13:10
 */
public class ResultVOUtil {

    /**
     * 成功时： 有参
     * @param obj
     * @return
     */
    public static ResultVO success(Object obj) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(obj);
        return resultVO;
    }

    /**
     * 成功时： 无参
     * @return
     */
    public static ResultVO success() {
        return success(null);
    }


    /**
     * 失败
     * @param resultEnum
     * @return
     */
    public static ResultVO error(ResultEnum resultEnum) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(resultEnum.getCode());
        resultVO.setMsg(resultEnum.getMessage());
        return resultVO;
    }
}

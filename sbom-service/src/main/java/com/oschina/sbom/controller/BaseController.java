package com.oschina.sbom.controller;

import com.oschina.sbom.common.result.R;
import lombok.extern.slf4j.Slf4j;

/**
 * 基础控制器
 */
@Slf4j
public class BaseController {

    /**
     * 返回成功结果
     */
    protected <T> R<T> success() {
        return R.ok();
    }

    /**
     * 返回成功结果
     * @param data 数据
     */
    protected <T> R<T> success(T data) {
        return R.ok(data);
    }

    /**
     * 返回成功结果
     * @param data 数据
     * @param message 消息
     */
    protected <T> R<T> success(T data, String message) {
        return R.ok(data, message);
    }

    /**
     * 返回失败结果
     */
    protected <T> R<T> fail() {
        return R.fail();
    }

    /**
     * 返回失败结果
     * @param message 错误信息
     */
    protected <T> R<T> fail(String message) {
        return R.fail(message);
    }

    /**
     * 返回失败结果
     * @param code 错误码
     * @param message 错误信息
     */
    protected <T> R<T> fail(Integer code, String message) {
        return R.fail(code, message);
    }
}

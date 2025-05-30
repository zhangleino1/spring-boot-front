package com.oschina.sbom.common.result;

import lombok.Data;
import java.io.Serializable;

/**
 * 统一返回结果类
 */
@Data
public class R<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 返回消息
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;

    /**
     * 是否成功
     */
    private Boolean success;

    /**
     * 时间戳
     */
    private long timestamp = System.currentTimeMillis();

    private R() {
    }

    /**
     * 成功返回结果
     */
    public static <T> R<T> ok() {
        R<T> r = new R<>();
        r.setCode(ResultCode.SUCCESS.getCode());
        r.setMessage(ResultCode.SUCCESS.getMessage());
        r.setSuccess(true);
        return r;
    }

    /**
     * 成功返回结果
     * @param data 返回的数据
     */
    public static <T> R<T> ok(T data) {
        R<T> r = new R<>();
        r.setCode(ResultCode.SUCCESS.getCode());
        r.setMessage(ResultCode.SUCCESS.getMessage());
        r.setData(data);
        r.setSuccess(true);
        return r;
    }

    /**
     * 成功返回结果
     * @param data 返回的数据
     * @param message 返回的消息
     */
    public static <T> R<T> ok(T data, String message) {
        R<T> r = new R<>();
        r.setCode(ResultCode.SUCCESS.getCode());
        r.setMessage(message);
        r.setData(data);
        r.setSuccess(true);
        return r;
    }

    /**
     * 失败返回结果
     */
    public static <T> R<T> fail() {
        R<T> r = new R<>();
        r.setCode(ResultCode.FAILED.getCode());
        r.setMessage(ResultCode.FAILED.getMessage());
        r.setSuccess(false);
        return r;
    }

    /**
     * 失败返回结果
     * @param message 错误信息
     */
    public static <T> R<T> fail(String message) {
        R<T> r = new R<>();
        r.setCode(ResultCode.FAILED.getCode());
        r.setMessage(message);
        r.setSuccess(false);
        return r;
    }

    /**
     * 失败返回结果
     * @param code 错误码
     * @param message 错误信息
     */
    public static <T> R<T> fail(Integer code, String message) {
        R<T> r = new R<>();
        r.setCode(code);
        r.setMessage(message);
        r.setSuccess(false);
        return r;
    }

    /**
     * 失败返回结果
     * @param resultCode 错误码枚举
     */
    public static <T> R<T> fail(IResultCode resultCode) {
        R<T> r = new R<>();
        r.setCode(resultCode.getCode());
        r.setMessage(resultCode.getMessage());
        r.setSuccess(false);
        return r;
    }
}

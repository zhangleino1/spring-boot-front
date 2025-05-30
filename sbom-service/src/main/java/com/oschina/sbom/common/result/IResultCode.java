package com.oschina.sbom.common.result;

/**
 * 响应码接口
 */
public interface IResultCode {
    /**
     * 获取响应码
     *
     * @return 响应码
     */
    Integer getCode();

    /**
     * 获取响应消息
     *
     * @return 响应消息
     */
    String getMessage();
}

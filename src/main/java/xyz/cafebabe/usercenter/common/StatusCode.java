package xyz.cafebabe.usercenter.common;

/**
 * 状态码接口
 */
public interface StatusCode {

    /**
     * 获得状态码
     *
     * @return 状态码
     */
    int getCode();

    /**
     * 获得状态信息
     *
     * @return 状态信息
     */
    String getMessage();

}

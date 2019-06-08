package lewiszlw.sso.server.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-05-31
 */
@Data
@Accessors(chain = true)
public class WebResponse {

    /**
     * 状态码
     */
    private Integer status;

    /**
     * 消息
     */
    private String message;

    /**
     * 数据
     */
    private Object data;

    public static final int STATUS_OK = 200;
    public static final int STATUS_BAD_REQUEST = 400;
    public static final int STATUS_UNAUTHORIZED = 401;

    public static WebResponse createOKWebResponse(Object data) {
        return new WebResponse().setStatus(STATUS_OK).setMessage("请求成功").setData(data);
    }

    public static WebResponse createWebResponse(int status, String message) {
        return new WebResponse().setStatus(status).setMessage(message);
    }

    public static WebResponse createWebResponse(int status, String message, Object data) {
        return new WebResponse().setStatus(status).setMessage(message).setData(data);
    }

}

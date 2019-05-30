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

}

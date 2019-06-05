package lewiszlw.sso.api.dto;

import com.alibaba.fastjson.JSONObject;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-06-05
 */
public class WebResponseDTO {

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
    private JSONObject data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }
}

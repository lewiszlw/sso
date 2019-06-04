package lewiszlw.sso.server.constant;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-06-03
 */
public class Constants {

    /**
     * code有效时间10分钟
     */
    public static final int CODE_VALID_TIME = 10 * 60;
    /**
     * refresh token 有效时间 10天
     */
    public static final int REFRESH_TOKEN_VALID_TIME = 10 * 24 * 3600;
    /**
     * access token 有效时间 1天
     */
    public static final int ACCESS_TOKEN_VALID_TIME = 1 * 24 * 3600;


    /**
     * token 状态：无效
     */
    public static final int STATUS_INVALID = 0;

    /**
     * token 状态：有效
     */
    public static final int STATUS_VALID = 1;
}

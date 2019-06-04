package lewiszlw.sso.server.util;

import java.util.UUID;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-06-02
 */
public class TokenUtils {

    public static String createAccessToken() {
        // TODO 暂时使用UUID作为token
        return UUID.randomUUID().toString();
    }

    public static String createRefreshToken() {
        // TODO 暂时使用UUID作为token
        return UUID.randomUUID().toString();
    }

    public static String createClientId() {
        // TODO 暂时使用UUID作为client id
        return UUID.randomUUID().toString();
    }

    public static String createClientSecret() {
        // TODO 暂时使用UUID作为Client secret
        return UUID.randomUUID().toString();
    }

    public static String createCode() {
        // TODO 暂时使用UUID
        return UUID.randomUUID().toString();
    }
}

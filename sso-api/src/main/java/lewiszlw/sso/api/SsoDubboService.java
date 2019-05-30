package lewiszlw.sso.api;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-05-31
 */
public interface SsoDubboService {

    void logout();

    boolean validateToken();
}

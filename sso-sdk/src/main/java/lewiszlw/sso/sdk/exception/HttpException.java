package lewiszlw.sso.sdk.exception;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-06-05
 */
public class HttpException extends RuntimeException {

    public HttpException (String message) {
        super(message);
    }

    public HttpException (String message, Throwable cause) {
        super(message, cause);
    }
}

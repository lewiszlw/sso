package lewiszlw.sso.server.util;

import com.google.common.collect.ImmutableMultimap;
import io.jsonwebtoken.Jwts;

import java.util.Date;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-05-31
 */
public class JwtUtils {

    public static String createAccessToken() {

        String test = Jwts.builder().setSubject("test").setExpiration(new Date()).compact();

        return test;
    }

    public static void main(String[] args) {
        System.out.println(createAccessToken());
    }
}

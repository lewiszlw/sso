package lewiszlw.sso.server.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-06-03
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WebUtilsTest {

    @Test
    public void testIsHttpUri() {
        Assert.assertTrue(WebUtils.isHttpUri("http://lewiszlw.com/sso/callback"));
        Assert.assertTrue(WebUtils.isHttpUri("http://127.0.0.1:8080/sso/callback"));
        Assert.assertTrue(!WebUtils.isHttpUri("httpss://127.0.0.1:8080/sso/callback"));
        Assert.assertTrue(WebUtils.isHttpUri("http://localhost:8080/sso/callback"));
        Assert.assertTrue(WebUtils.isHttpUri("https://lewiszlw.com/sso/callback"));
    }
}

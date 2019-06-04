package lewiszlw.sso.server.util;

import lewiszlw.sso.server.constant.Constants;
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
public class DateUtilsTest {

    @Test
    public void testGenExpiredDate() {
        System.out.println(DateUtils.genExpiredDate(Constants.CODE_VALID_TIME));
        System.out.println(DateUtils.genExpiredDate(Constants.REFRESH_TOKEN_VALID_TIME));
        System.out.println(DateUtils.genExpiredDate(Constants.ACCESS_TOKEN_VALID_TIME));
    }
}

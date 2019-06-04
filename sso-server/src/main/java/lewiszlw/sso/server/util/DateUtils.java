package lewiszlw.sso.server.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-06-03
 */
public class DateUtils {

    /**
     * 生成过期时间
     * @param time 有效时间长度（秒）
     */
    public static Date genExpiredDate(int time) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, time);
        return calendar.getTime();
    }
}

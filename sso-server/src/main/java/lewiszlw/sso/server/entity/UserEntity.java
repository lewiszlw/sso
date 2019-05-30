package lewiszlw.sso.server.entity;

import lombok.Data;

import java.util.Date;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-05-29
 */
@Data
public class UserEntity {

    /**
     * user id
     */
    private Integer id;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 住址
     */
    private String address;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新时间
     */
    private Date updatedTime;
}

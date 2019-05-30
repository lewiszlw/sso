package lewiszlw.sso.server.entity;

import lombok.Data;

import java.util.Date;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-05-31
 */
@Data
public class AccountEntity {

    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * user id
     */
    private Integer userId;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新时间
     */
    private Date updatedTime;
}

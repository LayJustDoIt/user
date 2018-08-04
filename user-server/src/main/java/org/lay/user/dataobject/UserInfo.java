package org.lay.user.dataobject;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Create by Lay
 * 2018-04-01 10:02
 */
@Data
@Entity
public class UserInfo {

    @Id
    private String id;

    private String username;
    
    private String password;
    
    private String openid;
    
    private Integer role;

}

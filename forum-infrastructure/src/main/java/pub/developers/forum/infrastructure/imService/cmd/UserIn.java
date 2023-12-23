package pub.developers.forum.infrastructure.imService.cmd;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserIn implements Serializable {

    private String account;

    private String password;
}

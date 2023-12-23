package pub.developers.forum.infrastructure.imService.query;

import lombok.Data;

import java.io.Serializable;

@Data
public class TokenBody implements Serializable {

    private String token;
}

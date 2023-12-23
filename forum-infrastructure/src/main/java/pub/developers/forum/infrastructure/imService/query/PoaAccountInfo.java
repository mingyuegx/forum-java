package pub.developers.forum.infrastructure.imService.query;

import lombok.Data;

import java.io.Serializable;

@Data
public class PoaAccountInfo implements Serializable {
    private String poaNumber;

    private String originName;
}

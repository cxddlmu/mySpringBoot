package my.springboot.server.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Setter
@Getter
public class UserPK implements Serializable {
    @Column(name = "User", nullable = false)
    private Integer user;

    @Column(name = "Host", nullable = false)
    private String host;
}

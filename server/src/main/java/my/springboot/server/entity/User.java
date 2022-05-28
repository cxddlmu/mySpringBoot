package my.springboot.server.entity;


import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "user")
@Data
public class User implements Serializable {

    @EmbeddedId
    private UserPK userPK;

//    @Column(name = "authentication_string")
//    private String authenticationString;
}

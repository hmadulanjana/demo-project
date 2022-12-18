package net.codinghermit.api.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
// import net.codinghermit.api.util.Encoder;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private long id;
    private String userName;
    private String emailId;
    private String role;
    private String password;
    // private String password = Encoder.encodeEncryptUserPassword(this.password);
}

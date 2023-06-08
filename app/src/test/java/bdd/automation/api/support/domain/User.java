package bdd.automation.api.support.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

    @Builder.Default
    private int id = 10;
    @Builder.Default
    private String username = "bhyan" ;
    @Builder.Default
    private String firstName = "Bryan";
    @Builder.Default
    private String lastName = "Brito";
    @Builder.Default
    private String email = "bhyan@gmail.com";
    @Builder.Default
    private String password = "123456";
    @Builder.Default
    private String phone = "849999999";
    @Builder.Default
    private int userStatus = 1;
}

package by.bsac.models;


import org.springframework.context.annotation.Lazy;

import javax.persistence.*;

@Entity(name = "user")
@Table(name = "user")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int user_id;

    @Column(name = "user_id_alias", unique = true, length = 30)
    private String user_id_alias;

    @OneToOne(mappedBy = "account_owner")
    private Account user_account;

    public int getUserId() {
        return user_id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    public String getUserIdAlias() {
        return user_id_alias;
    }

    public void setUserIdAlias(String user_id_alias) {
        this.user_id_alias = user_id_alias;
    }


    public Account getUser_account() {
        return user_account;
    }

    public void setUser_account(Account user_account) {
        this.user_account = user_account;
    }
}

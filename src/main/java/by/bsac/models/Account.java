package by.bsac.models;







import javax.persistence.*;

@Entity()
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue
    @Column(name = "account_id")
    private int account_id;

    @Column(name = "account_email", nullable = false, unique = true)
    private String account_email;

    @Column(name = "account_password", nullable = false)
    private String account_password;

    @OneToOne
    @JoinColumn(name = "account_owner_user_id", referencedColumnName = "user_id")
    @MapsId
    private User account_owner;

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }


    public String getAccount_email() {
        return account_email;
    }

    public void setAccount_email(String account_email) {
        this.account_email = account_email;
    }

    public String getAccount_password() {
        return account_password;
    }

    public void setAccount_password(String account_password) {
        this.account_password = account_password;
    }

    public User getAccount_owner() {
        return account_owner;
    }

    public void setAccount_owner(User account_owner) {
        this.account_owner = account_owner;
    }
}

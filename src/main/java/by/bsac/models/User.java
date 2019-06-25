package by.bsac.models;


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
}

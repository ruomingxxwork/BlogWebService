package webservice.blog.blog;

import javax.persistence.*;


@Entity
@Table(name = "auth_user")

public class Author {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

}

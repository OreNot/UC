package ucproject.domain;



import javax.persistence.*;

@Entity
public class Statement {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String comment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User autor;

    public Statement() {
    }

    public Statement(String comment, User user) {
        this.autor = user;
        this.comment = comment;
    }

    public String getAuthorName()
    {
        return autor != null ? autor.getUsername() : "none";
    }

    public User getAutor() {
        return autor;
    }

    public void setAutor(User autor) {
        this.autor = autor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

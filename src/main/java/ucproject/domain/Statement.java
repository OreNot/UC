package ucproject.domain;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Statement {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String comment;

    public Statement() {
    }

    public Statement(String comment) {
        this.comment = comment;
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

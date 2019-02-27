package ucproject.domain;



import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Statement {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    //private String regDate;
    private Date regDate;

    private String comment;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User autor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "exec_id")
    private User executor;

    private String status;

    private String filename;

    private String packfilename;

    public Statement() {
    }

    public Statement(User user, Client client, String comment, String status) {
        //this.regDate = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date());
        this.regDate = new Date();
        this.autor = user;
        this.client = client;
        this.comment = comment;
        this.status = status;

    }

    public String getPackfilename() {
        return packfilename;
    }

    public void setPackfilename(String packfilename) {
        this.packfilename = packfilename;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getStatus() {
        return status != null ? status : "none";
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getExecutor() {
        return executor;
    }

    public void setExecutor(User executor) {
        this.executor = executor;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getAuthorName()
    {
        return autor != null ? autor.getUsername() : "none";
    }

    public String getExecutorName()
    {
        return executor != null ? executor.getUsername() : "Не назначен";
    }

    public String getExecutorFio()
    {
        return executor.getFio().getFio() != null ? executor.getFio().getFio() : "Не назначен";
    }

    public String getClientOrg()
    {
        return client != null ? client.getOrganization().getOrgName() : "none";
    }


    public String getClientFio()
    {
        return client != null ? client.getFio().getFio() : "none";
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

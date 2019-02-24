package ucproject.repos;

import org.springframework.data.repository.CrudRepository;
import ucproject.domain.Statement;
import ucproject.domain.User;

import java.util.List;


public interface StatementRepo extends CrudRepository<Statement, Integer> {

    List<Statement> findByComment(String comment);
    List<Statement> findByAutor(User autor);
}

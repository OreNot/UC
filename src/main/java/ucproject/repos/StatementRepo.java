package ucproject.repos;

import org.springframework.data.repository.CrudRepository;
import ucproject.domain.Statement;
import ucproject.domain.User;

import java.util.List;
import java.util.Optional;


public interface StatementRepo extends CrudRepository<Statement, Integer> {

    List<Statement> findByComment(String comment);
    Optional<Statement> findById(Integer id);
    List<Statement> findByAutor(User autor);
    List<Statement> findByExecutor(User executor);
    List<Statement> findByExecutorNull();
    List<Statement> findByStatusAndExecutor(String status, User user);

}

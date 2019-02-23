package ucproject.repos;

import org.springframework.data.repository.CrudRepository;
import ucproject.domain.Statement;

import java.util.List;


public interface StatementRepo extends CrudRepository<Statement, Integer> {

    List<Statement> findByComment(String comment);
}

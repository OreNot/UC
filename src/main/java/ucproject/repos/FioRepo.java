package ucproject.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ucproject.domain.Fio;

public interface FioRepo extends JpaRepository<Fio, Integer> {
    Fio findByFio(String fio);
    Fio findByFioIgnoreCase(String fio);
    Fio findByFioContaining(String fio);
}

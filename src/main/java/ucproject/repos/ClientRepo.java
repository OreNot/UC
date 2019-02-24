package ucproject.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ucproject.domain.Client;
import ucproject.domain.Fio;
import ucproject.domain.Organization;


public interface ClientRepo extends JpaRepository<Client, Integer>{

    Client findByFio(Fio fio);
    Client findByOrganization(Organization organization);
}

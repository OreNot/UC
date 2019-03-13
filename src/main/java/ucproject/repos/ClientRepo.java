package ucproject.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ucproject.domain.Client;
import ucproject.domain.Fio;
import ucproject.domain.Organization;


public interface ClientRepo extends JpaRepository<Client, Integer>{

    Iterable<Client> findByFio(Fio fio);
    //Client findByOrganization(Organization organization);
    Iterable<Client> findByOrganization(Organization organization);
    Client findByFioAndOrganization(Fio fio, Organization organization);
}

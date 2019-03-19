package ucproject.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ucproject.domain.Organization;

import java.util.List;

public interface OrganizationRepo extends JpaRepository<Organization, Integer> {

    Organization findByOrgName(String username);
    Organization findByOrgNameIgnoreCase(String username);
    //List<Organization> findByOrgNameContainingIgnoreCase(String orgName);
    Organization findByOrgNameLike(String username);
    Organization findByOrgNameContaining(String username);
    List<Organization> findByOrgNameContainingIgnoreCase(String orgname);
}

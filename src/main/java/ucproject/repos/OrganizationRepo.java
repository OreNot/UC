package ucproject.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ucproject.domain.Organization;

public interface OrganizationRepo extends JpaRepository<Organization, Integer> {

    Organization findByOrgName(String username);
    Organization findByOrgNameIgnoreCase(String username);
    Organization findByOrgNameLike(String username);
    Organization findByOrgNameContaining(String username);
    Organization findByOrgNameContainingIgnoreCase(String orgname);
}

package kz.kegoc.bln.repository.dict;

import kz.kegoc.bln.entity.dict.Organization;
import kz.kegoc.bln.repository.common.JpaRepository;
import javax.ejb.Local;

@Local
public interface OrganizationRepository extends JpaRepository<Organization> {
    Organization selectByTin(String tin);
}

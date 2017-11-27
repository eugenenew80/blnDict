package kz.kegoc.bln.repository.dict;

import kz.kegoc.bln.entity.dict.Organization;
import kz.kegoc.bln.repository.common.Repository;

import javax.ejb.Local;

@Local
public interface CompanyRepository extends Repository<Organization> {
    Organization selectByTin(String tin);
}

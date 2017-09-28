package kz.kegoc.bln.repository.dict;

import kz.kegoc.bln.entity.dict.Company;
import kz.kegoc.bln.repository.common.Repository;

public interface CompanyRepository extends Repository<Company> {
    Company selectByTin(String tin);
}

package kz.kegoc.bln.repository.dict;

import kz.kegoc.bln.entity.dict.AccountingType;
import kz.kegoc.bln.repository.common.JpaRepository;
import javax.ejb.Local;

@Local
public interface AccountingTypeRepository extends JpaRepository<AccountingType> {}

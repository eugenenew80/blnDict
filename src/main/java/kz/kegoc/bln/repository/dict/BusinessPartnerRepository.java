package kz.kegoc.bln.repository.dict;

import kz.kegoc.bln.entity.dict.BusinessPartner;
import kz.kegoc.bln.repository.common.JpaRepository;
import javax.ejb.Local;

@Local
public interface BusinessPartnerRepository extends JpaRepository<BusinessPartner> {}

package kz.kegoc.bln.repository.dict;

import kz.kegoc.bln.entity.dict.Bank;
import kz.kegoc.bln.entity.dict.BusinessPartner;
import kz.kegoc.bln.repository.common.Repository;

import javax.ejb.Local;

@Local
public interface BankRepository extends Repository<Bank> {}

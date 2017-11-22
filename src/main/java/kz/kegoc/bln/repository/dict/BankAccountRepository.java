package kz.kegoc.bln.repository.dict;

import kz.kegoc.bln.entity.dict.BankAccount;
import kz.kegoc.bln.repository.common.Repository;

import javax.ejb.Local;

@Local
public interface BankAccountRepository extends Repository<BankAccount> {}

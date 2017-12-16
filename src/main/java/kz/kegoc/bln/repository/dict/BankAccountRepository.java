package kz.kegoc.bln.repository.dict;

import kz.kegoc.bln.entity.dict.BankAccount;
import kz.kegoc.bln.repository.common.JpaRepository;

import javax.ejb.Local;

@Local
public interface BankAccountRepository extends JpaRepository<BankAccount> {}

package kz.kegoc.bln.service.dict;

import kz.kegoc.bln.entity.dict.BankAccount;
import kz.kegoc.bln.service.common.EntityService;

import javax.ejb.Local;

@Local
public interface BankAccountService extends EntityService<BankAccount> {}

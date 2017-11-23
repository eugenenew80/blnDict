package kz.kegoc.bln.service.dict;

import kz.kegoc.bln.entity.dict.Bank;
import kz.kegoc.bln.service.common.EntityService;
import javax.ejb.Local;

@Local
public interface BankService extends EntityService<Bank> {}

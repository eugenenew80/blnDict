package kz.kegoc.bln.service.dict.impl;

import kz.kegoc.bln.entity.dict.BankAccount;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.repository.dict.BankAccountRepository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.dict.BankAccountService;
import kz.kegoc.bln.translator.Translator;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;

@Stateless
public class BankAccountServiceImpl extends AbstractEntityService<BankAccount> implements BankAccountService {
	@Inject
    public BankAccountServiceImpl(BankAccountRepository repository, Validator validator, Filter<BankAccount> prePersistFilter, Translator<BankAccount> translator) {
        super(repository, validator, prePersistFilter, translator);
    }
}

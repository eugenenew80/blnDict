package kz.kegoc.bln.service.dict.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;
import kz.kegoc.bln.entity.dict.AccountingType;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.repository.dict.AccountingTypeRepository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.dict.AccountingTypeService;
import kz.kegoc.bln.translator.Translator;


@Stateless
public class AccountingTypeServiceImpl extends AbstractEntityService<AccountingType> implements AccountingTypeService {
	@Inject
    public AccountingTypeServiceImpl(AccountingTypeRepository repository, Validator validator, Filter<AccountingType> prePersistFilter, Translator<AccountingType> translator) {
        super(repository, validator, prePersistFilter, translator);
    }
}

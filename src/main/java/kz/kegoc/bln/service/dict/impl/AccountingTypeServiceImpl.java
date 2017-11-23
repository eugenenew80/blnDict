package kz.kegoc.bln.service.dict.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;
import kz.kegoc.bln.entity.dict.AccountingType;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.repository.common.Repository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.dict.AccountingTypeService;


@Stateless
public class AccountingTypeServiceImpl extends AbstractEntityService<AccountingType>
        implements AccountingTypeService {
    
	@Inject
    public AccountingTypeServiceImpl(Repository<AccountingType> repository, Validator validator, Filter<AccountingType> prePersistFilter) {
        super(repository, validator, prePersistFilter);
    }
}

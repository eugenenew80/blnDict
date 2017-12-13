package kz.kegoc.bln.service.dict.impl;

import kz.kegoc.bln.entity.dict.Bank;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.repository.dict.BankRepository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.dict.BankService;
import kz.kegoc.bln.translator.Translator;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;

@Stateless
public class BankServiceImpl extends AbstractEntityService<Bank>
        implements BankService {

	@Inject
    public BankServiceImpl(BankRepository repository, Validator validator, Filter<Bank> prePersistFilter, Translator<Bank> translator) {
        super(repository, validator, prePersistFilter, translator);
    }
}

package kz.kegoc.bln.service.dict.impl;

import kz.kegoc.bln.entity.dict.PostAddress;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.repository.common.Repository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.dict.PostAddressService;
import kz.kegoc.bln.translator.Translator;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;


@Stateless
public class PostAddressServiceImpl extends AbstractEntityService<PostAddress> implements PostAddressService {
	@Inject
    public PostAddressServiceImpl(Repository<PostAddress> repository, Validator validator, Filter<PostAddress> prePersistFilter, Translator<PostAddress> translator) {
        super(repository, validator, prePersistFilter, translator);
    }
}

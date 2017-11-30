package kz.kegoc.bln.service.dict.impl;

import kz.kegoc.bln.entity.dict.VoltageClass;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.repository.common.Repository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.dict.VoltageClassService;
import kz.kegoc.bln.translator.Translator;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;

@Stateless
public class VoltageClassServiceImpl extends AbstractEntityService<VoltageClass> implements VoltageClassService {

	@Inject
    public VoltageClassServiceImpl(Repository<VoltageClass> repository, Validator validator, Filter<VoltageClass> prePersistFilter, Translator<VoltageClass> translator) {
        super(repository, validator, prePersistFilter, translator);
    }
}

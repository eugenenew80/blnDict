package kz.kegoc.bln.service.dict.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;

import kz.kegoc.bln.entity.dict.VoltageTrans;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.repository.common.Repository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.dict.VoltageTransService;
import kz.kegoc.bln.translator.Translator;


@Stateless
public class VoltageTransServiceImpl extends AbstractEntityService<VoltageTrans>
        implements VoltageTransService {
    
	@Inject
    public VoltageTransServiceImpl(Repository<VoltageTrans> repository, Validator validator, Filter<VoltageTrans> prePersistFilter, Translator<VoltageTrans> translator) {
        super(repository, validator, prePersistFilter, translator);
    }
}

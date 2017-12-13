package kz.kegoc.bln.service.dict.impl;

import kz.kegoc.bln.entity.dict.MeteringPointVoltageTrans;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.repository.dict.MeteringPointVoltageTransRepository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.dict.MeteringPointVoltageTransService;
import kz.kegoc.bln.translator.Translator;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;

@Stateless
public class MeteringPointVoltageTransServiceImpl extends AbstractEntityService<MeteringPointVoltageTrans>
        implements MeteringPointVoltageTransService {

	@Inject
    public MeteringPointVoltageTransServiceImpl(MeteringPointVoltageTransRepository repository, Validator validator, Filter<MeteringPointVoltageTrans> prePersistFilter, Translator<MeteringPointVoltageTrans> translator) {
        super(repository, validator, prePersistFilter, translator);
    }
}

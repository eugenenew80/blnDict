package kz.kegoc.bln.service.dict.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;
import kz.kegoc.bln.entity.dict.MeteringPoint;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.repository.dict.MeteringPointRepository;
import kz.kegoc.bln.service.common.AbstractEntityServiceOrg;
import kz.kegoc.bln.service.dict.MeteringPointService;
import kz.kegoc.bln.translator.Translator;

@Stateless
public class MeteringPointServiceImpl extends AbstractEntityServiceOrg<MeteringPoint>
        implements MeteringPointService {
    
	@Inject
    public MeteringPointServiceImpl(MeteringPointRepository repository, Validator validator, Filter<MeteringPoint> prePersistFilter, Translator<MeteringPoint> translator) {
        super(repository, validator, prePersistFilter, translator);
    }
}

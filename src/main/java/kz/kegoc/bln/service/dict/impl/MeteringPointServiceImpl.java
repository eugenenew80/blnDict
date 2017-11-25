package kz.kegoc.bln.service.dict.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;
import kz.kegoc.bln.entity.dict.MeteringPoint;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.repository.common.Repository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.dict.MeteringPointService;
import kz.kegoc.bln.translator.Translator;


@Stateless
public class MeteringPointServiceImpl extends AbstractEntityService<MeteringPoint>
        implements MeteringPointService {
    
	@Inject
    public MeteringPointServiceImpl(Repository<MeteringPoint> repository, Validator validator, Filter<MeteringPoint> prePersistFilter, Translator<MeteringPoint> translator) {
        super(repository, validator, prePersistFilter, translator);
    }
}

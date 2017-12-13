package kz.kegoc.bln.service.dict.impl;

import kz.kegoc.bln.entity.dict.SubstationMeteringPoint;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.repository.common.Repository;
import kz.kegoc.bln.repository.dict.SubstationMeteringPointRepository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.dict.SubstationMeteringPointService;
import kz.kegoc.bln.translator.Translator;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;

@Stateless
public class SubstationMeteringPointServiceImpl extends AbstractEntityService<SubstationMeteringPoint>
        implements SubstationMeteringPointService {

	@Inject
    public SubstationMeteringPointServiceImpl(SubstationMeteringPointRepository repository, Validator validator, Filter<SubstationMeteringPoint> prePersistFilter, Translator<SubstationMeteringPoint> translator) {
        super(repository, validator, prePersistFilter, translator);
    }
}

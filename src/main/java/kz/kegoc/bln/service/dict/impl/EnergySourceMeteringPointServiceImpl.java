package kz.kegoc.bln.service.dict.impl;

import kz.kegoc.bln.entity.dict.EnergySourceMeteringPoint;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.repository.common.Repository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.dict.EnergySourceMeteringPointService;
import kz.kegoc.bln.translator.Translator;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;

@Stateless
public class EnergySourceMeteringPointServiceImpl extends AbstractEntityService<EnergySourceMeteringPoint>
        implements EnergySourceMeteringPointService {

	@Inject
    public EnergySourceMeteringPointServiceImpl(Repository<EnergySourceMeteringPoint> repository, Validator validator, Filter<EnergySourceMeteringPoint> prePersistFilter, Translator<EnergySourceMeteringPoint> translator) {
        super(repository, validator, prePersistFilter, translator);
    }
}

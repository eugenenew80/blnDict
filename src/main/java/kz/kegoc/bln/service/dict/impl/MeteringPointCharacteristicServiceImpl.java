package kz.kegoc.bln.service.dict.impl;

import kz.kegoc.bln.entity.dict.MeteringPointCharacteristic;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.repository.dict.MeteringPointCharacteristicRepository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.dict.MeteringPointCharacteristicService;
import kz.kegoc.bln.translator.Translator;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;


@Stateless
public class MeteringPointCharacteristicServiceImpl extends AbstractEntityService<MeteringPointCharacteristic> implements MeteringPointCharacteristicService {
	@Inject
    public MeteringPointCharacteristicServiceImpl(MeteringPointCharacteristicRepository repository, Validator validator, Filter<MeteringPointCharacteristic> prePersistFilter, Translator<MeteringPointCharacteristic> translator) {
        super(repository, validator, prePersistFilter, translator);
    }
}

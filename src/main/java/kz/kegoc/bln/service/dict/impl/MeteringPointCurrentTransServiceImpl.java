package kz.kegoc.bln.service.dict.impl;

import kz.kegoc.bln.entity.dict.MeteringPointCurrentTrans;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.repository.dict.MeteringPointCurrentTransRepository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.dict.MeteringPointCurrentTransService;
import kz.kegoc.bln.translator.Translator;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;

@Stateless
public class MeteringPointCurrentTransServiceImpl extends AbstractEntityService<MeteringPointCurrentTrans>
        implements MeteringPointCurrentTransService {

	@Inject
    public MeteringPointCurrentTransServiceImpl(MeteringPointCurrentTransRepository repository, Validator validator, Filter<MeteringPointCurrentTrans> prePersistFilter, Translator<MeteringPointCurrentTrans> translator) {
        super(repository, validator, prePersistFilter, translator);
    }
}

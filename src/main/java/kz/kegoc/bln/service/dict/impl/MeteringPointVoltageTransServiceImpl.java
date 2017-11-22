package kz.kegoc.bln.service.dict.impl;

import kz.kegoc.bln.entity.dict.MeteringPointVoltageTrans;
import kz.kegoc.bln.repository.common.Repository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.dict.MeteringPointVoltageTransService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;

@Stateless
public class MeteringPointVoltageTransServiceImpl extends AbstractEntityService<MeteringPointVoltageTrans>
        implements MeteringPointVoltageTransService {

	@Inject
    public MeteringPointVoltageTransServiceImpl(Repository<MeteringPointVoltageTrans> repository, Validator validator) {
        super(repository, validator);
    }
}

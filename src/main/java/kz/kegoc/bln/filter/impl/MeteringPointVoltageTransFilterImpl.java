package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.ejb.SessionContext;
import kz.kegoc.bln.entity.dict.MeteringPointVoltageTrans;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.MeteringPointVoltageTransService;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class MeteringPointVoltageTransFilterImpl implements Filter<MeteringPointVoltageTrans> {
    public MeteringPointVoltageTrans filter(MeteringPointVoltageTrans entity, SessionContext context) {
        return prepare(entity, context);
    }

    private MeteringPointVoltageTrans prepare(MeteringPointVoltageTrans entity, SessionContext context) {
        if (entity.getId()!=null) {
            MeteringPointVoltageTrans curEntity = service.findById(entity.getId(), null);
            entity.setCreateDate(curEntity.getCreateDate());
            entity.setCreateBy(curEntity.getCreateBy());
        }

        return entity;
    }

    @Inject
    private MeteringPointVoltageTransService service;
}

package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.ejb.SessionContext;
import kz.kegoc.bln.entity.dict.MeteringPointMeter;
import kz.kegoc.bln.filter.AbstractFilter;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.MeteringPointMeterService;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class MeteringPointMeterFilterImpl extends AbstractFilter<MeteringPointMeter> implements Filter<MeteringPointMeter> {
    public MeteringPointMeter filter(MeteringPointMeter entity, SessionContext context) {
        return prepare(entity, context);
    }

    private MeteringPointMeter prepare(MeteringPointMeter entity, SessionContext context) {
        if (entity.getId()!=null) {
            MeteringPointMeter curEntity = service.findById(entity.getId(), null);
            entity.setCreateDate(curEntity.getCreateDate());
            entity.setCreateBy(curEntity.getCreateBy());
        }

        entity = addUpdateInfo(entity, context);
        return entity;
    }

    @Inject
    private MeteringPointMeterService service;
}

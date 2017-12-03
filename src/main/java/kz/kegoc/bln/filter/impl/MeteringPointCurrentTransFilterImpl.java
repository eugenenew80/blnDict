package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.ejb.SessionContext;
import kz.kegoc.bln.entity.dict.MeteringPointCurrentTrans;
import kz.kegoc.bln.filter.AbstractFilter;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.MeteringPointCurrentTransService;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class MeteringPointCurrentTransFilterImpl extends AbstractFilter<MeteringPointCurrentTrans> implements Filter<MeteringPointCurrentTrans> {
    public MeteringPointCurrentTrans filter(MeteringPointCurrentTrans entity, SessionContext context) {
        return prepare(entity, context);
    }

    private MeteringPointCurrentTrans prepare(MeteringPointCurrentTrans entity, SessionContext context) {
        if (entity.getId()!=null) {
            MeteringPointCurrentTrans curEntity = service.findById(entity.getId(), null);
            entity.setCreateDate(curEntity.getCreateDate());
            entity.setCreateBy(curEntity.getCreateBy());
        }

        entity = addUpdateInfo(entity, context);
        return entity;
    }

    @Inject
    private MeteringPointCurrentTransService service;
}

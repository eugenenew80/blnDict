package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.ejb.SessionContext;
import kz.kegoc.bln.entity.dict.MeteringPointCurrentTrans;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.MeteringPointCurrentTransService;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class MeteringPointCurrentTransFilterImpl implements Filter<MeteringPointCurrentTrans> {
    public MeteringPointCurrentTrans filter(MeteringPointCurrentTrans entity, SessionContext context) {
        return prepare(entity);
    }

    private MeteringPointCurrentTrans prepare(MeteringPointCurrentTrans entity) {
        if (entity.getId()!=null) {
            MeteringPointCurrentTrans curEntity = service.findById(entity.getId(), null);
            entity.setCreateDate(curEntity.getCreateDate());
            entity.setCreateBy(curEntity.getCreateBy());
        }

        return entity;
    }

    @Inject
    private MeteringPointCurrentTransService service;
}

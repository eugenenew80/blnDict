package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.ejb.SessionContext;
import kz.kegoc.bln.entity.dict.SubstationMeteringPoint;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.SubstationMeteringPointService;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class SubstationMeteringPointFilterImpl implements Filter<SubstationMeteringPoint> {
    public SubstationMeteringPoint filter(SubstationMeteringPoint entity, SessionContext context) {
        return prepare(entity, context);
    }

    private SubstationMeteringPoint prepare(SubstationMeteringPoint entity, SessionContext context) {
        if (entity.getId()!=null) {
            SubstationMeteringPoint curEntity = service.findById(entity.getId(), null);
            entity.setCreateDate(curEntity.getCreateDate());
            entity.setCreateBy(curEntity.getCreateBy());
        }

        return entity;
    }

    @Inject
    private SubstationMeteringPointService service;
}

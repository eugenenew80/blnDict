package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.entity.dict.SubstationMeteringPoint;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.SubstationMeteringPointService;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class SubstationMeteringPointFilterImpl implements Filter<SubstationMeteringPoint> {
    public SubstationMeteringPoint filter(SubstationMeteringPoint entity) {
        return prepare(entity);
    }

    private SubstationMeteringPoint prepare(SubstationMeteringPoint entity) {
        if (entity.getId()!=null) {
            SubstationMeteringPoint curEntity = service.findById(entity.getId());
            entity.setCreateDate(curEntity.getCreateDate());
            entity.setCreateBy(curEntity.getCreateBy());
        }

        return entity;
    }

    @Inject
    private SubstationMeteringPointService service;
}

package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.ejb.SessionContext;
import kz.kegoc.bln.entity.dict.EnergySourceMeteringPoint;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.EnergySourceMeteringPointService;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class EnergySourceMeteringPointFilterImpl implements Filter<EnergySourceMeteringPoint> {
    public EnergySourceMeteringPoint filter(EnergySourceMeteringPoint entity, SessionContext context) {
        return prepare(entity);
    }

    private EnergySourceMeteringPoint prepare(EnergySourceMeteringPoint entity) {
        if (entity.getId()!=null) {
            EnergySourceMeteringPoint curEntity = service.findById(entity.getId(), null);
            entity.setCreateDate(curEntity.getCreateDate());
            entity.setCreateBy(curEntity.getCreateBy());
        }

        return entity;
    }

    @Inject
    private EnergySourceMeteringPointService service;
}

package kz.kegoc.bln.filter.dict;

import kz.kegoc.bln.ejb.SessionContext;
import kz.kegoc.bln.entity.dict.EnergySourceMeteringPoint;
import kz.kegoc.bln.filter.AbstractFilter;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.EnergySourceMeteringPointService;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class EnergySourceMeteringPointFilterImpl extends AbstractFilter<EnergySourceMeteringPoint> implements Filter<EnergySourceMeteringPoint> {
    public EnergySourceMeteringPoint filter(EnergySourceMeteringPoint entity, SessionContext context) {
        return prepare(entity, context);
    }

    private EnergySourceMeteringPoint prepare(EnergySourceMeteringPoint entity, SessionContext context) {
        if (entity.getId()!=null) {
            EnergySourceMeteringPoint curEntity = service.findById(entity.getId(), null);
            entity.setCreateDate(curEntity.getCreateDate());
            entity.setCreateBy(curEntity.getCreateBy());
        }

        entity = addUpdateInfo(entity, context);
        return entity;
    }

    @Inject
    private EnergySourceMeteringPointService service;
}

package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.entity.dict.EnergySourceMeteringPoint;
import kz.kegoc.bln.filter.Filter;

import javax.ejb.Stateless;

@Stateless
public class EnergySorceMeteringPointFilterImpl implements Filter<EnergySourceMeteringPoint> {
    public EnergySourceMeteringPoint filter(EnergySourceMeteringPoint entity) {
        return translate(entity);
    }

    private EnergySourceMeteringPoint translate(EnergySourceMeteringPoint entity) {
        return entity;
    }
}

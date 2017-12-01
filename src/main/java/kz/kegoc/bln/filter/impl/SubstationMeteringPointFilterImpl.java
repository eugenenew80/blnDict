package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.entity.dict.SubstationMeteringPoint;
import kz.kegoc.bln.filter.Filter;
import javax.ejb.Stateless;

@Stateless
public class SubstationMeteringPointFilterImpl implements Filter<SubstationMeteringPoint> {
    public SubstationMeteringPoint filter(SubstationMeteringPoint entity) {
        return entity;
    }
}

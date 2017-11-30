package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.entity.dict.MeteringPointVoltageTrans;
import kz.kegoc.bln.filter.Filter;

import javax.ejb.Stateless;

@Stateless
public class MeteringPointVoltageTransFilterImpl implements Filter<MeteringPointVoltageTrans> {
    public MeteringPointVoltageTrans filter(MeteringPointVoltageTrans entity) {
        return translate(entity);
    }

    private MeteringPointVoltageTrans translate(MeteringPointVoltageTrans entity) {
        return entity;
    }
}

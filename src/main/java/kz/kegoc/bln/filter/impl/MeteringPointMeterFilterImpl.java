package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.entity.dict.MeteringPointMeter;
import kz.kegoc.bln.filter.Filter;
import javax.ejb.Stateless;

@Stateless
public class MeteringPointMeterFilterImpl implements Filter<MeteringPointMeter> {
    public MeteringPointMeter filter(MeteringPointMeter entity) {
        return translate(entity);
    }

    private MeteringPointMeter translate(MeteringPointMeter entity) {
        return entity;
    }
}

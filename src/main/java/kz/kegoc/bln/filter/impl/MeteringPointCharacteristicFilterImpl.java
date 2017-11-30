package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.entity.dict.MeteringPointCharacteristic;
import kz.kegoc.bln.filter.Filter;
import javax.ejb.Stateless;

@Stateless
public class MeteringPointCharacteristicFilterImpl implements Filter<MeteringPointCharacteristic> {
    public MeteringPointCharacteristic filter(MeteringPointCharacteristic entity) {
        return translate(entity);
    }

    private MeteringPointCharacteristic translate(MeteringPointCharacteristic entity) {
        return entity;
    }
}

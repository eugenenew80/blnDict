package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.entity.dict.MeteringPointCharacteristic;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.MeteringPointCharacteristicService;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class MeteringPointCharacteristicFilterImpl implements Filter<MeteringPointCharacteristic> {
    public MeteringPointCharacteristic filter(MeteringPointCharacteristic entity) {
        return prepare(entity);
    }

    private MeteringPointCharacteristic prepare(MeteringPointCharacteristic entity) {
        if (entity.getId()!=null) {
            MeteringPointCharacteristic curEntity = service.findById(entity.getId());
            entity.setCreateDate(curEntity.getCreateDate());
            entity.setCreateBy(curEntity.getCreateBy());
        }

        return entity;
    }

    @Inject
    private MeteringPointCharacteristicService service;
}

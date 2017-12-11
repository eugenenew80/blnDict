package kz.kegoc.bln.filter.dict;

import kz.kegoc.bln.ejb.SessionContext;
import kz.kegoc.bln.entity.dict.MeteringPointCharacteristic;
import kz.kegoc.bln.filter.AbstractFilter;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.MeteringPointCharacteristicService;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class MeteringPointCharacteristicFilterImpl extends AbstractFilter<MeteringPointCharacteristic> implements Filter<MeteringPointCharacteristic> {
    public MeteringPointCharacteristic filter(MeteringPointCharacteristic entity, SessionContext context) {
        return prepare(entity, context);
    }

    private MeteringPointCharacteristic prepare(MeteringPointCharacteristic entity, SessionContext context) {
        if (entity.getId()!=null) {
            MeteringPointCharacteristic curEntity = service.findById(entity.getId(), null);
            entity.setCreateDate(curEntity.getCreateDate());
            entity.setCreateBy(curEntity.getCreateBy());
        }

        entity = addUpdateInfo(entity, context);
        return entity;
    }

    @Inject
    private MeteringPointCharacteristicService service;
}

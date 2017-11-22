package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.MeteringPoint;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.MeteringPointService;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class MeteringPointFilterImpl implements Filter<MeteringPoint> {
    public MeteringPoint filter(MeteringPoint entity) {
        if (entity.getId()!=null) {
        }
        return translate(entity);
    }

    private MeteringPoint translate(MeteringPoint entity) {
        return entity;
    }

    @Inject
    private MeteringPointService meteringPointService;

    @Inject
    private Lang defLang;
}

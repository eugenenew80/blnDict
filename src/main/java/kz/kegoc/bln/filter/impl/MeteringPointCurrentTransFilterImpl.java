package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.entity.dict.MeteringPointCurrentTrans;
import kz.kegoc.bln.filter.Filter;

import javax.ejb.Stateless;

@Stateless
public class MeteringPointCurrentTransFilterImpl implements Filter<MeteringPointCurrentTrans> {
    public MeteringPointCurrentTrans filter(MeteringPointCurrentTrans entity) {
        return entity;
    }
}

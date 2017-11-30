package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.entity.dict.EnergySourceBusinessPartner;
import kz.kegoc.bln.filter.Filter;

import javax.ejb.Stateless;

@Stateless
public class EnergySorceBusinessPartnerFilterImpl implements Filter<EnergySourceBusinessPartner> {
    public EnergySourceBusinessPartner filter(EnergySourceBusinessPartner entity) {
        return translate(entity);
    }

    private EnergySourceBusinessPartner translate(EnergySourceBusinessPartner entity) {
        return entity;
    }
}

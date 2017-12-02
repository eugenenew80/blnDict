package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.entity.dict.EnergySourceBusinessPartner;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.EnergySourceBusinessPartnerService;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class EnergySorceBusinessPartnerFilterImpl implements Filter<EnergySourceBusinessPartner> {
    public EnergySourceBusinessPartner filter(EnergySourceBusinessPartner entity) {
        return prepare(entity);
    }

    private EnergySourceBusinessPartner prepare(EnergySourceBusinessPartner entity) {
        if (entity.getId()!=null) {
            EnergySourceBusinessPartner curEntity = service.findById(entity.getId());
            entity.setCreateDate(curEntity.getCreateDate());
            entity.setCreateBy(curEntity.getCreateBy());
        }

        return entity;
    }

    @Inject
    private EnergySourceBusinessPartnerService service;
}

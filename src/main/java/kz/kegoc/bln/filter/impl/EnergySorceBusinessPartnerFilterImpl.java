package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.ejb.SessionContext;
import kz.kegoc.bln.entity.dict.EnergySourceBusinessPartner;
import kz.kegoc.bln.filter.AbstractFilter;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.EnergySourceBusinessPartnerService;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class EnergySorceBusinessPartnerFilterImpl extends AbstractFilter<EnergySourceBusinessPartner> implements Filter<EnergySourceBusinessPartner> {
    public EnergySourceBusinessPartner filter(EnergySourceBusinessPartner entity, SessionContext context) {
        return prepare(entity, context);
    }

    private EnergySourceBusinessPartner prepare(EnergySourceBusinessPartner entity, SessionContext context) {
        if (entity.getId()!=null) {
            EnergySourceBusinessPartner curEntity = service.findById(entity.getId(), null);
            entity.setCreateDate(curEntity.getCreateDate());
            entity.setCreateBy(curEntity.getCreateBy());
        }

        entity = addUpdateInfo(entity, context);
        return entity;
    }

    @Inject
    private EnergySourceBusinessPartnerService service;
}

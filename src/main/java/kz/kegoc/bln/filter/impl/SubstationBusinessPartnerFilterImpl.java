package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.entity.dict.SubstationBusinessPartner;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.SubstationBusinessPartnerService;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class SubstationBusinessPartnerFilterImpl implements Filter<SubstationBusinessPartner> {
    public SubstationBusinessPartner filter(SubstationBusinessPartner entity) {
        return prepare(entity);
    }

    private SubstationBusinessPartner prepare(SubstationBusinessPartner entity) {
        if (entity.getId()!=null) {
            SubstationBusinessPartner curEntity = service.findById(entity.getId());
            entity.setCreateDate(curEntity.getCreateDate());
            entity.setCreateBy(curEntity.getCreateBy());
        }

        return entity;
    }

    @Inject
    private SubstationBusinessPartnerService service;
}

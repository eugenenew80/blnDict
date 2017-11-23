package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.Substation;
import kz.kegoc.bln.entity.dict.translate.SubstationTranslate;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.SubstationService;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;

@Stateless
public class SubstationFilterImpl implements Filter<Substation> {
    public Substation filter(Substation entity) {
        if (entity.getId()!=null) {
            Substation curEntity = substationService.findById(entity.getId());

            if (entity.getTranslations()==null)
                entity.setTranslations(curEntity.getTranslations());
        }
        return translate(entity);
    }

    private Substation translate(Substation entity) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;
        if (entity.getTranslations()==null)
            entity.setTranslations(new HashMap<>());

        SubstationTranslate translate = entity.getTranslations().getOrDefault(lang, new SubstationTranslate());
        translate.setLang(lang);
        translate.setSubstation(entity);
        translate.setName(entity.getName());
        translate.setShortName(entity.getShortName());
        translate.setAddress(entity.getAddress());
        entity.getTranslations().put(lang, translate);

        return entity;
    }

    @Inject
    private SubstationService substationService;

    @Inject
    private Lang defLang;
}

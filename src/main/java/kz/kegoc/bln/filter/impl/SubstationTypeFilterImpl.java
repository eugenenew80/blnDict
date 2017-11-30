package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.SubstationType;
import kz.kegoc.bln.entity.dict.translate.SubstationTypeTranslate;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.SubstationTypeService;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;

@Stateless
public class SubstationTypeFilterImpl implements Filter<SubstationType> {
    public SubstationType filter(SubstationType entity) {
        if (entity.getId()!=null) {
            SubstationType curEntity = service.findById(entity.getId());

            if (entity.getTranslations()==null)
                entity.setTranslations(curEntity.getTranslations());
        }
        return translate(entity);
    }

    private SubstationType translate(SubstationType entity) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;
        if (entity.getTranslations()==null)
            entity.setTranslations(new HashMap<>());

        SubstationTypeTranslate translate = entity.getTranslations().getOrDefault(lang, new SubstationTypeTranslate());
        translate.setLang(lang);
        translate.setSubstationType(entity);
        translate.setName(entity.getName());
        translate.setShortName(entity.getShortName());
        entity.getTranslations().put(lang, translate);

        return entity;
    }

    @Inject
    private SubstationTypeService service;

    @Inject
    private Lang defLang;
}

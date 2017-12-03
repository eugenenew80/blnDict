package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.ejb.SessionContext;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.SubstationType;
import kz.kegoc.bln.entity.dict.translate.SubstationTypeTranslate;
import kz.kegoc.bln.filter.AbstractFilter;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.SubstationTypeService;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;

@Stateless
public class SubstationTypeFilterImpl extends AbstractFilter<SubstationType> implements Filter<SubstationType> {
    public SubstationType filter(SubstationType entity, SessionContext context) {
        return translate(prepare(entity, context), context);
    }

    private SubstationType prepare(SubstationType entity, SessionContext context) {
        if (entity.getId()!=null) {
            SubstationType curEntity = service.findById(entity.getId(), null);

            entity.setCreateDate(curEntity.getCreateDate());
            entity.setCreateBy(curEntity.getCreateBy());

            if (entity.getTranslations()==null)
                entity.setTranslations(curEntity.getTranslations());
        }

        if (entity.getTranslations()==null)
            entity.setTranslations(new HashMap<>());

        entity = addUpdateInfo(entity, context);
        return entity;
    }

    private SubstationType translate(SubstationType entity, SessionContext context) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;

        SubstationTypeTranslate translate = entity.getTranslations().getOrDefault(lang, new SubstationTypeTranslate());
        translate = addUpdateInfo(translate, context);
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

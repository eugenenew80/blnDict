package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.ejb.SessionContext;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.Substation;
import kz.kegoc.bln.entity.dict.translate.SubstationTranslate;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.SubstationService;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.HashMap;

@Stateless
public class SubstationFilterImpl implements Filter<Substation> {
    public Substation filter(Substation entity, SessionContext context) {
        return translate(prepare(entity, context), context);
    }

    private Substation prepare(Substation entity, SessionContext context) {
        if (entity.getId()!=null) {
            Substation curEntity = substationService.findById(entity.getId(), null);

            entity.setCreateDate(curEntity.getCreateDate());
            entity.setCreateBy(curEntity.getCreateBy());

            if (entity.getTranslations()==null)
                entity.setTranslations(curEntity.getTranslations());
        }

        if (entity.getTranslations()==null)
            entity.setTranslations(new HashMap<>());

        return entity;
    }

    private Substation translate(Substation entity, SessionContext context) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;

        SubstationTranslate translate = entity.getTranslations().getOrDefault(lang, new SubstationTranslate());
        if (translate.getId()==null)
            translate.setCreateDate(LocalDateTime.now());
        else
            translate.setLastUpdateDate(LocalDateTime.now());

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

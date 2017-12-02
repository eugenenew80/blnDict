package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.EnergyNode;
import kz.kegoc.bln.entity.dict.translate.EnergyNodeTranslate;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.EnergyNodeService;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.HashMap;

@Stateless
public class EnergyNodeFilterImpl implements Filter<EnergyNode> {
    public EnergyNode filter(EnergyNode entity) {
        return translate(prepare(entity));
    }

    private EnergyNode prepare(EnergyNode entity) {
        if (entity.getId()!=null) {
            EnergyNode curEntity = service.findById(entity.getId());

            entity.setCreateDate(curEntity.getCreateDate());
            entity.setCreateBy(curEntity.getCreateBy());

            if (entity.getTranslations()==null)
                entity.setTranslations(curEntity.getTranslations());
        }

        if (entity.getTranslations()==null)
            entity.setTranslations(new HashMap<>());

        return entity;
    }

    private EnergyNode translate(EnergyNode entity) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;

        EnergyNodeTranslate translate = entity.getTranslations().getOrDefault(lang, new EnergyNodeTranslate());
        if (translate.getId()==null)
            translate.setCreateDate(LocalDateTime.now());
        else
            translate.setLastUpdateDate(LocalDateTime.now());

        translate.setLang(lang);
        translate.setEnergyNode(entity);
        translate.setName(entity.getName());
        translate.setShortName(entity.getShortName());
        entity.getTranslations().put(lang, translate);

        return entity;
    }

    @Inject
    private EnergyNodeService service;

    @Inject
    private Lang defLang;
}

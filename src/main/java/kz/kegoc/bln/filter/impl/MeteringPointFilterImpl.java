package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.ejb.SessionContext;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.MeteringPoint;
import kz.kegoc.bln.entity.dict.translate.MeteringPointTranslate;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.MeteringPointService;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.HashMap;

@Stateless
public class MeteringPointFilterImpl implements Filter<MeteringPoint> {
    public MeteringPoint filter(MeteringPoint entity, SessionContext context) {
        return translate(prepare(entity));
    }

    private MeteringPoint prepare(MeteringPoint entity) {
        if (entity.getId()!=null) {
            MeteringPoint curEntity = meteringPointService.findById(entity.getId(), null);

            entity.setCreateDate(curEntity.getCreateDate());
            entity.setCreateBy(curEntity.getCreateBy());

            if (entity.getTranslations()==null)
                entity.setTranslations(curEntity.getTranslations());
        }

        if (entity.getTranslations()==null)
            entity.setTranslations(new HashMap<>());

        return entity;
    }

    private MeteringPoint translate(MeteringPoint entity) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;

        MeteringPointTranslate translate = entity.getTranslations().getOrDefault(lang, new MeteringPointTranslate());
        if (translate.getId()==null)
            translate.setCreateDate(LocalDateTime.now());
        else
            translate.setLastUpdateDate(LocalDateTime.now());

        translate.setLang(lang);
        translate.setMeteringPoint(entity);
        translate.setName(entity.getName());
        translate.setShortName(entity.getShortName());
        entity.getTranslations().put(lang, translate);

        return entity;
    }

    @Inject
    private MeteringPointService meteringPointService;

    @Inject
    private Lang defLang;
}

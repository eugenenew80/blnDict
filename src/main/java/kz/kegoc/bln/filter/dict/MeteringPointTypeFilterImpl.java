package kz.kegoc.bln.filter.dict;

import kz.kegoc.bln.ejb.SessionContext;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.MeteringPointType;
import kz.kegoc.bln.entity.dict.translate.MeteringPointTypeTranslate;
import kz.kegoc.bln.filter.AbstractFilter;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.MeteringPointTypeService;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;

@Stateless
public class MeteringPointTypeFilterImpl extends AbstractFilter<MeteringPointType> implements Filter<MeteringPointType> {
    public MeteringPointType filter(MeteringPointType entity, SessionContext context) {
        return translate(prepare(entity, context), context);
    }

    private MeteringPointType prepare(MeteringPointType entity, SessionContext context) {
        if (entity.getId()!=null) {
            MeteringPointType curEntity = service.findById(entity.getId(), null);

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

    private MeteringPointType translate(MeteringPointType entity, SessionContext context) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;

        MeteringPointTypeTranslate translate = entity.getTranslations().getOrDefault(lang, new MeteringPointTypeTranslate());
        translate = addUpdateInfo(translate, context);
        translate.setLang(lang);
        translate.setMeteringPointType(entity);
        translate.setName(entity.getName());
        entity.getTranslations().put(lang, translate);

        return entity;
    }

    @Inject
    private MeteringPointTypeService service;

    @Inject
    private Lang defLang;
}

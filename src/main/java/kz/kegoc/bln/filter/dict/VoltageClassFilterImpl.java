package kz.kegoc.bln.filter.dict;

import kz.kegoc.bln.webapi.filters.SessionContext;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.VoltageClass;
import kz.kegoc.bln.entity.dict.translate.VoltageClassTranslate;
import kz.kegoc.bln.filter.AbstractFilter;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.VoltageClassService;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;

@Stateless
public class VoltageClassFilterImpl extends AbstractFilter<VoltageClass> implements Filter<VoltageClass> {
    public VoltageClass filter(VoltageClass entity, SessionContext context) {
        return translate(prepare(entity, context), context);
    }

    private VoltageClass prepare(VoltageClass entity, SessionContext context) {
        if (entity.getId()!=null) {
            VoltageClass curEntity = service.findById(entity.getId(), context);

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

    private VoltageClass translate(VoltageClass entity, SessionContext context) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;

        VoltageClassTranslate translate = entity.getTranslations().getOrDefault(lang, new VoltageClassTranslate());
        translate = addUpdateInfo(translate, context);
        translate.setLang(lang);
        translate.setVoltageClass(entity);
        translate.setName(entity.getName());
        entity.getTranslations().put(lang, translate);

        return entity;
    }

    @Inject
    private VoltageClassService service;

    @Inject
    private Lang defLang;
}

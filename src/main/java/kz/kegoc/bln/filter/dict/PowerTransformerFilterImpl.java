package kz.kegoc.bln.filter.dict;

import kz.kegoc.bln.entity.dict.Organization;
import kz.kegoc.bln.webapi.filters.SessionContext;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.PowerTransformer;
import kz.kegoc.bln.entity.dict.translate.PowerTransformerTranslate;
import kz.kegoc.bln.filter.AbstractFilter;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.PowerTransformerService;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;

@Stateless
public class PowerTransformerFilterImpl extends AbstractFilter<PowerTransformer> implements Filter<PowerTransformer> {
    public PowerTransformer filter(PowerTransformer entity, SessionContext context) {
        return translate(prepare(entity, context), context);
    }

    private PowerTransformer prepare(PowerTransformer entity, SessionContext context) {
        if (entity.getId()!=null) {
            PowerTransformer curEntity = service.findById(entity.getId(), null);

            entity.setCreateDate(curEntity.getCreateDate());
            entity.setCreateBy(curEntity.getCreateBy());

            if (entity.getTranslations()==null)
                entity.setTranslations(curEntity.getTranslations());
        }

        if (entity.getOrg()==null)
            entity.setOrg(new Organization(context.getUser().getOrgId()));

        if (entity.getTranslations()==null)
            entity.setTranslations(new HashMap<>());

        entity = addUpdateInfo(entity, context);
        return entity;
    }

    private PowerTransformer translate(PowerTransformer entity, SessionContext context) {
        Lang lang = entity.getLang()!=null ? entity.getLang() : defLang;

        PowerTransformerTranslate translate = entity.getTranslations().getOrDefault(lang, new PowerTransformerTranslate());
        translate = addUpdateInfo(translate, context);
        translate.setLang(lang);
        translate.setPowerTransformer(entity);
        translate.setName(entity.getName());
        entity.getTranslations().put(lang, translate);

        return entity;
    }

    @Inject
    private PowerTransformerService service;

    @Inject
    private Lang defLang;
}

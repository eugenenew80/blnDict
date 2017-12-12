package kz.kegoc.bln.translator.dict;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.Region;
import kz.kegoc.bln.entity.dict.translate.RegionTranslate;
import kz.kegoc.bln.translator.Translator;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class RegionTranslatorImpl implements Translator<Region> {
    public Region translate(Region entity, Lang lang) {
        entity.setLang(lang);

        if (entity.getTranslations()==null)
            return entity;

        RegionTranslate translate = entity.getTranslations().get(lang);
        if (translate==null)
            translate = entity.getTranslations().get(defLang);

        if (translate!=null)
            entity.setName(translate.getName());

        return entity;
    }

    @Inject
    private Lang defLang;
}

package kz.kegoc.bln.translator.dict;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.Substation;
import kz.kegoc.bln.entity.dict.translate.SubstationTranslate;
import kz.kegoc.bln.translator.Translator;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class SubstationTranslatorImpl implements Translator<Substation> {
    public Substation translate(Substation entity, Lang lang) {
        entity.setLang(lang);

        if (entity.getTranslations()==null)
            return entity;

        SubstationTranslate translate = entity.getTranslations().get(lang);
        if (translate==null)
            translate = entity.getTranslations().get(defLang);

        if (translate!=null) {
            entity.setName(translate.getName());
            entity.setShortName(translate.getShortName());
            entity.setAddress(translate.getAddress());
        }

        return entity;
    }

    @Inject
    private Lang defLang;
}

package kz.kegoc.bln.translator.dict;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.Organization;
import kz.kegoc.bln.entity.dict.translate.OrganizationTranslate;
import kz.kegoc.bln.translator.Translator;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class OrganizationTranslatorImpl implements Translator<Organization> {
    public Organization translate(Organization entity, Lang lang) {
        entity.setLang(lang);

        if (entity.getTranslations()==null)
            return entity;

        OrganizationTranslate translate = entity.getTranslations().get(lang);
        if (translate==null)
            translate = entity.getTranslations().get(defLang);

        if (translate!=null) {
            entity.setName(translate.getName());
            entity.setShortName(translate.getShortName());
        }

        return entity;
    }

    @Inject
    private Lang defLang;
}

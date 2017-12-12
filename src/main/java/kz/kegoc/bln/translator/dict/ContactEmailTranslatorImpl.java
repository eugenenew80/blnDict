package kz.kegoc.bln.translator.dict;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.ContactEmail;
import kz.kegoc.bln.entity.dict.translate.ContactEmailTranslate;
import kz.kegoc.bln.translator.Translator;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class ContactEmailTranslatorImpl implements Translator<ContactEmail> {
    public ContactEmail translate(ContactEmail entity, Lang lang) {
        entity.setLang(lang);

        if (entity.getTranslations()==null)
            return entity;

        ContactEmailTranslate translate = entity.getTranslations().get(lang);
        if (translate==null)
            translate = entity.getTranslations().get(defLang);

        if (translate!=null)
            entity.setDescription(translate.getDescription());

        return entity;
    }

    @Inject
    private Lang defLang;
}

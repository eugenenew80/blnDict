package kz.kegoc.bln.translator.dict;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.Contact;
import kz.kegoc.bln.entity.dict.translate.ContactTranslate;
import kz.kegoc.bln.translator.Translator;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

@Stateless
public class ContactTranslatorImpl implements Translator<Contact> {
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Contact translate(Contact entity, Lang lang) {
        entity.setLang(lang);

        if (entity.getTranslations()==null)
            return entity;

        ContactTranslate translate = entity.getTranslations().get(lang);
        if (translate==null)
            translate = entity.getTranslations().get(defLang);

        if (translate!=null) {
            entity.setPost(translate.getPost());
            entity.setDescription(translate.getDescription());
        }

        return entity;
    }

    @Inject
    private Lang defLang;
}

package kz.kegoc.bln.translator.dict;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.dict.PostAddress;
import kz.kegoc.bln.entity.dict.translate.PostAddressTranslate;
import kz.kegoc.bln.translator.Translator;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class PostAddressTranslatorImpl implements Translator<PostAddress> {
    public PostAddress translate(PostAddress entity, Lang lang) {
        entity.setLang(lang);

        if (entity.getTranslations()==null)
            return entity;

        PostAddressTranslate translate = entity.getTranslations().get(lang);
        if (translate==null)
            translate = entity.getTranslations().get(defLang);

        if (translate!=null) {
            entity.setName(translate.getName());
            entity.setLocality(translate.getLocality());
            entity.setStreet(translate.getStreet());
        }

        return entity;
    }

    @Inject
    private Lang defLang;
}

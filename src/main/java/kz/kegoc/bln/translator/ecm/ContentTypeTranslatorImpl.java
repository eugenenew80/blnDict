package kz.kegoc.bln.translator.ecm;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.ecm.ContentType;
import kz.kegoc.bln.translator.Translator;
import javax.ejb.Stateless;

@Stateless
public class ContentTypeTranslatorImpl implements Translator<ContentType> {
    public ContentType translate(ContentType entity, Lang lang) {
        return entity;
    }
}

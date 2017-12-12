package kz.kegoc.bln.translator.ecm;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.ecm.Content;
import kz.kegoc.bln.translator.Translator;

import javax.ejb.Stateless;

@Stateless
public class ContentTranslatorImpl implements Translator<Content> {
    public Content translate(Content entity, Lang lang) {
        return entity;
    }
}

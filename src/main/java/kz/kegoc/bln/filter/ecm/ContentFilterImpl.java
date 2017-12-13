package kz.kegoc.bln.filter.ecm;

import kz.kegoc.bln.webapi.filters.SessionContext;
import kz.kegoc.bln.entity.ecm.Content;
import kz.kegoc.bln.filter.AbstractFilter;
import kz.kegoc.bln.filter.Filter;

import javax.ejb.Stateless;

@Stateless
public class ContentFilterImpl extends AbstractFilter<Content> implements Filter<Content> {
    public Content filter(Content entity, SessionContext context) {
        return translate(prepare(entity, context), context);
    }

    private Content prepare(Content entity, SessionContext context) {
        entity = addUpdateInfo(entity, context);
        return entity;
    }

    private Content translate(Content entity, SessionContext context) {
        return entity;
    }
}

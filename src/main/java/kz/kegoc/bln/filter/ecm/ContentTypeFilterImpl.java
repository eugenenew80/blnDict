package kz.kegoc.bln.filter.ecm;

import kz.kegoc.bln.ejb.SessionContext;
import kz.kegoc.bln.entity.ecm.ContentType;
import kz.kegoc.bln.filter.AbstractFilter;
import kz.kegoc.bln.filter.Filter;
import javax.ejb.Stateless;

@Stateless
public class ContentTypeFilterImpl extends AbstractFilter<ContentType> implements Filter<ContentType> {
    public ContentType filter(ContentType entity, SessionContext context) {
        return translate(prepare(entity, context), context);
    }

    private ContentType prepare(ContentType entity, SessionContext context) {
        entity = addUpdateInfo(entity, context);
        return entity;
    }

    private ContentType translate(ContentType entity, SessionContext context) {
        return entity;
    }
}

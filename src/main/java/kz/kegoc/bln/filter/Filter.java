package kz.kegoc.bln.filter;

import kz.kegoc.bln.ejb.SessionContext;
import kz.kegoc.bln.entity.common.HasId;

public interface Filter<T extends HasId> {
    T filter(T entity, SessionContext context);
}

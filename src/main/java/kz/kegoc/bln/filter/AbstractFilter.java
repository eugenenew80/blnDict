package kz.kegoc.bln.filter;

import kz.kegoc.bln.webapi.filters.SessionContext;
import kz.kegoc.bln.entity.common.HasDates;
import kz.kegoc.bln.entity.common.HasId;
import kz.kegoc.bln.entity.common.HasUser;

import java.time.LocalDateTime;

public abstract class AbstractFilter<T extends HasId> implements Filter<T> {
    public abstract T filter(T entity, SessionContext context);

    protected <T extends HasId & HasDates & HasUser> T addUpdateInfo(T entity, SessionContext context) {
        if (entity.getId()==null) {
            entity.setCreateDate(LocalDateTime.now());
            entity.setCreateBy(context.getUser());
        }
        else {
            entity.setLastUpdateDate(LocalDateTime.now());
            entity.setLastUpdateBy(context.getUser());
        }

        return entity;
    }
}

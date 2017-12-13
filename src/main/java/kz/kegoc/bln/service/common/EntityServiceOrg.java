package kz.kegoc.bln.service.common;

import kz.kegoc.bln.entity.common.HasId;
import kz.kegoc.bln.webapi.filters.SessionContext;
import java.util.List;

public interface EntityServiceOrg<T extends HasId> extends EntityService<T> {
	List<T> findByOrg(SessionContext context);
}

package kz.kegoc.bln.service.dict;

import kz.kegoc.bln.entity.dict.MeteringPoint;
import kz.kegoc.bln.service.common.EntityService;
import kz.kegoc.bln.webapi.filters.SessionContext;

import javax.ejb.Local;
import java.util.List;

@Local
public interface MeteringPointService extends EntityService<MeteringPoint> {
    List<MeteringPoint> findEveryWhere(String value, SessionContext context);
}

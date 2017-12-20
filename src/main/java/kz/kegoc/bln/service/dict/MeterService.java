package kz.kegoc.bln.service.dict;

import kz.kegoc.bln.entity.dict.Meter;
import kz.kegoc.bln.service.common.EntityService;
import kz.kegoc.bln.webapi.filters.SessionContext;

import javax.ejb.Local;
import java.util.List;

@Local
public interface MeterService extends EntityService<Meter> {
    List<Meter> find(String code, String shortName, String name, String serialNumber, SessionContext context);
}

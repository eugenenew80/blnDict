package kz.kegoc.bln.service.dict;

import kz.kegoc.bln.ejb.SessionContext;
import kz.kegoc.bln.entity.dict.Reactor;
import kz.kegoc.bln.service.common.EntityService;
import javax.ejb.Local;
import java.util.List;

@Local
public interface ReactorService extends EntityService<Reactor> {
    List<Reactor> findByOrg(SessionContext context);
}

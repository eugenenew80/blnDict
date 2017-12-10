package kz.kegoc.bln.repository.dict;

import kz.kegoc.bln.entity.dict.Reactor;
import kz.kegoc.bln.repository.common.Repository;
import javax.ejb.Local;
import java.util.List;

@Local
public interface ReactorRepository extends Repository<Reactor> {
    List<Reactor> selectByOrg(List<Long> orgList);
}

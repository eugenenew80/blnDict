package kz.kegoc.bln.repository.dict;

import kz.kegoc.bln.entity.dict.Unit;
import kz.kegoc.bln.repository.common.JpaRepository;
import javax.ejb.Local;

@Local
public interface UnitRepository extends JpaRepository<Unit> {}

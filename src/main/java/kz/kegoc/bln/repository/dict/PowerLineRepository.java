package kz.kegoc.bln.repository.dict;

import kz.kegoc.bln.entity.dict.PowerLine;
import kz.kegoc.bln.repository.common.JpaRepository;
import javax.ejb.Local;

@Local
public interface PowerLineRepository extends JpaRepository<PowerLine> {}

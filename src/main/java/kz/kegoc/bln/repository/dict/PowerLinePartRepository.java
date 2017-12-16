package kz.kegoc.bln.repository.dict;

import kz.kegoc.bln.entity.dict.PowerLinePart;
import kz.kegoc.bln.repository.common.JpaRepository;
import javax.ejb.Local;

@Local
public interface PowerLinePartRepository extends JpaRepository<PowerLinePart> {}

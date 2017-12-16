package kz.kegoc.bln.repository.dict;

import kz.kegoc.bln.entity.dict.PowerLineType;
import kz.kegoc.bln.repository.common.JpaRepository;
import javax.ejb.Local;

@Local
public interface PowerLineTypeRepository extends JpaRepository<PowerLineType> {}

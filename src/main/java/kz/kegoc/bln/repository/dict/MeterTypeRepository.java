package kz.kegoc.bln.repository.dict;

import kz.kegoc.bln.entity.dict.MeterType;
import kz.kegoc.bln.repository.common.JpaRepository;
import javax.ejb.Local;

@Local
public interface MeterTypeRepository extends JpaRepository<MeterType> {}

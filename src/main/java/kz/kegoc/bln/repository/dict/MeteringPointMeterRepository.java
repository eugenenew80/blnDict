package kz.kegoc.bln.repository.dict;

import kz.kegoc.bln.entity.dict.MeteringPointMeter;
import kz.kegoc.bln.repository.common.JpaRepository;
import javax.ejb.Local;

@Local
public interface MeteringPointMeterRepository extends JpaRepository<MeteringPointMeter> {}

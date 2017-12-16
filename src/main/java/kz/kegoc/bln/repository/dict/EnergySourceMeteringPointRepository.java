package kz.kegoc.bln.repository.dict;

import kz.kegoc.bln.entity.dict.EnergySourceMeteringPoint;
import kz.kegoc.bln.repository.common.JpaRepository;
import javax.ejb.Local;

@Local
public interface EnergySourceMeteringPointRepository extends JpaRepository<EnergySourceMeteringPoint> {}

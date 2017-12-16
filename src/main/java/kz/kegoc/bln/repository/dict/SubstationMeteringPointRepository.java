package kz.kegoc.bln.repository.dict;

import kz.kegoc.bln.entity.dict.SubstationMeteringPoint;
import kz.kegoc.bln.repository.common.JpaRepository;
import javax.ejb.Local;

@Local
public interface SubstationMeteringPointRepository extends JpaRepository<SubstationMeteringPoint> {}

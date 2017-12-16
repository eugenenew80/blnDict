package kz.kegoc.bln.repository.dict;

import kz.kegoc.bln.entity.dict.MeteringPointVoltageTrans;
import kz.kegoc.bln.repository.common.JpaRepository;
import javax.ejb.Local;

@Local
public interface MeteringPointVoltageTransRepository extends JpaRepository<MeteringPointVoltageTrans> {}

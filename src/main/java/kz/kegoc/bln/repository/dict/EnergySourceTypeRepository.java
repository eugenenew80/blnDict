package kz.kegoc.bln.repository.dict;

import kz.kegoc.bln.entity.dict.EnergySourceType;
import kz.kegoc.bln.repository.common.JpaRepository;
import javax.ejb.Local;

@Local
public interface EnergySourceTypeRepository extends JpaRepository<EnergySourceType> {}

package kz.kegoc.bln.repository.dict;

import kz.kegoc.bln.entity.dict.EnergyZone;
import kz.kegoc.bln.repository.common.JpaRepository;
import javax.ejb.Local;

@Local
public interface EnergyZoneRepository extends JpaRepository<EnergyZone> {}

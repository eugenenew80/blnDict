package kz.kegoc.bln.repository.dict;

import kz.kegoc.bln.entity.dict.EnergyDistrict;
import kz.kegoc.bln.repository.common.JpaRepository;
import javax.ejb.Local;

@Local
public interface EnergyDistrictRepository extends JpaRepository<EnergyDistrict> {}

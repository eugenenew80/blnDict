package kz.kegoc.bln.repository.dict;

import kz.kegoc.bln.entity.dict.Region;
import kz.kegoc.bln.repository.common.JpaRepository;
import javax.ejb.Local;

@Local
public interface RegionRepository extends JpaRepository<Region> {}

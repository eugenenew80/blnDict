package kz.kegoc.bln.repository.dict;

import kz.kegoc.bln.entity.dict.EnergyNode;
import kz.kegoc.bln.repository.common.JpaRepository;

import javax.ejb.Local;

@Local
public interface EnergyNodeRepository extends JpaRepository<EnergyNode> {}

package kz.kegoc.bln.repository.dict;

import kz.kegoc.bln.entity.dict.Reactor;
import kz.kegoc.bln.repository.common.JpaRepository;
import javax.ejb.Local;

@Local
public interface ReactorRepository extends JpaRepository<Reactor> { }

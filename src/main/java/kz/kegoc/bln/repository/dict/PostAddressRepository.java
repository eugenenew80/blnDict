package kz.kegoc.bln.repository.dict;

import kz.kegoc.bln.entity.dict.PostAddress;
import kz.kegoc.bln.repository.common.JpaRepository;
import javax.ejb.Local;

@Local
public interface PostAddressRepository extends JpaRepository<PostAddress> {}

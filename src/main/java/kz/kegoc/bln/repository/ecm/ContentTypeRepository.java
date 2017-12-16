package kz.kegoc.bln.repository.ecm;

import kz.kegoc.bln.entity.ecm.ContentType;
import kz.kegoc.bln.repository.common.JpaRepository;
import javax.ejb.Local;

@Local
public interface ContentTypeRepository extends JpaRepository<ContentType> {}

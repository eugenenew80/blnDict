package kz.kegoc.bln.repository.ecm;

import kz.kegoc.bln.entity.ecm.Content;
import kz.kegoc.bln.repository.common.Repository;

import javax.ejb.Local;

@Local
public interface ContentRepository extends Repository<Content> {}

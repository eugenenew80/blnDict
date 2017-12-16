package kz.kegoc.bln.repository.dict;

import kz.kegoc.bln.entity.dict.Contact;
import kz.kegoc.bln.repository.common.JpaRepository;
import javax.ejb.Local;

@Local
public interface ContactRepository extends JpaRepository<Contact> {}

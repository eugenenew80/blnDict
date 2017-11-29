package kz.kegoc.bln.repository.dict;

import kz.kegoc.bln.entity.dict.Contact;
import kz.kegoc.bln.repository.common.Repository;

import javax.ejb.Local;

@Local
public interface ContactRepository extends Repository<Contact> {}

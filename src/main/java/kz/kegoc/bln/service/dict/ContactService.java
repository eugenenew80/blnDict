package kz.kegoc.bln.service.dict;

import kz.kegoc.bln.entity.dict.Contact;
import kz.kegoc.bln.service.common.EntityService;

import javax.ejb.Local;

@Local
public interface ContactService extends EntityService<Contact> {}

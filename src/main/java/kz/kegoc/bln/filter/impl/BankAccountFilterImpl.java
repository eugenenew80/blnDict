package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.entity.dict.BankAccount;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.BankAccountService;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class BankAccountFilterImpl implements Filter<BankAccount> {
    public BankAccount filter(BankAccount entity) {
        return prepare(entity);
    }

    private BankAccount prepare(BankAccount entity) {
        if (entity.getId()!=null) {
            BankAccount curEntity = service.findById(entity.getId());
            entity.setCreateDate(curEntity.getCreateDate());
            entity.setCreateBy(curEntity.getCreateBy());
        }

        return entity;
    }

    @Inject
    private BankAccountService service;
}

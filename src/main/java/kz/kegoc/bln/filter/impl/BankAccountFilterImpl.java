package kz.kegoc.bln.filter.impl;

import kz.kegoc.bln.entity.dict.BankAccount;
import kz.kegoc.bln.filter.Filter;
import javax.ejb.Stateless;

@Stateless
public class BankAccountFilterImpl implements Filter<BankAccount> {
    public BankAccount filter(BankAccount entity) {
        return translate(entity);
    }

    private BankAccount translate(BankAccount entity) {
        return entity;
    }
}

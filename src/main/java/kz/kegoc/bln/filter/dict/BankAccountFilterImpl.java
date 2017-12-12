package kz.kegoc.bln.filter.dict;

import kz.kegoc.bln.ejb.SessionContext;
import kz.kegoc.bln.entity.dict.BankAccount;
import kz.kegoc.bln.filter.AbstractFilter;
import kz.kegoc.bln.filter.Filter;
import kz.kegoc.bln.service.dict.BankAccountService;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class BankAccountFilterImpl extends AbstractFilter<BankAccount> implements Filter<BankAccount> {
    public BankAccount filter(BankAccount entity, SessionContext context) {
        return prepare(entity, context);
    }

    private BankAccount prepare(BankAccount entity, SessionContext context) {
        if (entity.getId()!=null) {
            BankAccount curEntity = service.findById(entity.getId(), context);
            entity.setCreateDate(curEntity.getCreateDate());
            entity.setCreateBy(curEntity.getCreateBy());
        }

        entity = addUpdateInfo(entity, context);
        return entity;
    }

    @Inject
    private BankAccountService service;
}

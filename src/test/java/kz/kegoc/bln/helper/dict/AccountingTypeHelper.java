package kz.kegoc.bln.helper.dict;

import kz.kegoc.bln.entity.dict.AccountingType;
import org.json.JSONException;
import org.json.JSONObject;
import static org.junit.Assert.*;

public class AccountingTypeHelper {
    public final static Long ACCOUNTING_TYPE_ID = 1L;
    public final static String ACCOUNTING_TYPE_CODE="01";
    public final static String ACCOUNTING_TYPE_NAME="Технический учёт";


    public static AccountingType newAccountingType() {
        AccountingType entity = new AccountingType();
        entity.setId(ACCOUNTING_TYPE_ID);
        entity.setName(ACCOUNTING_TYPE_NAME);
        entity.setCode(ACCOUNTING_TYPE_CODE);
        return entity;
    }

    public static AccountingType newAccountingType(Long id) {
        AccountingType entity = newAccountingType();
        entity.setId(id);
        return entity;
    }


    public static void assertAccountingType(AccountingType entity) {
        assertNotNull(entity);
        assertNotNull(entity.getId());
        assertTrue(entity.getId()>0);
        assertEquals(ACCOUNTING_TYPE_NAME, entity.getName());
        assertEquals(ACCOUNTING_TYPE_CODE, entity.getCode());
    }

    public static void assertAccountingType(AccountingType entity1, AccountingType entity2) {
        assertNotNull(entity1);
        assertNotNull(entity1.getId());
        assertTrue(entity1.getId()>0);

        assertNotNull(entity2);
        assertNotNull(entity2.getId());
        assertTrue(entity2.getId()>0);

        assertEquals(entity1.getId(), entity2.getId());
        assertEquals(entity1.getName(), entity2.getName());
        assertEquals(entity1.getCode(), entity2.getCode() );
    }


    public static String accountTypeToJson(AccountingType entity) {
        JSONObject body = new JSONObject();

        try {
            if (entity.getId()!=null)
                body.put("id", entity.getId());

            if (entity.getName()!=null)
                body.put("name", entity.getName());

            if (entity.getCode()!=null)
                body.put("code", entity.getCode());
        }
        catch (JSONException e) {}

        return body.toString();
    }

}

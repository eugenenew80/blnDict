package kz.kegoc.bln.helper.dict;

import kz.kegoc.bln.entity.dict.Company;
import kz.kegoc.bln.entity.dict.Unit;
import org.json.JSONException;
import org.json.JSONObject;

import static org.junit.Assert.*;

public class CompanyHelper {
    public final static Long COMPANY_ID = 1L;
    public final static String COMPANY_CODE="0001";
    public final static String COMPANY_NAME="ТОО \"АРЭК-Энергосбыт\"";
    public final static String COMPANY_TIN="040840000124";


    public static Company newCompany() {
        Company entity = new Company();
        entity.setId(COMPANY_ID);
        entity.setName(COMPANY_NAME);
        entity.setCode(COMPANY_CODE);
        entity.setTin(COMPANY_TIN);

        return entity;
    }

    public static Company newCompany(Long id) {
        Company entity= newCompany();
        entity.setId(id);
        return entity;
    }


    public static void assertCompany(Company entity) {
        assertNotNull(entity);
        assertNotNull(entity.getId());
        assertTrue(entity.getId()>0);
        assertEquals(COMPANY_NAME, entity.getName());
        assertEquals(COMPANY_CODE, entity.getCode());
        assertEquals(COMPANY_TIN, entity.getTin());
    }

    public static void assertCompany(Company entity1, Company entity2) {
        assertNotNull(entity1);
        assertNotNull(entity1.getId());
        assertTrue(entity1.getId()>0);

        assertNotNull(entity2);
        assertNotNull(entity2.getId());
        assertTrue(entity2.getId()>0);

        assertEquals(entity1.getId(), entity2.getId());
        assertEquals(entity1.getName(), entity2.getName());
        assertEquals(entity1.getCode(), entity2.getCode() );
        assertEquals(entity1.getTin(), entity2.getTin() );
    }


    public static String companyToJson(Company entity) {
        JSONObject body = new JSONObject();

        try {
            if (entity.getId()!=null)
                body.put("id", entity.getId());

            if (entity.getName()!=null)
                body.put("name", entity.getName());

            if (entity.getCode()!=null)
                body.put("code", entity.getCode());

            if (entity.getTin()!=null)
                body.put("tin", entity.getTin());
        }
        catch (JSONException e) {}

        return body.toString();
    }

}

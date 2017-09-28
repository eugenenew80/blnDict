package kz.kegoc.bln.helper.dict;

import kz.kegoc.bln.entity.dict.Unit;
import org.json.JSONException;
import org.json.JSONObject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class UnitHelper {
    public final static Long UNIT_ID = 1L;
    public final static String UNIT_CODE="А";
    public final static String UNIT_NAME="ампер";


    public static Unit newUnit() {
        Unit entity = new Unit();
        entity.setId(UNIT_ID);
        entity.setName(UNIT_NAME);
        entity.setCode(UNIT_CODE);
        return entity;
    }

    public static Unit newUnit(Long id) {
        Unit entity = newUnit();
        entity.setId(id);
        return entity;
    }


    public static void assertUnit(Unit entity) {
        assertNotNull(entity);
        assertNotNull(entity.getId());
        assertTrue(entity.getId()>0);
        assertEquals(UNIT_NAME, entity.getName());
        assertEquals(UNIT_CODE, entity.getCode());
    }

    public static void assertUnit(Unit entity1, Unit entity2) {
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


    public static String unitToJson(Unit entity) {
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

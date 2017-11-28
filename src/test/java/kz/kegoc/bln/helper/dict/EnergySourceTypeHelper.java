package kz.kegoc.bln.helper.dict;

import kz.kegoc.bln.entity.dict.EnergySourceType;
import org.json.JSONException;
import org.json.JSONObject;

import static org.junit.Assert.*;

public class EnergySourceTypeHelper {
    public final static Long ENERGY_SOURCE_TYPE_ID = 1L;
    public final static String ENERGY_SOURCE_TYPE_NAME="Атомная электростанция";
    public final static String ENERGY_SOURCE_TYPE_SHORT_NAME="АЭС";
    
    public static EnergySourceType newEnergySourceType() {
        EnergySourceType entity = new EnergySourceType();
        entity.setId(ENERGY_SOURCE_TYPE_ID);
        entity.setName(ENERGY_SOURCE_TYPE_NAME);
        entity.setShortName(ENERGY_SOURCE_TYPE_SHORT_NAME);
        return entity;
    }

    public static EnergySourceType newEnergySourceType(Long id) {
        EnergySourceType entity = newEnergySourceType();
        entity.setId(id);
        return entity;
    }


    public static void assertEnergySourceType(EnergySourceType entity) {
        assertNotNull(entity);
        assertNotNull(entity.getId());
        assertTrue(entity.getId()>0);
        assertEquals(ENERGY_SOURCE_TYPE_NAME, entity.getName());
        assertEquals(ENERGY_SOURCE_TYPE_SHORT_NAME, entity.getShortName());
    }

    public static void assertEnergySourceType(EnergySourceType entity1, EnergySourceType entity2) {
        assertNotNull(entity1);
        assertNotNull(entity1.getId());
        assertTrue(entity1.getId()>0);

        assertNotNull(entity2);
        assertNotNull(entity2.getId());
        assertTrue(entity2.getId()>0);

        assertEquals(entity1.getId(), entity2.getId());
        assertEquals(entity1.getName(), entity2.getName());
        assertEquals(entity1.getShortName(), entity2.getShortName());
    }


    public static String energySourceTypeToJson(EnergySourceType entity) {
        JSONObject body = new JSONObject();

        try {
            if (entity.getId()!=null)
                body.put("id", entity.getId());

            if (entity.getName()!=null)
                body.put("name", entity.getName());

            if (entity.getShortName()!=null)
                body.put("shortName", entity.getShortName());
        }
        catch (JSONException e) {}

        return body.toString();
    }
}

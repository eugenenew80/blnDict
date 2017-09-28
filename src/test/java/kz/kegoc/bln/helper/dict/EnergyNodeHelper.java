package kz.kegoc.bln.helper.dict;

import kz.kegoc.bln.entity.dict.EnergyNode;
import org.json.JSONException;
import org.json.JSONObject;

import static org.junit.Assert.*;

public class EnergyNodeHelper {
    public final static Long ENERGY_NODE_ID = 1L;
    public final static String ENERGY_NODE_CODE="Кар.ЭУ";
    public final static String ENERGY_NODE_NAME="Карагандинский энергоузел";


    public static EnergyNode newEnergyNode() {
        EnergyNode entity = new EnergyNode();
        entity.setId(ENERGY_NODE_ID);
        entity.setName(ENERGY_NODE_NAME);
        entity.setCode(ENERGY_NODE_CODE);
        return entity;
    }

    public static EnergyNode newEnergyNode(Long id) {
        EnergyNode entity = newEnergyNode();
        entity.setId(id);
        return entity;
    }


    public static void assertEnergyNode(EnergyNode entity) {
        assertNotNull(entity);
        assertNotNull(entity.getId());
        assertTrue(entity.getId()>0);
        assertEquals(ENERGY_NODE_NAME, entity.getName());
        assertEquals(ENERGY_NODE_CODE, entity.getCode());
    }

    public static void assertEnergyNode(EnergyNode entity1, EnergyNode entity2) {
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


    public static String energyNodeToJson(EnergyNode entity) {
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

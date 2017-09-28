package kz.kegoc.bln.helper.dict;

import kz.kegoc.bln.entity.dict.DataSource;
import org.json.JSONException;
import org.json.JSONObject;

import static org.junit.Assert.*;

public class DataSourceHelper {
    public final static Long DATA_SOURCE_ID = 1L;
    public final static String DATA_SOURCE_CODE="KEGOC_EMCOS";
    public final static String DATA_SOURCE_NAME="АСКУЭ ЕМКОС";


    public static DataSource newDataSource() {
        DataSource entity = new DataSource();
        entity.setId(DATA_SOURCE_ID);
        entity.setName(DATA_SOURCE_NAME);
        entity.setCode(DATA_SOURCE_CODE);
        return entity;
    }

    public static DataSource newDataSource(Long id) {
        DataSource entity = newDataSource();
        entity.setId(id);
        return entity;
    }


    public static void assertDataSource(DataSource entity) {
        assertNotNull(entity);
        assertNotNull(entity.getId());
        assertTrue(entity.getId()>0);
        assertEquals(DATA_SOURCE_NAME, entity.getName());
        assertEquals(DATA_SOURCE_CODE, entity.getCode());
    }

    public static void assertDataSource(DataSource entity1, DataSource entity2) {
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


    public static String dataSourceToJson(DataSource entity) {
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

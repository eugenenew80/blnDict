package kz.kegoc.bln.repository.dict;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import java.util.*;
import org.junit.*;

import static kz.kegoc.bln.helper.dict.UnitHelper.*;
import kz.kegoc.bln.repository.dict.impl.UnitRepositoryImpl;
import kz.kegoc.bln.repository.common.query.*;
import kz.kegoc.bln.entity.dict.Unit;
import kz.kegoc.bln.helper.*;



public class UnitRepositoryTest {
    private static final DbHelper dbHelper = new DbHelper();
    private static final UnitRepository repository = new UnitRepositoryImpl(dbHelper.getEntityManager());;
	private static final List<DataSetLoader> dataSetList = Arrays.asList(new DataSetLoader("apps", "dicts.xml"));

	private static final Long nonExistedId = 3l;
	private static final String nonExistedCode = "123";
	private static final String nonExistedName = "Бла бла бла";


	@BeforeClass
	public static void setUpClass()  {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}
		
	@AfterClass
	public static void tearDownClass()  {
        dbHelper.closeDatabase();
	}
	
	
	@Before
	public void setUp() throws Exception {
        dbHelper.beginTransaction();
        dbHelper.cleanAndInsert(dataSetList);
	}
	
	@After
	public void tearDown() {
        dbHelper.commitTransaction();
	}


	//Success cases

	@Test
	public void theListUnitsMayBeSelected() {
		List<Unit> list = repository.selectAll();
		
		assertThat(list, is(not(nullValue())));
		assertThat(list, hasSize(2));
		
		assertThat(list.get(0), is(not(nullValue())));
        assertThat(list.get(0).getId(), is(not(nullValue())));
		assertUnit(list.get(0));

		assertThat(list.get(1), is(not(nullValue())));
		assertThat(list.get(1).getId(), is(not(nullValue())));
	}
	
	
	@Test
	public void theListUnitsMayBeSelectedByQuery() {
		Query query = QueryImpl.builder()
			.setParameter("name", new MyQueryParam("name", UNIT_NAME, ConditionType.LIKE))
			.build();		
		
		List<Unit> list = repository.select(query);
		assertThat(list, is(not(nullValue())));
		assertThat(list, hasSize(1));
		assertThat(list.get(0), is(not(nullValue())));
		assertThat(list.get(0).getId(), is(not(nullValue())));
		assertUnit(list.get(0));

		query = QueryImpl.builder().build();
        list = repository.select(query);
		assertThat(list, is(not(nullValue())));
		assertThat(list, hasSize(2));
	}	
	
	
	@Test
	public void existingUnitMayBeSelectedById() {
		Unit entity = repository.selectById(UNIT_ID);
		assertThat(entity, is(not(nullValue())));
		assertUnit(entity);
	}


	@Test
	public void newUnitMayBeInserted() {
		Unit origEntity = newUnit(null);

		Unit entity = repository.insert(origEntity);
		assertThat(entity.getId(), is(not(nullValue())));

        entity = repository.selectById(entity.getId());
		assertThat(entity, is(not(nullValue())));
		assertUnit(entity);
	}


	@Test
	public void existingUnitMayBeUpdated() {
		Unit origEntity = repository.selectById(UNIT_ID);;
        origEntity.setName(origEntity.getName() + " (нов)");

		Unit entity = repository.update(origEntity);
		assertThat(entity, is(not(nullValue())));

        entity = repository.selectById(entity.getId());
		assertUnit(origEntity, entity);
	}


	@Test
	public void existingUnitMayBeDeleted() {
		boolean result = repository.delete(UNIT_ID);
        assertThat(result, is(equalTo(true)));

        Unit entity = repository.selectById(UNIT_ID);
        assertThat(entity, is(nullValue()));
    }



    //Fail cases

    @Test
    public void methodSelectByIdShouldReturnNullIfIdIsNotExists() {
        Unit entity = repository.selectById(nonExistedId);
        assertThat(entity, is(nullValue()));
    }

    @Test
    public void methodDeleteShouldReturnFalseIfIdIsNotExists() {
        boolean result = repository.delete(nonExistedId);
        assertThat(result, is(equalTo(false)));
    }
}

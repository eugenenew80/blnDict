package kz.kegoc.bln.repository.dict;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;

import static kz.kegoc.bln.helper.dict.EnergySourceTypeHelper.*;
import kz.kegoc.bln.entity.dict.EnergySourceType;
import kz.kegoc.bln.helper.*;
import kz.kegoc.bln.repository.common.query.*;
import kz.kegoc.bln.repository.dict.impl.EnergySourceTypeRepositoryImpl;


public class EnergySourceTypeRepositoryTest {
    private static final DbHelper dbHelper = new DbHelper();
    private static final EnergySourceTypeRepository repository = new EnergySourceTypeRepositoryImpl(dbHelper.getEntityManager());;
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
	public void theListEnergySourceTypesMayBeSelected() {
		List<EnergySourceType> list = repository.selectAll();
		
		assertThat(list, is(not(nullValue())));
		assertThat(list, hasSize(2));
		
		assertThat(list.get(0), is(not(nullValue())));
        assertThat(list.get(0).getId(), is(not(nullValue())));
		assertEnergySourceType(list.get(0));

		assertThat(list.get(1), is(not(nullValue())));
		assertThat(list.get(1).getId(), is(not(nullValue())));
	}
	
	
	@Test
	public void theListEnergySourceTypesMayBeSelectedByQuery() {
		Query query = QueryImpl.builder()
			.setParameter("name", new MyQueryParam("name", ENERGY_SOURCE_TYPE_NAME, ConditionType.LIKE))
			.build();		
		
		List<EnergySourceType> list = repository.select(query);
		assertThat(list, is(not(nullValue())));
		assertThat(list, hasSize(1));
		assertThat(list.get(0), is(not(nullValue())));
		assertThat(list.get(0).getId(), is(not(nullValue())));
		assertEnergySourceType(list.get(0));

		query = QueryImpl.builder().build();
        list = repository.select(query);
		assertThat(list, is(not(nullValue())));
		assertThat(list, hasSize(2));
	}	
	
	
	@Test
	public void existingEnergySourceTypeMayBeSelectedById() {
		EnergySourceType entity = repository.selectById(ENERGY_SOURCE_TYPE_ID);
		assertThat(entity, is(not(nullValue())));
		assertEnergySourceType(entity);
	}


	@Test
	public void existingEnergySourceTypeMayBeSelectedByName()  {
		EnergySourceType entity = repository.selectByName(ENERGY_SOURCE_TYPE_NAME);
		assertThat(entity, is(not(nullValue())));
		assertEnergySourceType(entity);
	}




	@Test
	public void newEnergySourceTypeMayBeInserted() {
		EnergySourceType origEntity = newEnergySourceType(null);

		EnergySourceType entity = repository.insert(origEntity);
		assertThat(entity.getId(), is(not(nullValue())));

        entity = repository.selectById(entity.getId());
		assertThat(entity, is(not(nullValue())));
		assertEnergySourceType(entity);
	}


	@Test
	public void existingEnergySourceTypeMayBeUpdated() {
		EnergySourceType origEntity = repository.selectById(ENERGY_SOURCE_TYPE_ID);;
        origEntity.setName(origEntity.getName() + " (нов)");

		EnergySourceType entity = repository.update(origEntity);
		assertThat(entity, is(not(nullValue())));

        entity = repository.selectById(entity.getId());
		assertEnergySourceType(origEntity, entity);
	}


	@Test
	public void existingEnergySourceTypeMayBeDeleted() {
		boolean result = repository.delete(ENERGY_SOURCE_TYPE_ID);
        assertThat(result, is(equalTo(true)));

        EnergySourceType entity = repository.selectById(ENERGY_SOURCE_TYPE_ID);
        assertThat(entity, is(nullValue()));
    }



    //Fail cases

    @Test
    public void methodSelectByIdShouldReturnNullIfIdIsNotExists() {
        EnergySourceType entity = repository.selectById(nonExistedId);
        assertThat(entity, is(nullValue()));
    }

    @Test
    public void methodSelectByNameShouldReturnNullIfNameIsNotExists() {
        EnergySourceType entity = repository.selectByName(nonExistedName);
        assertThat(entity, is(nullValue()));
    }

    @Test
    public void methodSelectByCodeShouldReturnNullIfCodeIsNotExists() {
        EnergySourceType entity = repository.selectByCode(nonExistedCode);
        assertThat(entity, is(nullValue()));
    }

    @Test
    public void methodDeleteShouldReturnFalseIfIdIsNotExists() {
        boolean result = repository.delete(nonExistedId);
        assertThat(result, is(equalTo(false)));
    }
}

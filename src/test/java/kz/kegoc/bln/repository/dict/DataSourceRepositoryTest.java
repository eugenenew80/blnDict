package kz.kegoc.bln.repository.dict;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import java.util.*;
import org.junit.*;

import static kz.kegoc.bln.helper.dict.DataSourceHelper.*;
import kz.kegoc.bln.entity.dict.DataSource;
import kz.kegoc.bln.helper.DataSetLoader;
import kz.kegoc.bln.helper.DbHelper;
import kz.kegoc.bln.repository.common.query.*;
import kz.kegoc.bln.repository.dict.impl.DataSourceRepositoryImpl;


public class DataSourceRepositoryTest {
    private static final DbHelper dbHelper = new DbHelper();
    private static final DataSourceRepository repository = new DataSourceRepositoryImpl(dbHelper.getEntityManager());;
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
	public void theListDataSourcesMayBeSelected() {
		List<DataSource> list = repository.selectAll();
		
		assertThat(list, is(not(nullValue())));
		assertThat(list, hasSize(2));
		
		assertThat(list.get(0), is(not(nullValue())));
        assertThat(list.get(0).getId(), is(not(nullValue())));
		assertDataSource(list.get(0));

		assertThat(list.get(1), is(not(nullValue())));
		assertThat(list.get(1).getId(), is(not(nullValue())));
	}
	
	
	@Test
	public void theListDataSourcesMayBeSelectedByQuery() {
		Query query = QueryImpl.builder()
			.setParameter("name", new MyQueryParam("name", DATA_SOURCE_NAME, ConditionType.LIKE))
			.build();		
		
		List<DataSource> list = repository.select(query);
		assertThat(list, is(not(nullValue())));
		assertThat(list, hasSize(1));
		assertThat(list.get(0), is(not(nullValue())));
		assertThat(list.get(0).getId(), is(not(nullValue())));
		assertDataSource(list.get(0));

		query = QueryImpl.builder().build();
        list = repository.select(query);
		assertThat(list, is(not(nullValue())));
		assertThat(list, hasSize(2));
	}	
	
	
	@Test
	public void existingDataSourceMayBeSelectedById() {
		DataSource entity = repository.selectById(DATA_SOURCE_ID);
		assertThat(entity, is(not(nullValue())));
		assertDataSource(entity);
	}


	@Test
	public void newDataSourceMayBeInserted() {
		DataSource origEntity = newDataSource(null);

		DataSource entity = repository.insert(origEntity);
		assertThat(entity.getId(), is(not(nullValue())));

        entity = repository.selectById(entity.getId());
		assertThat(entity, is(not(nullValue())));
		assertDataSource(entity);
	}


	@Test
	public void existingDataSourceMayBeUpdated() {
		DataSource origEntity = repository.selectById(DATA_SOURCE_ID);;
        origEntity.setName(origEntity.getName() + " (нов)");

		DataSource entity = repository.update(origEntity);
		assertThat(entity, is(not(nullValue())));

        entity = repository.selectById(entity.getId());
		assertDataSource(origEntity, entity);
	}


	@Test
	public void existingDataSourceMayBeDeleted() {
		boolean result = repository.delete(DATA_SOURCE_ID);
        assertThat(result, is(equalTo(true)));

        DataSource entity = repository.selectById(DATA_SOURCE_ID);
        assertThat(entity, is(nullValue()));
    }



    //Fail cases

    @Test
    public void methodSelectByIdShouldReturnNullIfIdIsNotExists() {
        DataSource entity = repository.selectById(nonExistedId);
        assertThat(entity, is(nullValue()));
    }

    @Test
    public void methodDeleteShouldReturnFalseIfIdIsNotExists() {
        boolean result = repository.delete(nonExistedId);
        assertThat(result, is(equalTo(false)));
    }
}

package kz.kegoc.bln.repository.dict;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import org.junit.*;
import java.util.*;

import static kz.kegoc.bln.helper.dict.AccountingTypeHelper.*;
import kz.kegoc.bln.entity.dict.AccountingType;
import kz.kegoc.bln.helper.*;
import kz.kegoc.bln.repository.common.query.*;
import kz.kegoc.bln.repository.dict.impl.AccountingTypeRepositoryImpl;


public class AccountingTypeRepositoryTest {
    private static final DbHelper dbHelper = new DbHelper();
    private static final AccountingTypeRepository repository = new AccountingTypeRepositoryImpl(dbHelper.getEntityManager());;
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
	public void theListAccountingTypesMayBeSelected() {
		List<AccountingType> list = repository.selectAll();
		
		assertThat(list, is(not(nullValue())));
		assertThat(list, hasSize(2));
		
		assertThat(list.get(0), is(not(nullValue())));
        assertThat(list.get(0).getId(), is(not(nullValue())));
		assertAccountingType(list.get(0));

		assertThat(list.get(1), is(not(nullValue())));
		assertThat(list.get(1).getId(), is(not(nullValue())));
	}
	
	
	@Test
	public void theListAccountingTypesMayBeSelectedByQuery() {
		Query query = QueryImpl.builder()
			.setParameter("name", new MyQueryParam("name", ACCOUNTING_TYPE_NAME, ConditionType.LIKE))
			.build();		
		
		List<AccountingType> list = repository.select(query);
		assertThat(list, is(not(nullValue())));
		assertThat(list, hasSize(1));
		assertThat(list.get(0), is(not(nullValue())));
		assertThat(list.get(0).getId(), is(not(nullValue())));
		assertAccountingType(list.get(0));

		query = QueryImpl.builder().build();
        list = repository.select(query);
		assertThat(list, is(not(nullValue())));
		assertThat(list, hasSize(2));
	}	
	
	
	@Test
	public void existingAccountingTypeMayBeSelectedById() {
		AccountingType entity = repository.selectById(ACCOUNTING_TYPE_ID);
		assertThat(entity, is(not(nullValue())));
		assertAccountingType(entity);
	}


	@Test
	public void newAccountingTypeMayBeInserted() {
		AccountingType origEntity = newAccountingType(null);

		AccountingType entity = repository.insert(origEntity);
		assertThat(entity.getId(), is(not(nullValue())));

        entity = repository.selectById(entity.getId());
		assertThat(entity, is(not(nullValue())));
		assertAccountingType(entity);
	}


	@Test
	public void existingAccountingTypeMayBeUpdated() {
		AccountingType origEntity = repository.selectById(ACCOUNTING_TYPE_ID);;
        origEntity.setName(origEntity.getName() + " (нов)");

		AccountingType entity = repository.update(origEntity);
		assertThat(entity, is(not(nullValue())));

        entity = repository.selectById(entity.getId());
		assertAccountingType(origEntity, entity);
	}


	@Test
	public void existingAccountingTypeMayBeDeleted() {
		boolean result = repository.delete(ACCOUNTING_TYPE_ID);
        assertThat(result, is(equalTo(true)));

        AccountingType entity = repository.selectById(ACCOUNTING_TYPE_ID);
        assertThat(entity, is(nullValue()));
    }



    //Fail cases

    @Test
    public void methodSelectByIdShouldReturnNullIfIdIsNotExists() {
        AccountingType entity = repository.selectById(nonExistedId);
        assertThat(entity, is(nullValue()));
    }

    @Test
    public void methodDeleteShouldReturnFalseIfIdIsNotExists() {
        boolean result = repository.delete(nonExistedId);
        assertThat(result, is(equalTo(false)));
    }
}

package kz.kegoc.bln.repository.dict;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import kz.kegoc.bln.entity.dict.Organization;
import org.junit.*;
import java.util.*;

import static kz.kegoc.bln.helper.dict.CompanyHelper.*;

import kz.kegoc.bln.helper.*;
import kz.kegoc.bln.repository.common.query.*;
import kz.kegoc.bln.repository.dict.impl.CompanyRepositoryImpl;

public class CompanyRepositoryTest {
    private static final DbHelper dbHelper = new DbHelper();
    private static final CompanyRepository repository = new CompanyRepositoryImpl(dbHelper.getEntityManager());;
	private static final List<DataSetLoader> dataSetList = Arrays.asList(new DataSetLoader("apps", "dicts.xml"));

	private static final Long nonExistedId = 3l;
	private static final String nonExistedCode = "123";
	private static final String nonExistedName = "Бла бла бла";
	private static final String nonExistedTin = "999999999999";


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
	public void theListCompanysMayBeSelected() {
		List<Organization> list = repository.selectAll();
		
		assertThat(list, is(not(nullValue())));
		assertThat(list, hasSize(2));
		
		assertThat(list.get(0), is(not(nullValue())));
        assertThat(list.get(0).getId(), is(not(nullValue())));
		assertCompany(list.get(0));

		assertThat(list.get(1), is(not(nullValue())));
		assertThat(list.get(1).getId(), is(not(nullValue())));
	}
	
	
	@Test
	public void theListCompanysMayBeSelectedByQuery() {
		Query query = QueryImpl.builder()
			.setParameter("name", new MyQueryParam("name", COMPANY_NAME, ConditionType.LIKE))
			.build();		
		
		List<Organization> list = repository.select(query);
		assertThat(list, is(not(nullValue())));
		assertThat(list, hasSize(1));
		assertThat(list.get(0), is(not(nullValue())));
		assertThat(list.get(0).getId(), is(not(nullValue())));
		assertCompany(list.get(0));

		query = QueryImpl.builder().build();
        list = repository.select(query);
		assertThat(list, is(not(nullValue())));
		assertThat(list, hasSize(2));
	}	
	
	
	@Test
	public void existingCompanyMayBeSelectedById() {
		Organization entity = repository.selectById(COMPANY_ID);
		assertThat(entity, is(not(nullValue())));
		assertCompany(entity);
	}


	@Test
	public void existingCompanyMayBeSelectedByName()  {
		Organization entity = repository.selectByName(COMPANY_NAME);
		assertThat(entity, is(not(nullValue())));
		assertCompany(entity);
	}

	@Test
	public void existingCompanyMayBeSelectedByCode() {
		Organization entity = repository.selectByCode(COMPANY_CODE);
		assertThat(entity, is(not(nullValue())));
		assertCompany(entity);
	}


	@Test
	public void existingCompanyMayBeSelectedByTin() {
		Organization entity = repository.selectByTin(COMPANY_TIN);
		assertThat(entity, is(not(nullValue())));
		assertCompany(entity);
	}


	@Test
	public void newCompanyMayBeInserted() {
		Organization origEntity = newCompany(null);

		Organization entity = repository.insert(origEntity);
		assertThat(entity.getId(), is(not(nullValue())));

        entity = repository.selectById(entity.getId());
		assertThat(entity, is(not(nullValue())));
		assertCompany(entity);
	}


	@Test
	public void existingCompanyMayBeUpdated() {
		Organization origEntity = repository.selectById(COMPANY_ID);;
        origEntity.setName(origEntity.getName() + " (нов)");

		Organization entity = repository.update(origEntity);
		assertThat(entity, is(not(nullValue())));

        entity = repository.selectById(entity.getId());
		assertCompany(origEntity, entity);
	}


	@Test
	public void existingCompanyMayBeDeleted() {
		boolean result = repository.delete(COMPANY_ID);
        assertThat(result, is(equalTo(true)));

        Organization entity = repository.selectById(COMPANY_ID);
        assertThat(entity, is(nullValue()));
    }



    //Fail cases

    @Test
    public void methodSelectByIdShouldReturnNullIfIdIsNotExists() {
        Organization entity = repository.selectById(nonExistedId);
        assertThat(entity, is(nullValue()));
    }

    @Test
    public void methodSelectByNameShouldReturnNullIfNameIsNotExists() {
        Organization entity = repository.selectByName(nonExistedName);
        assertThat(entity, is(nullValue()));
    }

    @Test
    public void methodSelectByCodeShouldReturnNullIfCodeIsNotExists() {
        Organization entity = repository.selectByCode(nonExistedCode);
        assertThat(entity, is(nullValue()));
    }

	@Test
	public void methodSelectByTinShouldReturnNullIfCodeIsNotExists() {
		Organization entity = repository.selectByTin(nonExistedTin);
		assertThat(entity, is(nullValue()));
	}

    @Test
    public void methodDeleteShouldReturnFalseIfIdIsNotExists() {
        boolean result = repository.delete(nonExistedId);
        assertThat(result, is(equalTo(false)));
    }
}

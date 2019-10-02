package by.senla.study.board.service.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.jupiter.api.Test;

import by.senla.study.board.model.entity.PersonalData;

public class RersonalDataServiceTest extends AbstractTest {

	@Test
	public void testCreate() {
		PersonalData entity = createPersonalDataEntity();
		
		PersonalData entityDb = personalDataService.getById(entity.getId());
		
		assertNotNull(entityDb);
		assertEquals(entity.getLogin(), entityDb.getLogin());
		assertEquals(entity.getPassword(), entityDb.getPassword());
		assertEquals(entity.getRole(), entityDb.getRole());
		assertEquals(entity.getUserAccount().getId().intValue(), entityDb.getUserAccount().getId().intValue());
		assertNotNull(entityDb.getId());
		assertNotNull(entityDb.getCreated());
		assertNotNull(entityDb.getUpdated());
		assertTrue(entityDb.getCreated().equals(entityDb.getUpdated()));
	}
	
	@Test
	public void testUpdate() throws InterruptedException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		PersonalData entity = createPersonalDataEntity();

		String newLogin = entity.getLogin() + "_updated";
		entity.setLogin(newLogin);
		Thread.sleep(TIME);
		personalDataService.update(entity);

		PersonalData entityDb = personalDataService.getById(entity.getId());

		assertNotNull(entityDb);
		assertEquals(entity.getLogin(), entityDb.getLogin());
		assertEquals(entity.getPassword(), entityDb.getPassword());
		assertEquals(entity.getRole(), entityDb.getRole());
		assertEquals(entity.getUserAccount().getId().intValue(), entityDb.getUserAccount().getId().intValue());
		assertNotNull(entityDb.getId());
		assertNotNull(entityDb.getCreated());
		assertNotNull(entityDb.getUpdated());
		//assertEquals(dateFormat.format(entity.getCreated()), dateFormat.format(entityDb.getCreated()));
		assertTrue(entityDb.getUpdated().after(entity.getCreated()));
	}
	
	@Test
	public void testDelete() {
		PersonalData entity = createPersonalDataEntity();
		personalDataService.deleteById(entity.getId());
		assertNull(personalDataService.getById(entity.getId()));
	}
	
	@Test
	public void testSellectAll() {
		int entityCount = personalDataService.selectAll().size();

		int randomObjectsCount = getRandomInt();
		for (int i = 0; i < randomObjectsCount; i++) {
			createPersonalDataEntity();
		}

		List<PersonalData> allEntities = personalDataService.selectAll();
		for (PersonalData entityDb : allEntities) {
			assertNotNull(entityDb.getLogin());
			assertNotNull(entityDb.getPassword());
			assertNotNull(entityDb.getRole());
			assertNotNull(entityDb.getUserAccount());
			assertNotNull(entityDb.getId());
			assertNotNull(entityDb.getCreated());
			assertNotNull(entityDb.getUpdated());
		}
		assertEquals(randomObjectsCount + entityCount, allEntities.size());
	}
}

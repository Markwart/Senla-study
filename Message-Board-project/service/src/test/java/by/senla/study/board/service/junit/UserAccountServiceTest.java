package by.senla.study.board.service.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.jupiter.api.Test;

import by.senla.study.board.model.entity.PersonalData;
import by.senla.study.board.model.entity.UserAccount;
import by.senla.study.board.model.enums.Roles;

public class UserAccountServiceTest extends AbstractTest {

	@Test
	public void testCreate() {
		UserAccount entity = createUserAccountEntity();
		
		UserAccount entityDb = userAccountService.getById(entity.getId());
		
		assertNotNull(entityDb);
		assertEquals(entity.getName(), entityDb.getName());
		assertEquals(entity.getEmail(), entityDb.getEmail());
		assertNotNull(entityDb.getId());
		assertNotNull(entityDb.getCreated());
		assertNotNull(entityDb.getUpdated());
		assertTrue(entityDb.getCreated().equals(entityDb.getUpdated()));
	}
	
	@Test
	public void testUpdate() throws InterruptedException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		UserAccount entity = createUserAccountEntity();

		String newName = entity.getName() + "_updated";
		entity.setName(newName);
		Thread.sleep(TIME);
		userAccountService.update(entity);

		UserAccount entityDb = userAccountService.getById(entity.getId());

		assertNotNull(entityDb);
		assertEquals(entity.getName(), entityDb.getName());
		assertEquals(entity.getEmail(), entityDb.getEmail());
		assertNotNull(entityDb.getId());
		assertNotNull(entityDb.getCreated());
		assertNotNull(entityDb.getUpdated());
		//assertEquals(dateFormat.format(entity.getCreated()), dateFormat.format(entityDb.getCreated()));
		assertTrue(entityDb.getUpdated().after(entity.getCreated()));
	}
	
	@Test
	public void testDelete() {
		UserAccount entity = createUserAccountEntity();
		userAccountService.deleteById(entity.getId());
		assertNull(userAccountService.getById(entity.getId()));
	}
	
	@Test
	public void testSellectAll() {
		int entityCount = userAccountService.selectAll().size();

		int randomObjectsCount = getRandomInt();
		for (int i = 0; i < randomObjectsCount; i++) {
			createUserAccountEntity();
		}

		List<UserAccount> allEntities = userAccountService.selectAll();
		for (UserAccount entityDb : allEntities) {
			assertNotNull(entityDb.getName());
			assertNotNull(entityDb.getEmail());
			assertNotNull(entityDb.getId());
			assertNotNull(entityDb.getCreated());
			assertNotNull(entityDb.getUpdated());
		}
		assertEquals(randomObjectsCount + entityCount, allEntities.size());
	}
	
	@Test
	public void createWithPersonalDataTest() {
		UserAccount userAccount = new UserAccount();
		userAccount.setEmail(getRandomString());
		userAccount.setName(getRandomString());
		
		PersonalData personalData = new PersonalData();
		personalData.setLogin(getRandomString());
		personalData.setPassword(getRandomString());
		personalData.setRole(Roles.USER);
		personalData.setId(userAccount.getId());
		userAccount.setPersonalData(personalData);
		
		userAccountService.createNewUser(userAccount);
		
		UserAccount userAccountDb = userAccountService.getFullInfo(userAccount.getId());
		
		assertNotNull(userAccountDb);
		assertEquals(userAccount.getName(), userAccountDb.getName());
		assertEquals(userAccount.getEmail(), userAccountDb.getEmail());
		assertNotNull(userAccountDb.getId());
		assertNotNull(userAccountDb.getCreated());
		assertNotNull(userAccountDb.getUpdated());
		assertTrue(userAccountDb.getCreated().equals(userAccountDb.getUpdated()));
		
		PersonalData perronalDataDb = userAccountDb.getPersonalData();
		assertEquals(personalData.getLogin(), perronalDataDb.getLogin());
		assertEquals(personalData.getPassword(), perronalDataDb.getPassword());
		assertEquals(personalData.getRole(), personalData.getRole());
		assertNotNull(perronalDataDb.getId());
		assertNotNull(perronalDataDb.getCreated());
		assertNotNull(perronalDataDb.getUpdated());
		assertTrue(perronalDataDb.getCreated().equals(perronalDataDb.getUpdated()));
	}
}

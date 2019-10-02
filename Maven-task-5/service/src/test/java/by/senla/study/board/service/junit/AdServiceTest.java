package by.senla.study.board.service.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.jupiter.api.Test;

import by.senla.study.board.model.entity.Ad;

public class AdServiceTest extends AbstractTest {

	@Test
	public void testCreate() {
		Ad entity = createAdEntity();

		Ad entityDb = adService.getById(entity.getId());

		assertNotNull(entityDb);
		assertEquals(entity.getImage(), entityDb.getImage());
		assertEquals(entity.getPrice(), entityDb.getPrice());
		assertEquals(entity.getStatus(), entityDb.getStatus());
		assertEquals(entity.getText(), entityDb.getText());
		assertEquals(entity.getTheme(), entityDb.getTheme());
		assertEquals(entity.getSeller().getId().intValue(), entityDb.getSeller().getId().intValue());
		assertEquals(entity.getCategory().getId().intValue(), entityDb.getCategory().getId().intValue());
		assertNotNull(entityDb.getId());
		assertNotNull(entityDb.getCreated());
		assertNotNull(entityDb.getUpdated());
		assertTrue(entityDb.getCreated().equals(entityDb.getUpdated()));
	}

	@Test
	public void testUpdate() throws InterruptedException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Ad entity = createAdEntity();

		String newTheme = entity.getTheme() + "_updated";
		entity.setTheme(newTheme);
		Thread.sleep(TIME);
		adService.update(entity);

		Ad entityDb = adService.getById(entity.getId());

		assertNotNull(entityDb);
		assertEquals(entity.getImage(), entityDb.getImage());
		assertEquals(entity.getPrice(), entityDb.getPrice());
		assertEquals(entity.getStatus(), entityDb.getStatus());
		assertEquals(entity.getText(), entityDb.getText());
		assertEquals(entity.getTheme(), entityDb.getTheme());
		assertEquals(entity.getSeller().getId().intValue(), entityDb.getSeller().getId().intValue());
		assertEquals(entity.getCategory().getId().intValue(), entityDb.getCategory().getId().intValue());
		assertNotNull(entityDb.getId());
		assertNotNull(entityDb.getCreated());
		assertNotNull(entityDb.getUpdated());
		// assertEquals(dateFormat.format(entity.getCreated()),
		// dateFormat.format(entityDb.getCreated()));
		assertTrue(entityDb.getUpdated().after(entity.getCreated()));
	}

	@Test
	public void testDelete() {
		Ad entity = createAdEntity();
		adService.deleteById(entity.getId());
		assertNull(adService.getById(entity.getId()));
	}

	@Test
	public void testSellectAll() {
		int entityCount = adService.selectAll().size();

		int randomObjectsCount = getRandomInt();
		for (int i = 0; i < randomObjectsCount; i++) {
			createAdEntity();
		}

		List<Ad> allEntities = adService.selectAll();
		for (Ad entityDb : allEntities) {
			assertNotNull(entityDb.getImage());
			assertNotNull(entityDb.getPrice());
			assertNotNull(entityDb.getStatus());
			assertNotNull(entityDb.getText());
			assertNotNull(entityDb.getTheme());
			assertNotNull(entityDb.getSeller());
			assertNotNull(entityDb.getCategory());
			assertNotNull(entityDb.getId());
			assertNotNull(entityDb.getCreated());
			assertNotNull(entityDb.getUpdated());
		}
		assertEquals(randomObjectsCount + entityCount, allEntities.size());
	}
}

package by.senla.study.board.service.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.jupiter.api.Test;

import by.senla.study.board.model.entity.Ranking;

public class RankingServiceTest extends AbstractTest {

	@Test
	public void testCreate() {
		Ranking entity = createRankingEntity();

		Ranking entityDb = rankingService.getById(entity.getId());

		assertNotNull(entityDb);
		assertEquals(entity.getFeedback().intValue(), entityDb.getFeedback().intValue());
		assertEquals(entity.getText(), entityDb.getText());
		assertEquals(entity.getUserFrom().getId().intValue(), entityDb.getUserFrom().getId().intValue());
		assertEquals(entity.getUserWhom().getId().intValue(), entityDb.getUserWhom().getId().intValue());
		assertNotNull(entityDb.getId());
		assertNotNull(entityDb.getCreated());
		assertNotNull(entityDb.getUpdated());
		assertTrue(entityDb.getCreated().equals(entityDb.getUpdated()));
	}

	@Test
	public void testUpdate() throws InterruptedException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Ranking entity = createRankingEntity();

		Integer newFeedback = gerRandomFeedback();
		entity.setFeedback(newFeedback);
		Thread.sleep(TIME);
		rankingService.update(entity);

		Ranking entityDb = rankingService.getById(entity.getId());

		assertNotNull(entityDb);
		assertEquals(entity.getFeedback().intValue(), entityDb.getFeedback().intValue());
		assertEquals(entity.getText(), entityDb.getText());
		assertEquals(entity.getUserFrom().getId().intValue(), entityDb.getUserFrom().getId().intValue());
		assertEquals(entity.getUserWhom().getId().intValue(), entityDb.getUserWhom().getId().intValue());
		assertNotNull(entityDb.getId());
		assertNotNull(entityDb.getCreated());
		assertNotNull(entityDb.getUpdated());
		// assertEquals(dateFormat.format(entity.getCreated()),
		// dateFormat.format(entityDb.getCreated()));
		assertTrue(entityDb.getUpdated().after(entity.getCreated()));
	}

	@Test
	public void testDelete() {
		Ranking entity = createRankingEntity();
		rankingService.deleteById(entity.getId());
		assertNull(rankingService.getById(entity.getId()));
	}

	@Test
	public void testSellectAll() {
		int entityCount = rankingService.selectAll().size();

		int randomObjectsCount = getRandomInt();
		for (int i = 0; i < randomObjectsCount; i++) {
			createRankingEntity();
		}

		List<Ranking> allEntities = rankingService.selectAll();
		for (Ranking entityDb : allEntities) {
			assertNotNull(entityDb.getFeedback());
			assertNotNull(entityDb.getText());
			assertNotNull(entityDb.getUserFrom());
			assertNotNull(entityDb.getUserWhom());
			assertNotNull(entityDb.getId());
			assertNotNull(entityDb.getCreated());
			assertNotNull(entityDb.getUpdated());
		}
		assertEquals(randomObjectsCount + entityCount, allEntities.size());
	}
}

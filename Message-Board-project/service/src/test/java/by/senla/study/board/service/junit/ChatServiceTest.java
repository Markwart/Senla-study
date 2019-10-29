package by.senla.study.board.service.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.jupiter.api.Test;

import by.senla.study.board.model.entity.Chat;

public class ChatServiceTest extends AbstractTest {

	@Test
	public void testCreate() {
		Chat entity = createChatEntity();

		Chat entityDb = chatService.getById(entity.getId());

		assertNotNull(entityDb);
		assertEquals(entity.getName(), entityDb.getName());
		assertNotNull(entityDb.getId());
		assertNotNull(entityDb.getCreated());
		assertNotNull(entityDb.getUpdated());
		assertTrue(entityDb.getCreated().equals(entityDb.getUpdated()));
	}

	@Test
	public void testUpdate() throws InterruptedException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Chat entity = createChatEntity();

		String newName = entity.getName() + "_updated";
		entity.setName(newName);
		Thread.sleep(TIME);
		chatService.update(entity);

		Chat entityDb = chatService.getById(entity.getId());

		assertNotNull(entityDb);
		assertEquals(entity.getName(), entityDb.getName());
		assertNotNull(entityDb.getId());
		assertNotNull(entityDb.getCreated());
		assertNotNull(entityDb.getUpdated());
		//assertEquals(dateFormat.format(entity.getCreated()), dateFormat.format(entityDb.getCreated()));
		assertTrue(entityDb.getUpdated().after(entity.getCreated()));
	}

	@Test
	public void testDelete() {
		Chat entity = createChatEntity();
		chatService.deleteById(entity.getId());
		assertNull(chatService.getById(entity.getId()));
	}

	@Test
	public void testSellectAll() {
		int entityCount = chatService.selectAll().size();

		int randomObjectsCount = getRandomInt();
		for (int i = 0; i < randomObjectsCount; i++) {
			createChatEntity();
		}

		List<Chat> allEntities = chatService.selectAll();
		for (Chat entityDb : allEntities) {
			assertNotNull(entityDb.getName());
			assertNotNull(entityDb.getId());
			assertNotNull(entityDb.getCreated());
			assertNotNull(entityDb.getUpdated());
		}
		assertEquals(randomObjectsCount + entityCount, allEntities.size());
	}
}

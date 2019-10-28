package by.senla.study.board.service.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.jupiter.api.Test;

import by.senla.study.board.model.entity.Message;

public class MessageServiceTest extends AbstractTest {

	@Test
	public void testCreate() {
		Message entity = createMessageEntity();

		Message entityDb = messageService.getById(entity.getId());

		assertNotNull(entityDb);
		assertEquals(entity.getText(), entityDb.getText());
		assertEquals(entity.getUser().getId().intValue(), entityDb.getUser().getId().intValue());
		assertEquals(entity.getChat().getId().intValue(), entityDb.getChat().getId().intValue());
		assertNotNull(entityDb.getId());
		assertNotNull(entityDb.getCreated());
		assertNotNull(entityDb.getUpdated());
		assertTrue(entityDb.getCreated().equals(entityDb.getUpdated()));
	}

	@Test
	public void testUpdate() throws InterruptedException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Message entity = createMessageEntity();

		String newText = entity.getText() + "_updated";
		entity.setText(newText);
		Thread.sleep(TIME);
		messageService.update(entity);

		Message entityDb = messageService.getById(entity.getId());

		assertNotNull(entityDb);
		assertEquals(entity.getText(), entityDb.getText());
		assertEquals(entity.getUser().getId().intValue(), entityDb.getUser().getId().intValue());
		assertEquals(entity.getChat().getId().intValue(), entityDb.getChat().getId().intValue());
		assertNotNull(entityDb.getId());
		assertNotNull(entityDb.getCreated());
		assertNotNull(entityDb.getUpdated());
		// assertEquals(dateFormat.format(entity.getCreated()),
		// dateFormat.format(entityDb.getCreated()));
		assertTrue(entityDb.getUpdated().after(entity.getCreated()));
	}

	@Test
	public void testDelete() {
		Message entity = createMessageEntity();
		messageService.deleteById(entity.getId());
		assertNull(messageService.getById(entity.getId()));
	}

	@Test
	public void testSellectAll() {
		int entityCount = messageService.selectAll().size();

		int randomObjectsCount = getRandomInt();
		for (int i = 0; i < randomObjectsCount; i++) {
			createMessageEntity();
		}

		List<Message> allEntities = messageService.selectAll();
		for (Message entityDb : allEntities) {
			assertNotNull(entityDb.getText());
			assertNotNull(entityDb.getChat());
			assertNotNull(entityDb.getUser());
			assertNotNull(entityDb.getId());
			assertNotNull(entityDb.getCreated());
			assertNotNull(entityDb.getUpdated());
		}
		assertEquals(randomObjectsCount + entityCount, allEntities.size());
	}
}

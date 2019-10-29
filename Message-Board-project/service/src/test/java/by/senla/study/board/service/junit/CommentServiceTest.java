package by.senla.study.board.service.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.jupiter.api.Test;

import by.senla.study.board.model.entity.Comment;

public class CommentServiceTest extends AbstractTest {

	@Test
	public void testCreate() {
		Comment entity = createCommentEntity();

		Comment entityDb = commentService.getById(entity.getId());

		assertNotNull(entityDb);
		assertEquals(entity.getText(), entityDb.getText());
		assertEquals(entity.getUserFrom().getId().intValue(), entityDb.getUserFrom().getId().intValue());
		assertEquals(entity.getAd().getId().intValue(), entityDb.getAd().getId().intValue());
		assertNotNull(entityDb.getId());
		assertNotNull(entityDb.getCreated());
		assertNotNull(entityDb.getUpdated());
		assertTrue(entityDb.getCreated().equals(entityDb.getUpdated()));
	}
	
	@Test
	public void testUpdate() throws InterruptedException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Comment entity = createCommentEntity();

		String newText = entity.getText() + "_updated";
		entity.setText(newText);
		Thread.sleep(TIME);
		commentService.update(entity);

		Comment entityDb = commentService.getById(entity.getId());

		assertNotNull(entityDb);
		assertEquals(entity.getText(), entityDb.getText());
		assertEquals(entity.getUserFrom().getId().intValue(), entityDb.getUserFrom().getId().intValue());
		assertEquals(entity.getAd().getId().intValue(), entityDb.getAd().getId().intValue());
		assertNotNull(entityDb.getId());
		assertNotNull(entityDb.getCreated());
		assertNotNull(entityDb.getUpdated());
		//assertEquals(dateFormat.format(entity.getCreated()), dateFormat.format(entityDb.getCreated()));
		assertTrue(entityDb.getUpdated().after(entity.getCreated()));
	}
	
	@Test
	public void testDelete() {
		Comment entity = createCommentEntity();
		commentService.deleteById(entity.getId());
		assertNull(commentService.getById(entity.getId()));
	}
	
	@Test
	public void testSellectAll() {
		int entityCount = commentService.selectAll().size();

		int randomObjectsCount = getRandomInt();
		for (int i = 0; i < randomObjectsCount; i++) {
			createCommentEntity();
		}

		List<Comment> allEntities = commentService.selectAll();
		for (Comment entityDb : allEntities) {
			assertNotNull(entityDb.getText());
			assertNotNull(entityDb.getAd());
			assertNotNull(entityDb.getUserFrom());
			assertNotNull(entityDb.getId());
			assertNotNull(entityDb.getCreated());
			assertNotNull(entityDb.getUpdated());
		}
		assertEquals(randomObjectsCount + entityCount, allEntities.size());
	}
}

package by.senla.study.board.service.junit;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import by.senla.study.board.api.service.IAdService;
import by.senla.study.board.api.service.ICategoryService;
import by.senla.study.board.api.service.IChatService;
import by.senla.study.board.api.service.ICommentService;
import by.senla.study.board.api.service.IMessageService;
import by.senla.study.board.api.service.IPersonalDataService;
import by.senla.study.board.api.service.IRankingService;
import by.senla.study.board.api.service.IUserAccountService;
import by.senla.study.board.model.entity.Ad;
import by.senla.study.board.model.entity.Category;
import by.senla.study.board.model.entity.Chat;
import by.senla.study.board.model.entity.Comment;
import by.senla.study.board.model.entity.Message;
import by.senla.study.board.model.entity.PersonalData;
import by.senla.study.board.model.entity.Ranking;
import by.senla.study.board.model.entity.UserAccount;
import by.senla.study.board.model.enums.Roles;
import by.senla.study.board.model.enums.Status;

@SpringJUnitConfig(locations = "classpath:service-context-test.xml")
public abstract class AbstractTest {

	private static final Random RANDOM = new Random();
	protected static final Integer TIME = 1000;

	@Autowired
	protected IAdService adService;
	@Autowired
	protected ICategoryService categoryService;
	@Autowired
	protected IChatService chatService;
	@Autowired
	protected ICommentService commentService;
	@Autowired
	protected IMessageService messageService;
	@Autowired
	protected IPersonalDataService personalDataService;
	@Autowired
	protected IRankingService rankingService;
	@Autowired
	protected IUserAccountService userAccountService;

	@BeforeEach
	public void cleanBd() {
		userAccountService.deleteAll();
		categoryService.deleteAll();
		rankingService.deleteAll();
		commentService.deleteAll();
		commentService.deleteAll();
		chatService.deleteAll();
		adService.deleteAll();
		personalDataService.deleteAll();

	}

	protected static String getRandomString() {
		return "random-" + RANDOM.nextInt(9999);
	}

	protected static Integer getRandomInt() {
		return RANDOM.nextInt(10) + 1;
	}

	protected static Integer gerRandomFeedback() {
		return RANDOM.nextInt(5) + 1;
	}

	public Ad createAdEntity() {
		Ad entity = new Ad();
		entity.setImage(getRandomString());
		entity.setPrice(new BigDecimal(getRandomInt()));
		entity.setTheme(getRandomString());
		entity.setText(getRandomString());
		entity.setStatus(Status.OPEN);
		entity.setCategory(createCategoryEntity());
		entity.setSeller(createUserAccountEntity());
		adService.insert(entity);
		return entity;
	}

	public Category createCategoryEntity() {
		Category entity = new Category();
		entity.setName(getRandomString());
		categoryService.insert(entity);
		return entity;
	}

	public Chat createChatEntity() {
		Chat entity = new Chat();
		entity.setName(getRandomString());
		chatService.insert(entity);
		return entity;
	}

	public Comment createCommentEntity() {
		Comment entity = new Comment();
		entity.setText(getRandomString());
		entity.setAd(createAdEntity());
		entity.setUserFrom(createUserAccountEntity());
		commentService.insert(entity);
		return entity;
	}

	public Message createMessageEntity() {
		Message entity = new Message();
		entity.setText(getRandomString());
		entity.setUser(createUserAccountEntity());
		entity.setChat(createChatEntity());
		messageService.insert(entity);
		return entity;
	}

	public Ranking createRankingEntity() {
		Ranking entity = new Ranking();
		entity.setFeedback(gerRandomFeedback());
		entity.setUserFrom(createUserAccountEntity());
		entity.setUserWhom(createUserAccountEntity());
		rankingService.insert(entity);
		return entity;
	}

	public PersonalData createPersonalDataEntity() {
		UserAccount userAccount = createUserAccountEntity();
		
		PersonalData entity = new PersonalData();
		entity.setLogin(getRandomString());
		entity.setPassword(getRandomString());
		entity.setRole(Roles.USER);
		entity.setId(userAccount.getId());
		entity.setUserAccount(userAccount);
		personalDataService.insert(entity);
		return entity;
	}

	public UserAccount createUserAccountEntity() {
		UserAccount userAccount = new UserAccount();
		// PersonalData personalData = new PersonalData();

		userAccount.setEmail(getRandomString());
		userAccount.setName(getRandomString());

		for (int i = 0; i < getRandomInt(); i++) {
			userAccount.getChats().add(createChatEntity());
		}

		/*
		 * personalData.setLogin(RANDOM_STRING);
		 * personalData.setPassword(RANDOM_STRING); personalData.setRole(Roles.USER);
		 * 
		 * userAccount.setPersonalData(personalData);
		 */

		userAccountService.insert(userAccount);
		return userAccount;
	}
}

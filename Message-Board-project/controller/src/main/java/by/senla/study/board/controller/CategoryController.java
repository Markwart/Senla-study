package by.senla.study.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.senla.study.board.api.service.ICategoryService;
import by.senla.study.board.model.dto.CategoryDto;
import by.senla.study.board.model.entity.Category;
import by.senla.study.board.service.mapper.CategoryMapper;

@RestController
@RequestMapping(value = "/category")
public class CategoryController extends AbstractController<Category, Integer, CategoryDto> {

	private final ICategoryService categoryService;
	private final CategoryMapper mapper;

	@Autowired
	public CategoryController(ICategoryService categoryService, CategoryMapper mapper) {
		super(Category.class, categoryService, mapper);
		this.categoryService = categoryService;
		this.mapper = mapper;
	}
}

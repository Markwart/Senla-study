package by.senla.study.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.senla.study.board.api.service.ICategoryService;
import by.senla.study.board.model.dto.CategoryDTO;
import by.senla.study.board.model.entity.Category;
import by.senla.study.board.service.converterDTO.CategoryFromDTO;
import by.senla.study.board.service.converterDTO.CategoryToDTO;

@RestController
@RequestMapping(value = "/category")
public class CategoryController extends AbstractController<Category, Integer, CategoryDTO> {

	private final ICategoryService categoryService;
	private final CategoryToDTO toDTOConverter;
	private final CategoryFromDTO fromDTOConverter;

	@Autowired
	public CategoryController(ICategoryService categoryService, CategoryToDTO toDTOConverter,
			CategoryFromDTO fromDTOConverter) {
		super(Category.class, categoryService, toDTOConverter, fromDTOConverter);
		this.categoryService = categoryService;
		this.toDTOConverter = toDTOConverter;
		this.fromDTOConverter = fromDTOConverter;
	}
}

package by.senla.study.board.service.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.senla.study.board.model.dto.CategoryDto;
import by.senla.study.board.model.entity.Category;

@Component
public class CategoryMapper extends AbstractMapper<Category, CategoryDto> {

	@Autowired
	CategoryMapper() {
		super(Category.class, CategoryDto.class);
	}
}

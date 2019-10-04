package by.senla.study.board.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import by.senla.study.board.api.DTOConverter.IMapper;
import by.senla.study.board.api.service.GenericService;
import by.senla.study.board.model.dto.BaseDto;
import by.senla.study.board.model.entity.BaseEntity;

public abstract class AbstractController<T extends BaseEntity, PK, D extends BaseDto> {
	
	protected static final String UPDATED = "%s with id=%d was updated";
	protected static final String CREATED = "new %s with id=%d was created";
	private static final String DELETED = "%s with id=%d was deleted";

	private Class<T> entityClass;

	private GenericService<T, PK, D> service;
	private IMapper<T, D> mapper;

	public AbstractController(Class<T> entityClass, GenericService<T, PK, D> service, IMapper<T, D> mapper) {
		super();
		this.entityClass = entityClass;
		this.service = service;
		this.mapper = mapper;
	}

	public Class<T> getEntityClass() {
		return entityClass;
	}

	@GetMapping(value = "/{id}")
	public D findByID(@PathVariable(name = "id", required = true) PK id) {
		T entity = service.getById(id);
		D dto = mapper.toDto(entity);
		return dto;
	}

	@GetMapping(value = "/sellectAll")
	public List<D> findAll() {
		List<T> entities = service.selectAll();
		List<D> dtos = new ArrayList<>();
		for (T entity : entities) {
			dtos.add(mapper.toDto(entity));
		}
		return dtos;
	}

	@PutMapping(value = "/{id}/edit")
	public String edit(@PathVariable(name = "id", required = true) PK id, D dto) {
		T entity = service.getById(id);
		service.setFieldsAndUpdate(entity, dto);
		return String.format(UPDATED, getEntityClass().getSimpleName(), id);
	}

	@PostMapping(value = "/create")
	public String create(D dto) {
		T entity = mapper.toEntity(dto);
		service.insert(entity);
		return String.format(CREATED, getEntityClass().getSimpleName(), entity.getId());
	}

	@DeleteMapping(value = "/{id}/delete")
	public String delete(@PathVariable(name = "id", required = true) PK id) {
		service.deleteById(id);
		return String.format(DELETED, getEntityClass().getSimpleName(), id);
	}
}

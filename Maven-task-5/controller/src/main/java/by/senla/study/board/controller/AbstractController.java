package by.senla.study.board.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import by.senla.study.board.api.service.GenericService;
import by.senla.study.board.api.сonverter.IMapper;
import by.senla.study.board.dto.ResponseDto;
import by.senla.study.board.exception.RecordNotFoundException;
import by.senla.study.board.model.dto.BaseDto;
import by.senla.study.board.model.entity.BaseEntity;
import by.senla.study.board.security.AuthHelper;

public abstract class AbstractController<T extends BaseEntity, PK, D extends BaseDto> {

	protected static final String UPDATED = "%s with id=%d was updated";
	protected static final String CREATED = "new %s with id=%d was created";
	private static final String DELETED = "%s with id=%d was deleted";
	protected static final String INVALID = "Sorry, you entered invalid data.";

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

		if (dto == null) {
			throw new RecordNotFoundException();
		}
		return dto;
	}

	@GetMapping(value = "/sellectAll")
	public List<D> findAll() {
		List<T> entities = service.selectAll();
		List<D> dtos = new ArrayList<>();
		for (T entity : entities) {
			dtos.add(mapper.toDto(entity));
		}
		if (dtos.isEmpty()) {
			throw new RecordNotFoundException();
		}
		return dtos;
	}

	@PutMapping(value = "/{id}/edit")
	public ResponseDto edit(@PathVariable(name = "id", required = true) PK id, @Valid @RequestBody D dto,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseDto(INVALID);
		} else {
			T entity = service.getById(id);
			service.setFieldsAndUpdate(entity, dto);
			return new ResponseDto(String.format(UPDATED, getEntityClass().getSimpleName(), id));
		}
	}

	@PostMapping(value = "/create")
	public ResponseDto create(@Valid @RequestBody D dto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseDto(INVALID);
		} else {
			T entity = mapper.toEntity(dto);
			service.insert(entity);
			return new ResponseDto(String.format(CREATED, getEntityClass().getSimpleName(), entity.getId()));
		}
	}

	@DeleteMapping(value = "/{id}/delete")
	public ResponseDto delete(@PathVariable(name = "id", required = true) PK id) {
		service.deleteById(id);
		return new ResponseDto(String.format(DELETED, getEntityClass().getSimpleName(), id));
	}

	public Integer getLoggedUserId() {
		return AuthHelper.getLoggedUserId();
	}
}

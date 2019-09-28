package by.senla.study.board.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import by.senla.study.board.api.DTOConverter.IFromDTOConverter;
import by.senla.study.board.api.DTOConverter.IToDTOConverter;
import by.senla.study.board.api.service.GenericService;
import by.senla.study.board.exception.JsonMapperException;
import by.senla.study.board.model.dto.BaseDTO;
import by.senla.study.board.model.entity.BaseEntity;

public abstract class AbstractController<T extends BaseEntity, PK, D extends BaseDTO> {

	private static final String EXCEPTION = "Controller layer. JSON Mapper exception";

	private Class<T> entityClass;

	protected GenericService<T, PK> service;
	protected IToDTOConverter<T, D> toDTOConverter;
	protected IFromDTOConverter<T, D> fromDTOConverter;

	public AbstractController(Class<T> entityClass, GenericService<T, PK> service, IToDTOConverter<T, D> toDTOConverter,
			IFromDTOConverter<T, D> fromDTOConverter) {
		super();
		this.entityClass = entityClass;
		this.service = service;
		this.toDTOConverter = toDTOConverter;
		this.fromDTOConverter = fromDTOConverter;
	}

	public Class<T> getEntityClass() {
		return entityClass;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String findByID(@PathVariable(name = "id", required = true) PK id) {
		T entity = service.getByID(id);
		D dto = toDTOConverter.apply(entity);

		String json = null;
		try {
			json = new ObjectMapper().writeValueAsString(dto);
		} catch (IOException e) {
			throw new JsonMapperException(EXCEPTION, e);
		}
		return json;
	}

	@RequestMapping(value = "/sellectAll", method = RequestMethod.GET)
	public String findAll() {
		List<T> entities = service.selectAll();
		List<D> dtos = entities.stream().map(toDTOConverter).collect(Collectors.toList());

		String json = null;
		try {
			json = new ObjectMapper().writeValueAsString(dtos);
		} catch (IOException e) {
			throw new JsonMapperException(EXCEPTION, e);
		}
		return json;
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.PUT)
	public String edit(@PathVariable(name = "id", required = true) PK id) {
		D dto = toDTOConverter.apply(service.getByID(id));

		String json = null;
		try {
			json = new ObjectMapper().writeValueAsString(dto);
		} catch (IOException e) {
			throw new JsonMapperException(EXCEPTION, e);
		}
		return json;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(D dto) {
		T entity = fromDTOConverter.apply(dto);
		service.insert(entity);

		String json = null;
		try {
			json = new ObjectMapper().writeValueAsString(toDTOConverter.apply(entity));
		} catch (IOException e) {
			throw new JsonMapperException(EXCEPTION, e);
		}
		return json;
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE)
	public void delete(@PathVariable(name = "id", required = true) PK id) {
		service.deleteByID(id);
	}
}

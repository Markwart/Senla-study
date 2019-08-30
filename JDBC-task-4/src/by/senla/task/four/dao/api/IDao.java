package by.senla.task.four.dao.api;

import java.util.List;

public interface IDao<ENTITY, ID> {

	ENTITY createEntity();

	ENTITY get(ID id);

	void update(ENTITY entity);

	void insert(ENTITY entity);

	void delete(ID id);

	void deleteAll();

	List<ENTITY> selectAll();
}

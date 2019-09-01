package by.senla.study.project.dao;

import java.util.List;

public interface IDao<T, PK> {

	/*T createEntity();
*/
	T get(PK id);

	void update(T entity);

	void insert(T entity);

	void delete(PK id);

	void deleteAll();

	List<T> selectAll();
}

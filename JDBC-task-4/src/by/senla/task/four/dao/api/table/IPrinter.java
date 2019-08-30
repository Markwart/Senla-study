package by.senla.task.four.dao.api.table;

public interface IPrinter extends IBaseEntity {

	Character getColor();
	void setColor(Character color);

	String getType();
	void setType(String type);
	

}

package by.senla.task.four.dao.api.table;

public interface IPC extends IBaseEntity {

	Integer getSpeed();
	void setSpeed(Integer speed);

	Integer getRam();
	void setRam(Integer ram);

	Integer getHd();
	void setHd(Integer hd);

	String getCd();
	void setCd(String cd);

}

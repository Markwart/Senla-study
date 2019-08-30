package by.senla.task.four.dao.api.table;

public interface ILaptop extends IBaseEntity {

	Integer getSpeed();
	void setSpeed(Integer speed);

	Integer getRam();
	void setRam(Integer ram);

	Integer getHd();
	void setHd(Integer hd);

	Integer getScreen();
	void setScreen(Integer screen);

}

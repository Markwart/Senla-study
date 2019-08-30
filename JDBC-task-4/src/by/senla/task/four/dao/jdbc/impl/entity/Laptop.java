package by.senla.task.four.dao.jdbc.impl.entity;

import by.senla.task.four.dao.api.table.ILaptop;

public class Laptop extends BaseEntity implements ILaptop {

	private Integer speed;
	private Integer ram;
	private Integer hd;
	private Integer screen;

	@Override
	public Integer getSpeed() {
		return speed;
	}

	@Override
	public void setSpeed(Integer speed) {
		this.speed = speed;
	}

	@Override
	public Integer getRam() {
		return ram;
	}

	@Override
	public void setRam(Integer ram) {
		this.ram = ram;
	}

	@Override
	public Integer getHd() {
		return hd;
	}

	@Override
	public void setHd(Integer hd) {
		this.hd = hd;
	}

	@Override
	public Integer getScreen() {
		return screen;
	}

	@Override
	public void setScreen(Integer screen) {
		this.screen = screen;
	}
}

package by.senla.task.four.dao.jdbc.impl.entity;

import by.senla.task.four.dao.api.table.IPC;

public class PC extends BaseEntity implements IPC {

	private Integer speed;
	private Integer ram;
	private Integer hd;
	private String cd;

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
	public String getCd() {
		return cd;
	}

	@Override
	public void setCd(String cd) {
		this.cd = cd;
	}
}

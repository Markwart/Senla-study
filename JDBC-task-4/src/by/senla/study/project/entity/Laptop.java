package by.senla.study.project.entity;

public class Laptop extends BaseEntity {

	private Integer speed;
	private Integer ram;
	private Integer hd;
	private Integer screen;

	public Integer getSpeed() {
		return speed;
	}

	public void setSpeed(Integer speed) {
		this.speed = speed;
	}

	public Integer getRam() {
		return ram;
	}

	public void setRam(Integer ram) {
		this.ram = ram;
	}

	public Integer getHd() {
		return hd;
	}

	public void setHd(Integer hd) {
		this.hd = hd;
	}

	public Integer getScreen() {
		return screen;
	}

	public void setScreen(Integer screen) {
		this.screen = screen;
	}
}

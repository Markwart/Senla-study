package by.senla.study.project.entity;

public class PC extends BaseEntity {

	private Integer speed;
	private Integer ram;
	private Integer hd;
	private String cd;

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

	public String getCd() {
		return cd;
	}

	public void setCd(String cd) {
		this.cd = cd;
	}
}

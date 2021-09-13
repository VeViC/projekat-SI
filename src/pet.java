
public class pet {
	public Integer cid;
	public String name;
	public String race;
	public String nameV;
	public String snameV;
	public Integer age;
	public String date;
	
	public pet() {};

	public pet(Integer cid, String name, String race, String nameV, String snameV, Integer age, String date) {
		super();
		this.cid = cid;
		this.name = name;
		this.race = race;
		this.nameV = nameV;
		this.snameV = snameV;
		this.age = age;
		this.date = date;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public String getNameV() {
		return nameV;
	}

	public void setNameV(String nameV) {
		this.nameV = nameV;
	}

	public String getSnameV() {
		return snameV;
	}

	public void setSnameV(String snameV) {
		this.snameV = snameV;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	

}

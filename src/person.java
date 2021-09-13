
public class person {
	
	String name;
	String surname;
	String rank;
	
	public person() {};
	
	public person(String name, String surname, String rank) {
		super();
		this.name = name;
		this.surname = surname;
		this.rank = rank;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}
	
	
}


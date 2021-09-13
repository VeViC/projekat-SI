
public class vlasnici {
	
	public Integer cid;
	public String ime;
	public String prezime;
	public String email;
	public String tel;
	
	public vlasnici() {}
	
	public vlasnici(Integer cid, String ime, String prezime, String email, String tel) {
		super();
		this.cid = cid;
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.tel = tel;
	}

	
	
	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}


	
}
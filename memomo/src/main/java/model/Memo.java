package model;

public class Memo {
        
	private int userid;
	private String daimei;
	private String honbun;
	

	public Memo(int userid, String daimei, String honbun) {
		super();
		this.userid = userid;
		this.daimei = daimei;
		this.honbun = honbun;
		
	}


	public int getUserid() {
		return userid;
	}


	public void setUserid(int userid) {
		this.userid = userid;
	}


	public String getDaimei() {
		return daimei;
	}


	public void setDaimei(String daimei) {
		this.daimei = daimei;
	}


	public String getHonbun() {
		return honbun;
	}


	public void setHonbun(String honbun) {
		this.honbun = honbun;
	}
	
	
	
}

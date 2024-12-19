//Uye sınıfı
public class Member {
	private String nameSurname;
	private int memberID;

	
	//getter ve setter metodları
	public String getNameSurname() {
		return nameSurname;
	}

	public void setNameSurname(String nameSurname) {
		this.nameSurname = nameSurname;
	}

	public int getMemberID() {
		return memberID;
	}

	public void setMemberID(int memberID) {
		this.memberID = memberID;
	}

	//constructor metodu
	public Member(String nameSurname, int memberID) {

		this.nameSurname = nameSurname;
		this.memberID = memberID;
	}
	
	
	

}

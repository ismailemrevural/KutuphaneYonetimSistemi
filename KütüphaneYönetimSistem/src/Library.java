import java.util.*;


//kütüphane sınıfı
public class Library {

	private static Map<Integer, Book> books = new HashMap<>();//kitapları tutmak için bir HashMap
	private static Map<Integer, Member> members = new HashMap<>();//Üyeleri tutmak için bir HashMap
	public static Map<Integer, Officer> officers = new HashMap<>();//Memurları(çalışanlar) tutmak için bir HashMap
	private static Map<Integer,Integer> borrowedBooks = new HashMap<>();//Odunc alınan kitap ve kitabı alan üye id lerini tutmak için bir HashMap
	private static int bookID = 1;//kitap id değişkeni sistem tarafından başlangıç olarak 1 ayarlandı
	private static int memberID = 1;////Uye id değişkeni sistem tarafından başlangıç olarak 1 ayarlandı
	private static int officerID = 1;////Çalışan id değişkeni sistem tarafından başlangıç olarak 1 ayarlandı

	static Scanner scan = new Scanner(System.in);
	//tanımlanan metodların static olmasının sebebi kod okunabilirliği ve modulerliği açısından ayarlanmıştır
	
		
	//Kitap ekleme metodu
	public static void addBook() {
		System.out.println("Book Name : ");
		String bName = scan.nextLine();//Eklenecek Kitabın ismini alınıyor
		
		System.out.println("Book Author : ");
		String bAuthor = scan.nextLine();//Eklenecek Kitabın yazarı alınıyor

		Book book = new Book(bName, bAuthor, bookID);//Alınan verilerle kitap nesnesi oluşturuluyor

		books.put(book.getBookID(), book);//oluştuurlan Kitap books adlı Hashmap e ekleniyor
		System.out.println(" Added Book  " + book.getBookName() + " " + book.getAuthor() + " Book  ID : "
				+ book.getBookID());
		bookID++;//book ıd sistem tarafından başlangıçta 1 int değer olarak ayarlanmıştır
				//her bir kitap eklenince bookID değişkeni 1 artarak her kitabın farklı id 
				//alması sağlanmıştır
	}

	//kitap Silme metodu
	public static void delBook() {
		bookList();	//bookList metodu kitapları ve ID lerini gösterir (silmek istediğin kitap idsini görmen için)
		System.out.println("Type Book ID for the delete Book : ");
		int bID = scan.nextInt();	//silinmesi istenen kitap id si alınıyor
		scan.nextLine();// dummy read
		Book book = books.get(bID);	//girilen bID değerine sahip kitap alınıyor
		if (books.containsKey(bID)) {//girilen değere sahip kitabın bulunma kontrolü
			books.remove(bID);//kitap silme işlemi
			System.out.println("Removed book : " + book.getBookName() + " " + book.getAuthor());//silinen kitabın bilgileri ekrana bastrılıyor
		} else {
			System.out.println("There is no book has a bookID " + bID);
		}
	}
	
	//üye ekleme metodu
	public static void addMember() {
		System.out.println("Member NameSurname : ");
		String nameSname = scan.nextLine();//Uyenin ad soyad bilgileri alınıyor
		Member member = new Member(nameSname, memberID);//yeni bir üye oluşturuluyor

		members.put(memberID, member);//oluşturulan üye members(üyeler) adlı hashmape ekleniyor
		System.out.println("Added Member : " + member.getNameSurname() + " " + " Member ID " + member.getMemberID());//eklenen üyenin bilgileri bastırıyor
		memberID++;//üye eklendikten sonra memberID değişkeni 1 artıyor
				// böylelikle her üye farklı ID alıyor
	}
	
	//üye silme metodu
	public static void delMember() {
		memberList();//Sisteme kayıtlı olan üyeleri ve ID lerini gösteren metod 
		System.out.println("Type Member ID for the delete Member : ");
		int mID = scan.nextInt();//silinmek istenen üye nin ID si giriliyor
		scan.nextLine();// dummy read

		Member member = members.get(mID);//members Hashmapında girilen üyeID değerine sahip olan üye nesnesini alıyoruz
		if (members.containsKey(mID)) {//girilen ID sahip üye üyeler hashmapinde olma kontorl durumu
			members.remove(mID);//üye silme işlemi
			System.out.println("Removed Member : " + member.getNameSurname() + " " + member.getMemberID());//silinen üyenin bilgileri bastırılyıor
		} else {
			System.out.println("There is no Member has a Member ID" + mID);
		}

	}

	//Memur ekleme işlemi
	public static void addOfficer() {
		System.out.println("Officer NameSurname : ");
		String NameSurname = scan.nextLine();//Memur bilgileri alınıyor

		Officer officer = new Officer(NameSurname, officerID);//Memur objesi oluşuturluyor
		officers.put(officerID, officer);//oluşturulan memur objesi officers Hashmapine atılıyor
		officerID++;//officer ID bir arttırılıyor 
		System.out.println("Added Officer : " + officer.getNameSurname() + " " + officer.getMemberID());//eklenen memur bilgileri ekrana bastırılıyor

	}

	//Memur silme işlemi
	public static void delOfficer() {
		officerList();//Sisteme kayıtlı olan memur listesi ekrana bastırma metodu
		System.out.println("Type Officer ID for the delete Officer : ");
		int oID = scan.nextInt();//silinmek istenen Memurun Id verisi alınıyor
		scan.nextLine();//dummy read
		Officer officer = officers.get(oID);//girilen officerID sahip olan officer nesnesi alınıyor
		if (officers.containsKey(oID)) {//girilen officer ID inin olma kontrolü durumu
			officers.remove(oID);//memur silme işlemi
			System.out.println("Officer removed " + officer.getNameSurname() + " " + officer.getMemberID());//silinen memurun verileri ekrana bastırılıyor
		} else {
			System.out.println("There is no Member has a Officer ID" + oID);
		}
	}
	
	//kitap ödüncAlma metodu
	public static void borrowBook() {
		bookList();//sisteme ekli kitapları görme metodu
		System.out.println("Book ID : ");
		int bookID = scan.nextInt();//ödünç alınacak kitabın id si ekrana basılıyor
		scan.nextLine();// dummy read
		
		memberList();//sisteme  ekli üyeleri görme metodu
		System.out.println("Member ID : ");
		int memberID = scan.nextInt();//ödünç verilecek üyenin Id si alınıyor
		scan.nextLine();// dummy read
		

		if (books.containsKey(bookID) && members.containsKey(memberID)&&!borrowedBooks.containsKey(bookID)) {
			//girilen bookID e sahip ve girilen memberID ye sahip üye varsa ve girilen kitap ödünç alınmamışsa kontrolü
			
			Member member = members.get(memberID);//girilen memberID ye sahip üyeyi members Hashmapinden alıyoruz
			Book book = books.get(bookID);//girilen bookID ye sahip kitabı books Hashmapinden alıyoruz
			borrowedBooks.put(bookID,memberID);//book ID ve member ID değerini borrowedBooks adlı Hash mape atıyoruz
			System.out.println(member.getNameSurname()+" "+"took"+" "+book.getBookName());//kitbı ödünç alan üyenin ve ödünç alınan kitap verlie ekrana bastıerlıyor
		} else {
			System.out.println("Demanded Book Cant Given  ");
			System.out.println("Book Might Be Taken Or Doesnt Exist");

		}

	}
	
	//Odünç alınan ktiabı iade etme metodu
	public static void giveBackBook() {
		System.out.println("Book ID");
		int bookID = scan.nextInt();//iade edilecek kitabın id si ekrana basılıyor
		scan.nextLine();// dummy read
		System.out.println("Member ID : ");
		int memberID = scan.nextInt();//iade eden  üyenin Id si alınıyor
		scan.nextLine();// dummy read
		
		if (borrowedBooks.containsKey(bookID)) {//girilen kitabID sinin ödünç alınma kontrolü
			Member member = members.get(memberID);//verileri ekrana bastırmak için memberID ye sahip üyeyi Hashmapten alyıoruz
			Book book = books.get(bookID);//verileri ekrana bastırmak için bookID ye sahip kitabı Hashmapten alyıoruz
			borrowedBooks.remove(bookID);//ödünç alınan kitabı iade işemi
			System.out.println(member.getNameSurname()+" "+"gived back taken book "+book.getBookName());//kitabı iade eden üyenin ve ktiabın verileri ekrana bastırılyıor
			
		} else {
			System.out.println("İnavlid BookID");
		}
	}
	
	
	//ödünç alınan kitapları görme metodu
	public static void seeOwnedbooks() {
	
		for (Map.Entry<Integer, Integer> entry : borrowedBooks.entrySet()) {//kitap ve kitabı alan üye verilerini yazdırmak için																	
            int bID = entry.getKey();										//for each ile kümeye çevirip verileri ekrana yazdırıyorum
            int mID = entry.getValue();
            Book book = books.get(bID);
            Member member = members.get(mID);
            System.out.println( book.getBookName()+" taken by "+member.getNameSurname());
		}
	}
	
	//sisteme eklenen kitapları görme metodu
	public static void bookList() {
		System.out.println("-----Book List------");
		for(Map.Entry<Integer, Book> entry : books.entrySet()) {//books hashmapindeki verileri küemye çevirip for each ile ekrana bastırıyorum
			int bID = entry.getKey();
			Book book=entry.getValue();
			 
			System.out.println("Book ID "+bID+" "+book.getBookName()+" "+ book.getAuthor());
		}
		
		
	}
	
	//sisteme ekli üyeleri görme metodu
	public static void memberList() {
		System.out.println("-----Member List------");
		for(Map.Entry<Integer, Member> entry : members.entrySet()) {//members hashmapindeki verileri küemye çevirip for each ile ekrana bastırıyorum
			int mID = entry.getKey();
			Member member=entry.getValue();
			 
			System.out.println("Member ID "+mID+" "+member.getNameSurname());
		}
		
		
	}
	
	//sisteme ekli memurları görme metodu
	public static void officerList() {
		System.out.println("-----Officer List------");
	for(Map.Entry<Integer, Officer> entry : officers.entrySet()) {//offciers hashmapindeki verileri küemye çevirip for each ile ekrana bastırıyorum
		int oID = entry.getKey();
		Officer officer=entry.getValue();
		 
		System.out.println("Officer ID "+oID+" "+officer.getNameSurname());
	}
	
	
}
	//kütüphane yöentim sistemnii başlatma metodu
	public static void start() {

		while (true) {//metodun sürekli çalışmasını sağlamak için while döngüsü kullandım
			//yapılmak istenen işlemleri ekrana bastıroyuz
			System.out.println("<==Library System==>");
			System.out.println("1 ) Add Member ");
			System.out.println("2 ) Delete Member");
			System.out.println("3 ) Add Officer ");
			System.out.println("4 ) Delete Officer ");
			System.out.println("5 ) Add Book ");
			System.out.println("6 ) Delete Book ");
			System.out.println("7 ) Borrow Book ");
			System.out.println("8 ) GiveBack Book");
			System.out.println("9 ) See Owner of the Borrowed Book");
			System.out.println("10 ) Exit Library System ");
			Scanner scan = new Scanner(System.in);
			int select = scan.nextInt();
			scan.nextLine(); // Dummy read
			switch (select) {
			case 1:
				addMember();//üye ekleme case
				break;
			case 2:
				delMember();//üye silme case
				break;
			case 3:
				addOfficer();//memur ekleme case
				break;
			case 4:
				delOfficer();//memur silme case
				break;
			case 5:
				addBook();//kitap ekleme case
				break;
			case 6:
				delBook();//kitap silme case
				break;
			case 7:
				borrowBook();//Ödünç kitap alma case
				break;
			case 8:
				giveBackBook();//kitap iade etme case
			case 9:
				seeOwnedbooks();//Ödünç alınan kitaları görme case
				break;
			case 10:
				System.exit(0);// döngüyü ve programı bitiren case burda
				break;
			}

		}
	}
}
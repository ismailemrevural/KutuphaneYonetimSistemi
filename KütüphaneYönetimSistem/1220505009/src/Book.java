//kitap sınıfı
public class Book {
	private String bookName;
	private String author;
	private int bookID;
	
	
	//getter ve setter metodları
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public int getBookID() {
		return bookID;
	}

	public void setBookID(int bookID) {
		this.bookID = bookID;
	}


	
	//constructor metodu
	public Book(String bookName, String author, int bookID) {
		super();
		this.bookName = bookName;
		this.author = author;
		this.bookID = bookID;
		
	}
	

	

}

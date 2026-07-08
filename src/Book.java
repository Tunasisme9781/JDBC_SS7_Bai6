import java.util.Scanner;

public class Book{
    private int bookId;
    private String bookName;

    private String author;

    public Book() {
    }

    public Book(int bookId, String bookName, String author) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.author = author;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void inputData(Scanner scanner) {
        System.out.print("Nhập tên sách: ");
        this.bookName = scanner.nextLine();
        System.out.print("Nhập tác giả: ");
        this.author = scanner.nextLine();
    }

    // Phương thức hiển thị dữ liệu theo hàng (để in dạng bảng)
    public void displayData() {
        System.out.printf("| %-7d | %-25s | %-20s |\n", this.bookId, this.bookName, this.author);
    }
}


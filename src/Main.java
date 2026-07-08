import java.util.Scanner;

public class Main {
    // Khởi tạo mảng chứa tối đa 100 cuốn sách và biến size để quản lý số lượng thực tế
    private static Book[] library = new Book[100];
    private static int size = 0;
    private static int autoIncrementId = 1; 

    public static void main(String[]join) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n================= QUẢN LÝ THƯ VIỆN =================");
            System.out.println("1. Thêm mới sách");
            System.out.println("2. Hiển thị danh sách tất cả sách");
            System.out.println("3. Sửa thông tin sách theo ID");
            System.out.println("4. Xóa sách theo ID");
            System.out.println("5. Tìm kiếm sách theo tên");
            System.out.println("6. Thoát");
            System.out.print("Mời bạn chọn chức năng (1-6): ");

            while (!scanner.hasNextInt()) {
                System.out.print("Vui lòng nhập một số từ 1 đến 6: ");
                scanner.next();
            }
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addBook(scanner);
                    break;
                case 2:
                    displayAllBooks();
                    break;
                case 3:
                    updateBook(scanner);
                    break;
                case 4:
                    deleteBook(scanner);
                    break;
                case 5:
                    searchBookByName(scanner);
                    break;
                case 6:
                    System.out.println("Cảm ơn bạn đã sử dụng phần mềm! Tạm biệt.");
                    break;
                default:
                    System.err.println("Chức năng không hợp lệ. Vui lòng chọn lại!");
            }
        } while (choice != 6);
    }

    // 1. CHỨC NĂNG THÊM SÁCH
    private static void addBook(Scanner scanner) {
        if (size >= library.length) {
            System.err.println("Thư viện đã đầy, không thể thêm sách mới!");
            return;
        }
        System.out.println("\n--- THÊM SÁCH MỚI ---");
        Book newBook = new Book();
        newBook.setBookId(autoIncrementId++); 
        newBook.inputData(scanner);

        library[size] = newBook; 
        size++;
        System.out.println("✨ Thêm sách thành công! ID của sách là: " + newBook.getBookId());
    }

    // 2. CHỨC NĂNG HIỂN THỊ DANH SÁCH (Dạng bảng)
    private static void displayAllBooks() {
        if (size == 0) {
            System.out.println("✉ Thư viện hiện tại chưa có sách nào.");
            return;
        }
        printTableHeader();
        for (int i = 0; i < size; i++) {
            library[i].displayData();
        }
        printTableFooter();
    }

    // 3. CHỨC NĂNG SỬA THÔNG TIN SÁCH
    private static void updateBook(Scanner scanner) {
        System.out.print("Nhập ID sách cần sửa: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Xóa bộ nhớ đệm

        int index = findIndexById(id);
        if (index == -1) {
            System.err.println("Không tìm thấy sách với ID: " + id);
            return;
        }

        System.out.println("--- Nhập thông tin mới cho sách (ID: " + id + ") ---");
        library[index].inputData(scanner);
        System.out.println("✨ Cập nhật thông tin thành công!");
    }

    // 4. CHỨC NĂNG XÓA SÁCH (Dịch chuyển phần tử để tránh khoảng trống Null)
    private static void deleteBook(Scanner scanner) {
        System.out.print("Nhập ID sách cần xóa: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        int index = findIndexById(id);
        if (index == -1) {
            System.err.println("Không tìm thấy sách với ID: " + id);
            return;
        }

        // Đẩy các phần tử phía sau lên trước để đè vào vị trí bị xóa
        for (int i = index; i < size - 1; i++) {
            library[i] = library[i + 1];
        }
        library[size - 1] = null; 
        size--;
        System.out.println("🗑 Xóa sách thành công!");
    }

    // 5. CHỨC NĂNG TÌM KIẾM (Không phân biệt hoa thường)
    private static void searchBookByName(Scanner scanner) {
        System.out.print("Nhập từ khóa tên sách cần tìm: ");
        String keyword = scanner.nextLine().trim().toLowerCase();

        boolean found = false;

        for (int i = 0; i < size; i++) {
            if (library[i].getBookName().toLowerCase().contains(keyword)) {
                // NẾU ĐÂY LÀ CUỐN ĐẦU TIÊN TÌM THẤY: In đầu bảng ra trước
                if (!found) {
                    printTableHeader();
                    found = true;
                }
                library[i].displayData();
            }
        }


        if (found) {
            printTableFooter(); 
        } else {
            System.err.println("Không tìm thấy sách nào chứa từ khóa: \"" + keyword + "\""); 
        }
    }


    private static int findIndexById(int id) {
        for (int i = 0; i < size; i++) {
            if (library[i].getBookId() == id) {
                return i;
            }
        }
        return -1; 
    }

    // HÀM BỔ TRỢ: In giao diện bảng cho đẹp mắt
    private static void printTableHeader() {
        System.out.println("\n+---------+---------------------------+----------------------+");
        System.out.printf("| %-7s | %-25s | %-20s |\n", "ID Sách", "Tên Sách", "Tác Giả");
        System.out.println("+---------+---------------------------+----------------------+");
    }

    private static void printTableFooter() {
        System.out.println("+---------+---------------------------+----------------------+");
    }
}

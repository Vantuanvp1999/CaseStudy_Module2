import java.sql.SQLOutput;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
    ContactsManager contactsManager = new ContactsManager();
    menu();
    int choice = readChoice();
    while(choice!=0){
        switch (choice){
            case 1:
                contactsManager.displayContacts();
                break;
            case 2:
                contactsManager.addContact();
                break;
            case 3:
                contactsManager.editContact();
                break;
            case 4:
                contactsManager.deleteContact();
                break;
            case 5:
                contactsManager.searchContact();
                break;
            case 6:
               contactsManager.sortContacByName();
               break;
            case 7:
                contactsManager.addToCSV();
                break;
            case 8:
                contactsManager.readFromCSV();
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ. chọn lại");
        }
        choice = readChoice();
    }

    }
    public static int readChoice(){
        Scanner scanner= new Scanner(System.in);
        System.out.println("Enter your choice: ");
        return scanner.nextInt();
    }
    public static void menu(){
        System.out.println("---CHƯƠNG TRÌNH QUẢN LÝ DANH BẠ---");
        System.out.println("1. Xem danh bạ");
        System.out.println("2. Thêm mới");
        System.out.println("3. Sửa");
        System.out.println("4. Xóa");
        System.out.println("5. Tìm kiếm");
        System.out.println("6. Sắp xếp");
        System.out.println("7. Ghi vào file");
        System.out.println("8. Đọc từ file");
        System.out.println("0. Thoát");
        System.out.println("Chọn chức năng:");

    }
}
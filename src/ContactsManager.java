import java.io.*;
import java.security.spec.RSAOtherPrimeInfo;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ContactsManager implements IManager<Contacts> {
    public static final String FILE_NAME = "contacts.dat";
    private static File file = new File(FILE_NAME);
    private List<Contacts> contactsList= new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public ContactsManager() {
    }
    public void displayContacts(){
        if(contactsList.isEmpty()){
            System.out.println("Danh bạ trống");
            return;
        }
        System.out.println("==Danh bạ==");
        int count = 0;
        for (int i = 0; i < contactsList.size(); i++) {
            System.out.println(contactsList.get(i));
            count ++;
            if(count==5){
                System.out.println("Nhấn Enter để xem tiếp");
                scanner.nextLine();
                count=0;
            }

        }
        System.out.println("==Hết danh sách!==\n");
    }
    private boolean isValidEmail(String email){
        String emailRegex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        return Pattern.matches(emailRegex, email);
    }
    public void addContact(){
        String phone, group, name, gender, address, birthdate, email;
        while(true){
            System.out.println("Nhập số điện thoại (10 chữ số): ");
            phone = scanner.nextLine();
            if(phone.isEmpty()){
                System.out.println("Số điện thoại không được để trống");
            } else if (!phone.matches("^\\d{10}$")) {
                System.out.println("Số điện thoại phải đủ 10 chữ số");
            }else {
                break;
            }
        }
        while (true){
            System.out.println("Nhập nhóm: ");
            group = scanner.nextLine();
            if (group.isEmpty()){
                System.out.println("Nhóm không được để trống");
            } else {
                break;
            }
        }
        while (true){
            System.out.println("Nhập họ và tên: ");
            name = scanner.nextLine();
            if (name.isEmpty()){
                System.out.println("Họ và tên không được để trống");
            } else {
                break;
            }
        }
        while (true){
            System.out.println("Nhập giới tính: ");
            gender = scanner.nextLine();
            if (gender.isEmpty()){
                System.out.println("Giới tính không được để trống");
            } else if (!gender.equals("Nam")&&!gender.equals("Nữ")) {
                System.out.println("Giới tính không đúng định dạng:(Nam/Nữ)!");

            } else {
                break;
            }
        }
        while (true){
            System.out.println("Nhập địa chỉ: ");
            address = scanner.nextLine();
            if (address.isEmpty()){
                System.out.println("địa chỉ không được để trống");
            } else {
                break;
            }
        }
        while (true){
            System.out.println("Nhập ngày sinh (dd/mm/yyyy): ");
            birthdate = scanner.nextLine();
            if (birthdate.isEmpty()){
                System.out.println("Ngày sinh không được để trống");
            } else if (!birthdate.matches("^\\d{2}/\\d{2}/\\d{4}$")) {
                System.out.println("Ngày sinh không đúng format (vd: 02/11/2019)");
            } else {
                break;
            }
        }
        while(true){
            System.out.println("Nhập email:");
            email = scanner.nextLine();
            if(email.isEmpty()){
                System.out.println("Email không được để trống!");
            } else if (!isValidEmail(email)) {
                System.out.println("Email không đúng định dạng (ví dụ: ten@gmail.com)!");
            }else {
                break;
            }
        }
        Contacts contacts = new Contacts(phone,group,name,gender,address,birthdate,email);
        contactsList.add(contacts);
        System.out.println("Thêm danh bạ thành công");
    }
    public String inputRequired(String label){
        while (true){
            System.out.println(label+": ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()){
                System.out.println(label+" không được để trống");
            }
            else {
                return input;
            }
        }
    }
    public void editContact(){

        while(true){
            String  group, name, gender, address, birthdate, email;
            System.out.println("Nhập số điện thoại cần sửa");
            String phone = scanner.nextLine().trim();
            if(phone.isEmpty()){
                System.out.println("hủy cập nhật quay về menu");
            }
            Contacts contactToUpdate=null;
            for(Contacts c :contactsList){
                if(c.getPhoneNumber().equals(phone)){
                    contactToUpdate=c;
                }
            }
            if (contactToUpdate == null) {
                System.out.println(" Không tìm thấy danh bạ với số điện thoại trên.");
                continue;
            }
            group = inputRequired("Nhóm");
            name = inputRequired("Họ và tên");
            while(true){
                System.out.println("Nhập giới tính:");
                gender = scanner.nextLine();
                if(gender.isEmpty()){
                    System.out.println(" giới tính không được để trống!");
                } else if (!gender.equals("Nam")&&!gender.equals("Nữ")) {
                    System.out.println("Giới tính không đúng định dạng (ví dụ: Nam/Nữ");
                }else {
                    break;
                }
            }

            address=inputRequired("Địa chỉ");

            while (true){
                System.out.println("Nhập ngày sinh (dd/mm/yyyy): ");
                birthdate = scanner.nextLine();
                if (birthdate.isEmpty()){
                    System.out.println("Ngày sinh không được để trống");
                } else if (!birthdate.matches("^\\d{2}/\\d{2}/\\d{4}$")) {
                    System.out.println("Ngày sinh không đúng format (vd: 02/11/2019)");
                } else {
                    break;
                }
            }
            while(true){
                System.out.println("Nhập email:");
                email = scanner.nextLine();
                if(email.isEmpty()){
                    System.out.println("Email không được để trống!");
                } else if (!isValidEmail(email)) {
                    System.out.println("Email không đúng định dạng (ví dụ: ten@gmail.com)!");
                }else {
                    break;
                }
            }
            contactToUpdate.setGroup(group);
            contactToUpdate.setName(name);
            contactToUpdate.setGender(gender);
            contactToUpdate.setAddress(address);
            contactToUpdate.setBirthDate(birthdate);
            contactToUpdate.setEmail(email);
            System.out.println("Cập nhật thành công");
            return;
        }
    }
    public void deleteContact(){
        while(true){
            System.out.println("Nhập số điện thoại của danh bạ cần xóa");
            String phone =scanner.nextLine().trim();

            if(phone.isEmpty()){
                System.out.println("Hủy, quay về menu");
                break;
            }
            Contacts contactToDelete=null;
            for (Contacts c: contactsList){
                if(c.getPhoneNumber().equals(phone)){
                    contactToDelete=c;
                    break;
                }
            }
            if(contactToDelete==null){
                System.out.println("Không tìm thấy danh bạ với số điện thoại trên!");
                continue;
            }
            System.out.println("Tìm thấy "+ contactToDelete);
            System.out.println("Có chắc bạn muốn xóa (Y/N)");
            String confirm = scanner.nextLine().trim();
            if ("Y".equals(confirm)){
                contactsList.remove(contactToDelete);
                System.out.println("xóa danh bạ thành công! ");
            }else {
                System.out.println("Không xóa! Quay về menu!");

            }
            return;
        }
    }
    public void searchContact(){
        System.out.println("Nhập số từ khóa cần tìm (số điện thoại hoặc danh bạ):");
        String keyword = scanner.nextLine().trim();


        List<Contacts> results = new ArrayList<>();
        for (Contacts c:contactsList){
            if(c.getPhoneNumber().contains(keyword)||c.getName().toLowerCase().contains(keyword)){
                results.add(c);
            }
        }
        if (results==null){
            System.out.println("Không tìm thấy danh bạ");
        }else {
            System.out.println("Danh sách kết quả tìm kiếm");
            for (Contacts c:results){
                System.out.println(c);
            }
        }
    }
    public void sortContacByName(){
        if(contactsList.isEmpty()){
            System.out.println("Danh bạ trông, không thể sắp xếp");
            return;
        }
        contactsList.sort(Comparator.comparing(c->c.getName().toLowerCase()));
        this.displayContacts();

    }

    @Override
    public void addToCSV() {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(contactsList);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Contacts> readFromCSV() {
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            contactsList = (ArrayList<Contacts>) ois.readObject();

        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return contactsList;
    }
}

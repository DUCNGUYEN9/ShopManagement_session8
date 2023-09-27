package shop.run;

import shop.business.CategoriesBusiness;
import shop.business.ProductBusiness;
import shop.entity.Categories;
import shop.entity.Product;
import shop.util.ConnectionDB;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class ShopManagement {

    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        do {
            System.out.println("************ SHOP MANAGEMENT **************\n" +
                    "1. Quản lý danh mục\n" +
                    "2. Quản lý sản phẩm\n" +
                    "3. Báo cáo thống kê\n" +
                    "4. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    catalogManagement();
                    break;
                case 2:
                    productManagement();
                    break;
                case 3:
                    reportManagement();
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.err.println("Vui lòng chọn 1 - 4");
            }
        } while (true);
    }

    public static void catalogManagement() {
        boolean exit = true;
        do {
            System.out.println("********CATEGORIES MANAGEMENT******\n" +
                    "1. Hiển thị các danh mục\n" +
                    "2. Hiển thị danh mục sắp xếp theo độ ưu tiên tăng dần\n" +
                    "3. Thêm mới danh mục\n" +
                    "4. Cập nhật danh mục\n" +
                    "5. Xóa danh mục\n" +
                    "6. Tìm kiếm danh mục theo tên\n" +
                    "7. Cập nhật trạng thái danh mục\n" +
                    "8. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    displayCatalogList();
                    break;
                case 2:
                    sortPriorityASC();
                    break;
                case 3:
                    addNewCatalog(scanner);
                    break;
                case 4:
                    updateCatalog(scanner);
                    break;
                case 5:
                    deleteCatalog(scanner);
                    break;
                case 6:
                    searchCatalog(scanner);
                    break;
                case 7:
                    updateStatus(scanner);
                    break;
                case 8:
                    exit = false;
                    break;
                default:
                    System.err.println("Vui lòng chọn 1 - 8");
            }
        } while (exit);
    }
    public static void displayCatalogList() {
        List<Categories> categoriesList = CategoriesBusiness.getAllData();
        System.out.println("***Thông Tin Danh Mục***");
        categoriesList.forEach(Categories::displayData);
    }
    public static void addNewCatalog(Scanner scanner) {
        Categories categoriesNew = new Categories();
        categoriesNew.inputData(scanner);
        boolean result = CategoriesBusiness.insertDataNew(categoriesNew);
        if (result) {
            System.out.println("Success");
        } else {
            System.err.println("Error");
        }
    }
    public static void sortPriorityASC(){
        List<Categories> categoriesList = CategoriesBusiness.sortPriority();
        System.out.println("** Sort Priority ASC **");
        categoriesList.forEach(Categories::displayData);
    }
    public static void updateCatalog(Scanner scanner){
        System.out.print("Nhập mã cần cập nhật: ");
        int editId = Integer.parseInt(scanner.nextLine()) ;
        Categories categoriesUpdate = CategoriesBusiness.checkIdExists(editId) ;
        if (categoriesUpdate != null) {
            System.out.println("Nhập tên cần cập nhật: ");
            categoriesUpdate.setCatalogName(scanner.nextLine());
            System.out.println("Nhập dộ ưu tiên: ");
            categoriesUpdate.setPriority(Integer.parseInt(scanner.nextLine()));
            System.out.println("Nhập trạng thai: ");
            categoriesUpdate.setCatalogStatus(Boolean.parseBoolean(scanner.nextLine()));
            boolean result = CategoriesBusiness.update(categoriesUpdate) ;
            if (result){
                System.out.println("success");
            } else {
                System.out.println("Error !");
            }
        } else {
            System.out.println("No Exists !");
        }
    }
    public static void deleteCatalog(Scanner scanner) {
        System.out.print("Nhập vào mã cần xóa: ");
        int deleteId = Integer.parseInt(scanner.nextLine()) ;
        Categories categoriesDelete = CategoriesBusiness.checkIdExists(deleteId) ;
        if (categoriesDelete != null) {
            boolean result = CategoriesBusiness.delete(deleteId) ;
            if (result){
                System.out.println("success");
            } else {
                System.out.println("Error");
            }
        } else {
            System.out.println("No exists !");
        }
    }
    public static void searchCatalog(Scanner scanner){
        System.out.println("Nhập tên cần tìm kiêm: ");
        String nameSearch = scanner.nextLine() ;
        Categories categoriesSearch = CategoriesBusiness.checkNameExists(nameSearch) ;
        if (categoriesSearch != null) {
            List<Categories> categoriesList = CategoriesBusiness.searchFromName(nameSearch) ;
            System.out.println("** Sách Tìm Kiếm");
            categoriesList.forEach(Categories::displayData);
        }else {
            System.out.println("NO exists !");
        }
    }
    public static void updateStatus(Scanner scanner) {
        System.out.println("Nhập mã danh mục để cập nhật trạng thái: ");
        int updateId = Integer.parseInt(scanner.nextLine()) ;
        Categories categoriesUpdate = CategoriesBusiness.checkIdExists(updateId) ;
        if (categoriesUpdate != null) {
            System.out.println("Nhập trạng thái cần cập nhap: ");
            categoriesUpdate.setCatalogStatus(Boolean.parseBoolean(scanner.nextLine()));
            boolean result = CategoriesBusiness.updateStatus(categoriesUpdate) ;
            if (result) {
                System.out.println("success");
            } else {
                System.out.println("Error");
            }
        } else {
            System.out.println("No Exists");
        }
    }
    public static void productManagement() {
        boolean exit = true;
        do {
            System.out.println("*******PRODUCT MANAGEMENT********\n" +
                    "1. Hiển thị sản phẩm\n" +
                    "2. Hiển thị sản phẩm theo giá giảm dần\n" +
                    "3. Thêm mới sản phẩm\n" +
                    "4. Cập nhật sản phẩm\n" +
                    "5. Cập nhật trạng thái sản phẩm\n" +
                    "6. Xóa sản phẩm\n" +
                    "7. Tìm kiếm sản phẩm theo tên sản phẩm\n" +
                    "8. Tìm kiếm sản phẩm trong khoảng giá a-b\n" +
                    "9. Bán sản phẩm\n" +
                    "10.Thoát");
            System.out.print("Lựa chọn của bạn: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    displayProduct();
                    break;
                case 2:
                    break;
                case 3:
                    addNewProduct(scanner);
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 10:
                    exit = false;
                    break;
                default:
                    System.err.println("Vui lòng chọn 1 - 10");
            }
        } while (exit);
    }
    public static void displayProduct() {
        List<Product> productList = ProductBusiness.getAllData();
        System.out.println("*** Thông tin ***");
        productList.forEach(Product::displayData) ;
    }
    public static void addNewProduct(Scanner scanner) {
        Product productNew = new Product();
        productNew.inputData(scanner);
        boolean result = ProductBusiness.insertDataNew(productNew);
        if (result) {
            System.out.println("Success");
        } else {
            System.err.println("Error");
        }
    }
    public static void reportManagement() {
        boolean exit = true;
        do {
            System.out.println("*********REPORT MANAGEMENT*******\n" +
                    "1. Thống kê số danh mục theo trạng thái danh mục\n" +
                    "2. Thống kê số lượng sản phẩm theo trạng thái sản phẩm\n" +
                    "3. Thống kê số lượng sản phẩm theo từng danh mục\n" +
                    "4. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    exit = false;
                    break;
                default:
                    System.err.println("Vui lòng chọn 1 - 4");
            }
        } while (exit);
    }

}

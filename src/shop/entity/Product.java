package shop.entity;


import java.util.Date;
import java.util.Scanner;

public class Product {
    private String productId;
    private String productName;
    private float price;
    private Date Create;
    private int quantity;
    private int view;
    private int catalogId;
    private boolean productStatus;

    public Product() {
    }

    public Product(String productId, String productName, float price,
                   Date create, int quantity, int view, int catalogId,
                   boolean productStatus) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.Create = create;
        this.quantity = quantity;
        this.view = view;
        this.catalogId = catalogId;
        this.productStatus = productStatus;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getCreate() {
        return Create;
    }

    public void setCreate(Date create) {
        Create = create;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public boolean isProductStatus() {
        return productStatus;
    }

    public void setProductStatus(boolean productStatus) {
        this.productStatus = productStatus;
    }

    public void inputData(Scanner scanner) {
        System.out.print("Nhập vào mã sản phẩm:");
        this.productId = scanner.nextLine();
        System.out.print("Nhập vào tên sản phẩm:");
        this.productName = scanner.nextLine();
        System.out.print("Nhập vào Giá:");
        this.price = Float.parseFloat(scanner.nextLine());
        this.Create= new Date();
        System.out.print("Nhập vào Số lượng :");
        this.quantity = Integer.parseInt(scanner.nextLine());
        System.out.print("Nhập vào Lượt xem :");
        this.view = Integer.parseInt(scanner.nextLine());
        System.out.print("Nhập vào trạng thái:");
        this.productStatus = Boolean.parseBoolean(scanner.nextLine());
    }

    public void displayData() {
        System.out.printf("mã sản phẩm: %s - Tên sản phẩm: %s - Giá: %f - Ngày:%s  - Số lượng: %d - Lượt xem: %d - trạng thái: %b\n",
                this.productId, this.productName, this.price,this.Create, this.quantity,this.view,this.productStatus);


    }
}

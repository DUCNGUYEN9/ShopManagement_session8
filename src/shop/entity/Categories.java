package shop.entity;

import java.util.Scanner;

public class Categories {
    private int catalogId;
    private String catalogName;
    private int priority;
    private boolean catalogStatus;

    public Categories() {
    }

    public Categories(int catalogId, String catalogName, int priority, boolean catalogStatus) {
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.priority = priority;
        this.catalogStatus = catalogStatus;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isCatalogStatus() {
        return catalogStatus;
    }

    public void setCatalogStatus(boolean catalogStatus) {
        this.catalogStatus = catalogStatus;
    }
    public void inputData(Scanner scanner) {
//        System.out.print("Nhập vào mã danh mục:");
//        this.catalogId = Integer.parseInt(scanner.nextLine());
        System.out.print("Nhập vào tên danh mục:");
        this.catalogName = scanner.nextLine();
        System.out.print("Nhập vào độ ưu tiên:");
        this.priority = Integer.parseInt(scanner.nextLine());
        System.out.print("Nhập vào trạng thái:");
        this.catalogStatus = Boolean.parseBoolean(scanner.nextLine());
    }
    public void displayData(){
        System.out.printf("Mã danh mục: %d - Tên danh mục: %s - độ ưu tiên: %d - Trạng thái: %b\n",
                this.catalogId,this.catalogName,this.priority,this.catalogStatus);
    }
}

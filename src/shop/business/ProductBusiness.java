package shop.business;

import shop.entity.Categories;
import shop.entity.Product;
import shop.util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductBusiness {
    public static List<Product> getAllData() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Product> productList = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call get_all_data_product()}");
            ResultSet rs = callSt.executeQuery();
            productList = new ArrayList<>();
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getString("ProductId"));
                product.setProductName(rs.getString("ProductName"));
                product.setPrice(rs.getFloat("Price"));
                product.setCreate(rs.getDate("CreateDate"));
                product.setQuantity(rs.getInt("Quantity"));
                product.setView(rs.getInt("View"));
                product.setCatalogId(rs.getInt("CatalogId"));
                product.setProductStatus(rs.getBoolean("ProductStatus"));
                productList.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return productList;
    }

    public static boolean insertDataNew(Product product) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call insert_new_catalog(?,?,?,?,?,?,?,?)}");
            callSt.setString(1, product.getProductId());
            callSt.setString(2, product.getProductName());
            callSt.setFloat(3, product.getPrice());
            callSt.setDate(4, (Date) product.getCreate());
            callSt.setInt(5, product.getQuantity());
            callSt.setInt(6, product.getView());
            callSt.setInt(7, product.getCatalogId());
            callSt.setBoolean(8, product.isProductStatus());
            callSt.executeUpdate();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }

}

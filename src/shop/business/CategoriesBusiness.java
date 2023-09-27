package shop.business;

import shop.entity.Categories;
import shop.util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CategoriesBusiness {
    public static List<Categories> getAllData() {
        List<Categories> categoriesList = null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call get_all_data_catalog()}");
            ResultSet rs = callSt.executeQuery();
            categoriesList = new ArrayList<>();
            while (rs.next()) {
                Categories categories = new Categories();
                categories.setCatalogId(rs.getInt("CatalogId"));
                categories.setCatalogName(rs.getString("CatalogName"));
                categories.setPriority(rs.getInt("Priority"));
                categories.setCatalogStatus(rs.getBoolean("CatalogStatus"));
                categoriesList.add(categories);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return categoriesList;
    }

    public static boolean insertDataNew(Categories categories) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call insert_new_catalog(?,?,?)}");
            callSt.setString(1, categories.getCatalogName());
            callSt.setInt(2, categories.getPriority());
            callSt.setBoolean(3, categories.isCatalogStatus());
            callSt.executeUpdate();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }

    public static List<Categories> sortPriority() {
        List<Categories> categoriesList = null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call sort_priority()}");
            ResultSet rs = callSt.executeQuery();
            categoriesList = new ArrayList<>();
            while (rs.next()) {
                Categories categories = new Categories();
                categories.setCatalogId(rs.getInt("CatalogId"));
                categories.setCatalogName(rs.getString("CatalogName"));
                categories.setPriority(rs.getInt("Priority"));
                categories.setCatalogStatus(rs.getBoolean("CatalogStatus"));
                categoriesList.add(categories);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return categoriesList;
    }

    public static boolean update(Categories categories) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call update_catalog(?,?,?,?)}");
            callSt.setInt(1, categories.getCatalogId());
            callSt.setString(2, categories.getCatalogName());
            callSt.setInt(3, categories.getPriority());
            callSt.setBoolean(4, categories.isCatalogStatus());
            callSt.executeUpdate();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }

    public static Categories checkIdExists(int id) {
        Categories categories = null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call get_catalog_id(?)}");
            callSt.setInt(1, id);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                categories = new Categories();
                categories.setCatalogId(rs.getInt("CatalogId"));
                categories.setCatalogName(rs.getString("CatalogName"));
                categories.setPriority(rs.getInt("Priority"));
                categories.setCatalogStatus(rs.getBoolean("CatalogStatus"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return categories;
    }

    public static boolean delete(int id) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call delete_catalog(?)}");
            callSt.setInt(1, id);
            callSt.executeUpdate();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }

    public static List<Categories> searchFromName(String name) {
        List<Categories> categoriesList = null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call search_catalog_from_name(?)}");
            callSt.setString(1, name);
            ResultSet rs = callSt.executeQuery();
            categoriesList = new ArrayList<>();
            while (rs.next()) {
                Categories categories = new Categories();
                categories.setCatalogId(rs.getInt("CatalogId"));
                categories.setCatalogName(rs.getString("CatalogName"));
                categories.setPriority(rs.getInt("Priority"));
                categories.setCatalogStatus(rs.getBoolean("CatalogStatus"));
                categoriesList.add(categories);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return categoriesList;
    }
    public static Categories checkNameExists (String name) {
        Connection conn = null ;
        CallableStatement callSt = null ;
        Categories categories = null ;
        try {
            conn = ConnectionDB.openConnection() ;
            callSt = conn.prepareCall("{call get_catalog_name(?)}");
            callSt.setString(1,name);
            ResultSet rs = callSt.executeQuery() ;
            while (rs.next()) {
                categories = new Categories();
                categories.setCatalogId(rs.getInt("CatalogId"));
                categories.setCatalogName(rs.getString("CatalogName"));
                categories.setPriority(rs.getInt("Priority"));
                categories.setCatalogStatus(rs.getBoolean("CatalogStatus"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return categories ;
    }
    public static boolean updateStatus (Categories categories) {
        Connection conn = null ;
        CallableStatement callSt = null ;
        boolean result = false ;
        try {
            conn = ConnectionDB.openConnection() ;
            callSt = conn.prepareCall("{call update_status_catalog(?,?)}");
            callSt.setInt(1,categories.getCatalogId());
            callSt.setBoolean(2,categories.isCatalogStatus());
            callSt.executeUpdate() ;
            result = true ;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }
}

package BddPackage;

import Models.ProductCategory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductCategoryOperation extends BDD<ProductCategory> {
    @Override
    public boolean insert(ProductCategory o) {
        boolean ins = false;
        String query = "INSERT INTO `PRODUCT_CATEGORY`(`CATEGORY_NAME`) VALUES (?)";
        try {
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1,o.getName());
            int insert = preparedStmt.executeUpdate();
            if(insert != -1) ins = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ins;
    }

    @Override
    public boolean update(ProductCategory o1, ProductCategory o2) {
        boolean upd = false;
        String query = "UPDATE `PRODUCT_CATEGORY` SET `CATEGORY_NAME`=? WHERE `ID_CATEGORY` = ?";
        try {
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1,o1.getName());
            preparedStmt.setInt(2, o2.getId());
            int update = preparedStmt.executeUpdate();
            if(update != -1) upd = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return upd;    }

    @Override
    public boolean delete(ProductCategory o) {
        boolean del = false;
        String query = "DELETE FROM `PRODUCT_CATEGORY` WHERE  `ID_CATEGORY` = ? ";
        try {
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1,o.getId());
            int delete = preparedStmt.executeUpdate();
            if(delete != -1) del = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return del;    }

    @Override
    public boolean isExist(ProductCategory o) {
        return false;
    }

    @Override
    public ArrayList<ProductCategory> getAll() {
        ArrayList<ProductCategory> list = new ArrayList<>();
        String query = "SELECT * FROM `PRODUCT_CATEGORY`";
        try {
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            ResultSet resultSet = preparedStmt.executeQuery();
            while (resultSet.next()){
                ProductCategory productCategory = new ProductCategory();
                productCategory.setId(resultSet.getInt("ID_CATEGORY"));
                productCategory.setName(resultSet.getString("CATEGORY_NAME"));
                list.add(productCategory);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;     }
}

package BddPackage;

import Models.Drinks;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DrinksOperation extends BDD<Drinks> {
    @Override
    public boolean insert(Drinks o) {
        boolean ins = false;
        String query = "INSERT INTO `DRINKS`(`ID_DRINKS_CATEGORY`, `DRINKS_NAME`, `DRINKS_DESCRIPTION`, `DRINKS_PRICE`, `DRINKS_IMAGE`) \n" +
                "VALUES (?,?,?,?,?)";
        try {
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(   1,o.getId_category());
            preparedStmt.setString(2,o.getName());
            preparedStmt.setString(3,o.getDescription());
            preparedStmt.setInt(   4,o.getPrice());
            preparedStmt.setString(5,o.getImage_path());
            int insert = preparedStmt.executeUpdate();
            if(insert != -1) ins = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ins;
    }

    @Override
    public boolean update(Drinks o1, Drinks o2) {
        boolean upd = false;
        String query = "UPDATE `DRINKS` SET `ID_DRINKS_CATEGORY`=?,`DRINKS_NAME`=?,\n" +
                "    `DRINKS_DESCRIPTION`=?,`DRINKS_PRICE`=?,`DRINKS_IMAGE`=? WHERE ID_DRINKS = ?";
        try {
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(   1,o2.getId_category());
            preparedStmt.setString(2,o2.getName());
            preparedStmt.setString(3,o2.getDescription());
            preparedStmt.setInt(   4,o2.getPrice());
            preparedStmt.setString(5,o2.getImage_path());
            preparedStmt.setInt(   6,o1.getId());
            int update = preparedStmt.executeUpdate();
            if(update != -1) upd = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return upd;
    }

    @Override
    public boolean delete(Drinks o) {
        boolean del = false;
        String query = "DELETE FROM `DRINKS` WHERE `ID_DRINKS` = ?";
        try {
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1,o.getId());
            int delete = preparedStmt.executeUpdate();
            if (delete != -1) del = true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return del;
    }

    @Override
    public boolean isExist(Drinks o) {
        return false;
    }

    @Override
    public ArrayList<Drinks> getAll() {
        ArrayList<Drinks> list = new ArrayList<>();
        String query = "SELECT * FROM `DRINKS`";
        try {
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            ResultSet resultSet = preparedStmt.executeQuery();
            while (resultSet.next()){
                Drinks drinks = new Drinks();
                drinks.setId(resultSet.getInt(           1));
                drinks.setId_category(resultSet.getInt(  2));
                drinks.setName(resultSet.getString(      3));
                drinks.setDescription(resultSet.getString(4));
                drinks.setPrice(resultSet.getInt(        5));
                drinks.setImage_path(resultSet.getString(6));
                list.add(drinks);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}

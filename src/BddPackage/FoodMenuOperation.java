package BddPackage;

import Models.FoodMenu;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class FoodMenuOperation extends BDD<FoodMenu> {
    @Override
    public boolean insert(FoodMenu o) {
        boolean ins = false;
        String query = "INSERT INTO `FOOD_MENU`(`ID_FOOD`, `ID_MENU`) VALUES (?,?)";
        try {
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1,o.getId_menu());
            preparedStmt.setInt(2,o.getId_food());
            int insert = preparedStmt.executeUpdate();
            if(insert != -1) ins = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ins;
    }

    @Override
    public boolean update(FoodMenu o1, FoodMenu o2) {
        boolean upd = false;
        String query = "UPDATE `FOOD_MENU` SET `ID_MENU`=?,`ID_FOOD`=? WHERE ID_MENU = ? and ID_FOOD = ?";
        try {
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1,o1.getId_menu());
            preparedStmt.setInt(2,o1.getId_food());
            preparedStmt.setInt(3,o2.getId_menu());
            preparedStmt.setInt(4,o2.getId_food());
            int update = preparedStmt.executeUpdate();
            if(update != -1) upd=true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return upd;
    }

    @Override
    public boolean delete(FoodMenu o) {
        boolean del = false;
        String query = "DELETE FROM `FOOD_MENU` WHERE ID_MENU = ? ";
        try {
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1,o.getId_menu());
            int delete = preparedStmt.executeUpdate();
            if(delete != -1) del = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isExist(FoodMenu o) {
        return false;
    }

    @Override
    public ArrayList<FoodMenu> getAll() {
        return null;
    }
}

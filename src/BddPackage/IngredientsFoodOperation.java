package BddPackage;

import Models.IngredientsFood;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class IngredientsFoodOperation extends BDD<IngredientsFood> {
    @Override
    public boolean insert(IngredientsFood o) {

        boolean ins = false;
        String query = "INSERT INTO `INGREDIENTS_FOOD`(`ID_FOOD_INGREDIENT`, `ID_PRODUCT_INGREDIENT`,`INGREDIENT_QUANTITY`) VALUES (?,?,?)";
        try {
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, o.getId_food());
            preparedStmt.setInt(2, o.getId_product());
            preparedStmt.setInt(3, o.getQuantity());
            int insert = preparedStmt.executeUpdate();
            if (insert != -1) ins = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ins;
    }

    @Override
    public boolean update(IngredientsFood o1, IngredientsFood o2) {
        boolean upd = false;
        String query = "UPDATE `INGREDIENTS_FOOD` SET  `INGREDIENT_QUANTITY`=? WHERE `ID_FOOD_INGREDIENT`= ? AND `ID_PRODUCT_INGREDIENT` = ?";
        try {
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, o1.getQuantity());
            preparedStmt.setInt(2, o2.getId_food());
            preparedStmt.setInt(3, o2.getId_product());

            int update = preparedStmt.executeUpdate();
            if (update != -1) upd = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return upd;
    }

    @Override
    public boolean delete(IngredientsFood o) {
        String query = "DELETE FROM `INGREDIENTS_FOOD` WHERE `ID_FOOD_INGREDIENT` = ? AND `ID_PRODUCT_INGREDIENT` = ?";
        boolean del = false;
        try {
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, o.getId_food());
            preparedStmt.setInt(2, o.getId_product());
            int delete = preparedStmt.executeUpdate();
            if (delete != -1) del = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return del;
    }

    @Override
    public boolean isExist(IngredientsFood o) {
        return false;
    }

    @Override
    public ArrayList<IngredientsFood> getAll() {
        ArrayList<IngredientsFood> list = new ArrayList<>();
        String query = "SELECT * FROM `INGREDIENTS_FOOD`";
        try {
            chargeData(list, query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;

    }

    private ArrayList<IngredientsFood> chargeData(ArrayList<IngredientsFood> list, String query) throws SQLException {
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        ResultSet resultSet = preparedStmt.executeQuery();
        while (resultSet.next()) {
            IngredientsFood ingredientsFood = new IngredientsFood();
            ingredientsFood.setId_food(resultSet.getInt("ID_FOOD_INGREDIENT"));
            ingredientsFood.setId_product(resultSet.getInt("ID_PRODUCT_INGREDIENT"));
            ingredientsFood.setQuantity(resultSet.getInt("INGREDIENT_QUANTITY"));
            list.add(ingredientsFood);
        }
        return list;
    }

    public ArrayList<IngredientsFood> getIngredientsFood(int idFood) {
        ArrayList<IngredientsFood> list = new ArrayList<>();
        String query = "SELECT `ID_FOOD_INGREDIENT`,`ID_PRODUCT_INGREDIENT`, `INGREDIENT_QUANTITY`,`PRODUCT_NAME` FROM`INGREDIENTS_FOOD`,PRODUCT WHERE `ID_FOOD_INGREDIENT` = ? AND ID_PRODUCT_INGREDIENT = PRODUCT.ID_PRODUCT ";
        try {
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, idFood);
            ResultSet resultSet = preparedStmt.executeQuery();
            while (resultSet.next()) {
                IngredientsFood ingredientsFood = new IngredientsFood();
                ingredientsFood.setId_food(resultSet.getInt("ID_FOOD_INGREDIENT"));
                ingredientsFood.setId_product(resultSet.getInt("ID_PRODUCT_INGREDIENT"));
                ingredientsFood.setQuantity(resultSet.getInt("INGREDIENT_QUANTITY"));
                ingredientsFood.setProduct_name(resultSet.getString("PRODUCT_NAME"));
                list.add(ingredientsFood);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}

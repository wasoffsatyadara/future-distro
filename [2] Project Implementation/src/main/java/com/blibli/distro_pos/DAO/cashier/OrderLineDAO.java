package com.blibli.distro_pos.DAO.cashier;

import com.blibli.distro_pos.DAO.BasicDAO;
import com.blibli.distro_pos.DAO.MyConnection;
import com.blibli.distro_pos.Model.cashier.OrderLine;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderLineDAO extends MyConnection implements BasicDAO<OrderLine, String> {
    @Override
    public List<OrderLine> getAll() {
        String sql = "SELECT * FROM orderline;";
        List<OrderLine> list = new ArrayList<>();
        try {
            this.connect();
            Statement statement = this.con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet != null) {
                while (resultSet.next()) {
                    OrderLine orderLine = new OrderLine(
                            resultSet.getString("id_orderline"),
                            resultSet.getString("id_trans"),
                            resultSet.getString("id_item"),
                            resultSet.getDouble("item_price"),
                            resultSet.getDouble("subtotal"),
                            resultSet.getInt("quantity")
                    );
                    list.add(orderLine);
                }
            }
            this.disconnect();
        } catch (Exception e) {
            System.out.println("#FETCH# something error : " + e.toString());
        }
        return list;
    }

    @Override
    public OrderLine getOne(String id) {
        String sql = "SELECT * FROM orderline WHERE id_orderline = '" + id + "';";
        OrderLine orderLine = new OrderLine();
        try {
            this.connect();
            Statement statement = this.con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet != null) {
                while (resultSet.next()) {
                    orderLine = new OrderLine(
                            resultSet.getString("id_orderline"),
                            resultSet.getString("id_trans"),
                            resultSet.getString("id_item"),
                            resultSet.getDouble("item_price"),
                            resultSet.getDouble("subtotal"),
                            resultSet.getInt("quantity")
                    );
                }
            }
            this.disconnect();
        } catch (Exception e) {
            System.out.println("#GET# something error : " + e.toString());
        }
        return orderLine;
    }

    @Override
    public void save(OrderLine orderLine) {
        String sql = "INSERT INTO orderline(id_orderline, id_trans, id_item, item_price, quantity, subtotal) " +
                "VALUES(?,?,?,?,?,?)";
        try {
            this.connect();
            PreparedStatement preparedStatement = this.con.prepareStatement(sql);
            preparedStatement.setString(1, orderLine.getId_orderline());
            preparedStatement.setString(2, orderLine.getId_trans());
            preparedStatement.setString(3, orderLine.getId_item());
            preparedStatement.setDouble(4, orderLine.getItem_price());
            preparedStatement.setInt(5, orderLine.getQuantity());
            preparedStatement.setDouble(6, orderLine.getSubtotal());
            preparedStatement.execute();
            this.disconnect();
        } catch (Exception e) {
            System.out.println("#SAVE# something error : " + e.toString());
        }
    }

    @Override
    public void update(OrderLine orderLine) {
        String sql = "UPDATE orderline SET quantity = ? WHERE id_orderline = ?";
        try {
            this.connect();
            PreparedStatement preparedStatement = this.con.prepareStatement(sql);
            preparedStatement.setInt(1, orderLine.getQuantity());
            preparedStatement.setString(2, orderLine.getId_orderline());
            this.disconnect();
        } catch (Exception e) {
            System.out.println("#UPDATE# something error : " + e.toString());
        }
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void softDelete(String id) {

    }

    @Override
    public int count() {
        return 0;
    }

    @Override
    public List<OrderLine> paginate(int page) {
        return null;
    }
}
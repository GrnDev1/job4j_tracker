package ru.job4j.tracker.store;

import ru.job4j.tracker.Item;
import ru.job4j.tracker.Store;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SqlTracker implements Store {

    private Connection cn;

    public SqlTracker() {
        init();
    }

    public SqlTracker(Connection cn) {
        this.cn = cn;
    }

    private void createTable() {
        String sql =
                "CREATE TABLE IF NOT EXISTS items ("
                        + "id SERIAL PRIMARY KEY,"
                        + "name text,"
                        + "created timestamp)";
        loadStatement(sql);
    }

    private void dropTable() {
        String sql = "DROP TABLE items;";
        loadStatement(sql);
    }

    private void loadStatement(String sql) {
        try (PreparedStatement statement = cn.prepareStatement(sql)) {
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() {
        try (InputStream in = new FileInputStream("db/liquibase.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            cn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
            createTable();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() throws SQLException {
        if (cn != null) {
            dropTable();
            cn.close();
        }
    }

    @Override
    public Item add(Item item) {
        String sql =
                "INSERT INTO items(name, created) "
                        + "VALUES (?, ?)";
        try (PreparedStatement statement = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, item.getName());
            statement.setTimestamp(2, Timestamp.valueOf(item.getDateTime()));
            statement.execute();
            try (ResultSet set = statement.getGeneratedKeys()) {
                if (set.next()) {
                    item.setId(set.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        boolean result = false;
        String sql =
                "UPDATE items "
                        + "SET name = ?, created = ?"
                        + "WHERE id = ?";
        try (PreparedStatement statement = cn.prepareStatement(sql)) {
            statement.setString(1, item.getName());
            statement.setTimestamp(2, Timestamp.valueOf(item.getDateTime()));
            statement.setInt(3, id);
            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean delete(int id) {
        boolean result = false;
        String sql =
                "DELETE FROM items "
                        + "WHERE id = ?";
        try (PreparedStatement statement = cn.prepareStatement(sql)) {
            statement.setInt(1, id);
            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static Item getItemWithNewTimestamp(ResultSet set) throws SQLException {
        Item temp = new Item(set.getInt("id"), set.getString("name"));
        temp.setDateTime(set.getTimestamp("created"));
        return temp;
    }

    @Override
    public List<Item> findAll() {
        List<Item> list = new ArrayList<>();
        String sql =
                "SELECT * FROM items";
        try (PreparedStatement statement = cn.prepareStatement(sql);
             ResultSet set = statement.executeQuery()) {
            while (set.next()) {
                Item temp = getItemWithNewTimestamp(set);
                list.add(temp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> list = new ArrayList<>();
        String sql =
                "SELECT * FROM items WHERE name = ?";
        try (PreparedStatement statement = cn.prepareStatement(sql)) {
            statement.setString(1, key);
            try (ResultSet set = statement.executeQuery()) {
                while (set.next()) {
                    Item temp = getItemWithNewTimestamp(set);
                    list.add(temp);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Item findById(int id) {
        Item result = null;
        String sql =
                "SELECT * FROM items WHERE id = ?";
        try (PreparedStatement statement = cn.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet set = statement.executeQuery()) {
                if (set.next()) {
                    result = getItemWithNewTimestamp(set);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
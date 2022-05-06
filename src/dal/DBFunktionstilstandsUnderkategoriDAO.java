package dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBFunktionstilstandsUnderkategoriDAO {

    private DBConnecting dbConnecting;

    public DBFunktionstilstandsUnderkategoriDAO(DBConnecting dbConnecting) {
        this.dbConnecting = dbConnecting;
    }

    public List<String> getFunktionstilstandsUnderkategoriList() {
        List<String> funktionstilstandsUnderkategoriList = new ArrayList<>();
        try (Connection connection = dbConnecting.getConnection()) {
            String sql = "SELECT * FROM [FS_Underkategori]";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String underkategori = rs.getString("Titel");
                funktionstilstandsUnderkategoriList.add(underkategori);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return funktionstilstandsUnderkategoriList;
    }


}

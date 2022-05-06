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
        List<String> funktionstilstandList = new ArrayList<>();
        try (Connection connection = dbConnecting.getConnection()) {
            String sql = "SELECT * FROM [FS_Overkategori]";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String overkategori = rs.getString("Titel");
                funktionstilstandList.add(overkategori);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return funktionstilstandList;
    }


}

package dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBHelbredstilstandsUnderkategoriDAO {

    private DBConnecting dbConnecting;

    public DBHelbredstilstandsUnderkategoriDAO(DBConnecting dbConnecting) {
        this.dbConnecting = dbConnecting;
    }

    public List<String> getHelbredstilstandsUnderkategoriList() {
        List<String> helbredstilstandsUnderkategoriList = new ArrayList<>();
        try (Connection connection = dbConnecting.getConnection()) {
            String sql = "SELECT * FROM [HS_Underkategori]";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String underkategori = rs.getString("HS_Underkategori_Titel");
                helbredstilstandsUnderkategoriList.add(underkategori);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return helbredstilstandsUnderkategoriList;
    }

}

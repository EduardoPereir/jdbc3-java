import java.nio.file.ReadOnlyFileSystemException;
import java.sql.*;

public class Program {
    public static void main(String[] args) {

        Connection conn = null;
        Connection conn2 = null;
        PreparedStatement pst = null;
        Statement st = null;
        ResultSet rs = null;

        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdatabase", "root", "eduardop$filho");
            pst = conn.prepareStatement("UPDATE seller "
            + "SET BaseSalary = BaseSalary + ? "
            + " WHERE (DepartmentId = ?)");

            pst.setDouble(1, 200.0);
            pst.setInt(2, 2);

            int rowsAffected = pst.executeUpdate();
            System.out.println("Done! Rows affected: " + rowsAffected);
            System.out.println("\nEmployees Updated: ");

            conn2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdatabase", "root", "eduardop$filho");

            st = conn2.createStatement();
            rs = st.executeQuery("SELECT Name, BaseSalary FROM seller WHERE (DepartmentId = 2) ");

            while(rs.next()) {
                System.out.println(rs.getString(1) + ", R$" + rs.getDouble(2 ));
            }

        }catch (SQLException e ){
            e.printStackTrace();
        }

    }
}
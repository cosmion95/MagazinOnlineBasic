import java.sql.*;
import java.util.ArrayList;

public class dbOperations {

    static final String URL = "jdbc:postgresql://54.93.65.5:5432/cosminp7";
    static final String USERNAME = "fasttrackit_dev";
    static final String PASSWORD = "fasttrackit_dev";

    public void adaugareProdus(int id) throws ClassNotFoundException, SQLException {
        // 1. loadg driver, no longer needed in new versions of JDBC\n" +
        Class.forName("org.postgresql.Driver");

        // 3. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        String sql = "SELECT nume, pret, id FROM produse WHERE id = ?";
        PreparedStatement pSt = conn.prepareStatement(sql);
        pSt.setInt(1, id);
        ResultSet rs = pSt.executeQuery();

        String nume = "";
        Double pret = 0.0;
        while (rs.next()) {
            nume = rs.getString("nume");
            pret = rs.getDouble("pret");
        }


        sql = "SELECT id FROM cos WHERE id = ?";
        pSt = conn.prepareStatement(sql);
        pSt.setInt(1,id);
        rs = pSt.executeQuery();
        int idProd = 0;
        while (rs.next()) {
            idProd = rs.getInt("id");
        }

        if (idProd != id) {
            sql = "INSERT INTO cos (nume, pret, id, cantitate) VALUES (?,?,?,?)";
            pSt = conn.prepareStatement(sql);
            pSt.setString(1, nume);
            pSt.setDouble(2, pret);
            pSt.setInt(3, id);
            pSt.setInt(4, 1);
            pSt.executeUpdate();
            System.out.println("Produsul a fost adaugat");
        }

        else {
            sql = "UPDATE cos SET cantitate = cantitate + 1 WHERE id = ?";
            pSt = conn.prepareStatement(sql);
            pSt.setInt(1,id);
            pSt.executeUpdate();
            System.out.println("Cantitatea a fost marita");
        }




        rs.close();
        pSt.close();
        conn.close();

    }

    public ArrayList<produs> afisareProduse() throws ClassNotFoundException, SQLException {
        // 1. loadg driver, no longer needed in new versions of JDBC\n" +
        Class.forName("org.postgresql.Driver");

        // 3. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT nume, pret, id FROM produse ORDER BY id");

        ArrayList<produs> listaProduse = new ArrayList<produs>();
        while (rs.next()) {
            produs prod = new produs(rs.getString("nume").trim(),rs.getDouble("pret"), rs.getInt("id"));
            listaProduse.add(prod);
        }

        rs.close();
        conn.close();
        st.close();

        return listaProduse;
    }

    public ArrayList<produs> afisareCos() throws ClassNotFoundException, SQLException {
        // 1. loadg driver, no longer needed in new versions of JDBC\n" +
        Class.forName("org.postgresql.Driver");

        // 3. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT nume, pret, id, cantitate FROM cos ORDER BY id");

        ArrayList<produs> listaProduse = new ArrayList<produs>();
        while (rs.next()) {
            produs prod = new produs(rs.getString("nume").trim(),rs.getDouble("pret"), rs.getInt("id"));
            prod.setCantitate(rs.getInt("cantitate"));
            listaProduse.add(prod);
        }

        rs.close();
        conn.close();
        st.close();

        return listaProduse;
    }

    public void stergeCos(int id) throws ClassNotFoundException, SQLException {
        // 1. loadg driver, no longer needed in new versions of JDBC\n" +
        Class.forName("org.postgresql.Driver");

        // 3. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        String sql = "SELECT cantitate FROM cos WHERE id = ?";
        PreparedStatement pSt = conn.prepareStatement(sql);
        pSt.setInt(1,id);
        ResultSet rs = pSt.executeQuery();
        int cantitate = 0;
        while (rs.next()) {
            cantitate = rs.getInt("cantitate");
        }

        if (cantitate > 1) {
            sql = "UPDATE cos SET cantitate = cantitate -1 WHERE id = ?";
            pSt = conn.prepareStatement(sql);
            pSt.setInt(1,id);
            pSt.executeUpdate();
            System.out.println("Am scazut cantitatea cu 1");
        }
        else if(cantitate == 1) {
            sql = "DELETE FROM cos WHERE id = ?";
            pSt = conn.prepareStatement(sql);
            pSt.setInt(1,id);
            pSt.executeUpdate();
            System.out.println("Am sters produsul din cos");
        }


        rs.close();
        pSt.close();
        conn.close();

    }

}


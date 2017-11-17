package kata5p1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Kata5P1 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        Kata5P1 kata5 = new Kata5P1();
        
        kata5.primeraParte();
        kata5.segundaParte();
    }
    
    private void primeraParte() throws ClassNotFoundException, SQLException {
        String baseDatePath = "C:/Users/Entrar/Downloads/SQLiteDatabaseBrowserPortable/Data/kata5.db";
        Class.forName("org.sqlite.JDBC");
        Connection connect = DriverManager.getConnection("jdbc:sqlite:" + baseDatePath); 
        
        Statement st = connect.createStatement();
        
        String query = "SELECT * FROM PEOPLE";
        ResultSet rs = st.executeQuery(query);
        
        while(rs.next()) {
            System.out.println(rs.getInt(1));
            System.out.println(rs.getString(2));
        }
        
        rs.close();
        st.close();
        connect.close();
    }
    
    private void segundaParte() throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
        String baseDatePath = "C:/Users/Entrar/Downloads/SQLiteDatabaseBrowserPortable/Data/kata5.db";
        Class.forName("org.sqlite.JDBC");
        Connection connect = DriverManager.getConnection("jdbc:sqlite:" + baseDatePath); 
        
        Statement st = connect.createStatement();
        
        String query;
        
        query = "CREATE TABLE IF NOT EXISTS MAIL"
                + "("
                + "'id' INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " 'mail' TEXT NOT NULL"
                + ")";
        
        st.execute(query);
        
        String pathFileEmail = "C:\\Users\\Entrar\\Downloads\\ficheros\\emails.txt";
        BufferedReader br = new BufferedReader(new FileReader(new File(pathFileEmail)));
        
        String mail;
        while((mail = br.readLine()) != null) {
            if (!mail.contains("@")) continue;
            query = "INSERT INTO MAIL (mail) VALUES ('" + mail + "')";
            st.execute(query);
        }
               
        st.close();
        connect.close();
    }
}

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UrunIslemleri {
    private Connection con = null;
    static int qqq = 1;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    
    public  ArrayList<Urun> urunleriGetir(){
    
        ArrayList<Urun> cikti = new ArrayList<Urun>();
        
        try {
            statement = con.createStatement();
            
            String sorgu = "Select * From urunler";
            
            ResultSet rs = statement.executeQuery(sorgu);
            
            while (rs.next()) {
                int ID = rs.getInt("ID");
                String  urun = rs.getString("urun");
                String kategori = rs.getString("kategori");
                String fiyat = rs.getString("fiyat");
                
                cikti.add(new Urun(ID,urun,kategori,fiyat));
            }
            return cikti;
        } catch (SQLException ex) {
            Logger.getLogger(UrunIslemleri.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public void urunSil(int ID) {
    
        String sorgu = "Delete from urunler where ID = ?";
        
        try {
            preparedStatement = con.prepareStatement(sorgu);
            preparedStatement.setInt(1, ID);
            
            preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(UrunIslemleri.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    
    }
    public void urunEkle(String ad,String kategori,String fiyat) {
        
        String sorgu = "Insert Into urunler (urun,kategori,fiyat) VALUES(?,?,?)";
        
        try {
            preparedStatement = con.prepareStatement(sorgu);
            
            
            preparedStatement.setString(1, ad);
            preparedStatement.setString(2, kategori);
            preparedStatement.setString(3, fiyat);
           
            
            preparedStatement.executeUpdate();
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(UrunIslemleri.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
        
    }
    public void urunGuncelle(int ID,String yeni_ad,String yeni_kategori,String yeni_fiyat) {
        
        String sorgu =  "Update urunler set urun = ? , kategori = ? , fiyat = ?  where ID = ?";
        
        try {
            preparedStatement = con.prepareStatement(sorgu);
            
            
            preparedStatement.setString(1,yeni_ad);
            preparedStatement.setString(2,yeni_kategori);
            
            preparedStatement.setString(3,yeni_fiyat);
           
            
            preparedStatement.setInt(4, ID);
            
            preparedStatement.executeUpdate();
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(UrunIslemleri.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public boolean girisYap(String kullanici_adi,String parola){
    
        String sorgu = "Select * From adminler where username = ? and parola = ?";
    
        try {
            preparedStatement = con.prepareStatement(sorgu);
            
            preparedStatement.setString(1,kullanici_adi);
            preparedStatement.setString(2,parola);
            
            ResultSet rs = preparedStatement.executeQuery();

             return rs.next();
             
        } catch (SQLException ex) {
            Logger.getLogger(UrunIslemleri.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
   public UrunIslemleri(){
    String url = "jdbc:mysql://" + Database.host + ":" + Database.port + "/" + Database.db_ismi+ "?useUnicode=true&characterEncoding=utf8";
        
        
        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver Bulunamadı....");
        }
        
        
        try {
            con = DriverManager.getConnection(url, Database.kullanici_adi, Database.parola);
            System.out.println("Bağlantı Başarılı...");
            
            
        } catch (SQLException ex) {
            System.out.println("Bağlantı Başarısız...");
            //ex.printStackTrace();
        }
        
        
    }
    public static void main(String[] args){
        UrunIslemleri islemler = new UrunIslemleri();
    
    
    }


}







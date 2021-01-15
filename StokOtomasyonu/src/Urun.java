
public class Urun {
    
    private int ID;
    
    private String urun;
    
    private String kategori;
    
    private String fiyat;

    public Urun(int ID,String urun, String kategori, String fiyat) {
        this.ID = ID;
        this.urun = urun;       
        this.kategori = kategori;
        this.fiyat = fiyat;
    }
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    public String getUrun() {
        return urun;
    }

    public void setUrun(String urun) {
        this.urun = urun;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getFiyat() {
        return fiyat;
    }

    public void setFiyat(String fiyat) {
        this.fiyat = fiyat;
    }
    
    
    
    
}

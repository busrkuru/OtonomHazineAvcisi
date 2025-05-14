package prolab2_1;

public class Konum {

    private int x1; 
    private int y1; 
    private int genislik; 
    private int yukseklik; 
    
    public Konum(int x1, int y1, int genislik, int yukseklik) {
        this.x1 = x1;
        this.y1 = y1;
        this.genislik = genislik;
        this.yukseklik = yukseklik;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getGenislik() {
        return genislik;
    }

    public void setGenislik(int genislik) {
        this.genislik = genislik;
    }

    public int getYukseklik() {
        return yukseklik;
    }

    public void setYukseklik(int yukseklik) {
        this.yukseklik = yukseklik;
    }
}

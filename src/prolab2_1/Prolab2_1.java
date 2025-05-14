package prolab2_1;

import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;

public class Prolab2_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Haritanın genişliğini girin: ");
        int genislik = scanner.nextInt();
        System.out.print("Haritanın yüksekliğini girin: ");
        int yukseklik = scanner.nextInt();
        scanner.close();

        Harita harita = new Harita(genislik, yukseklik);
        Konum baslangicKonumu = new Konum(0, 0, genislik/2, yukseklik/2);
        Karakter karakter = new Karakter(1, "Karakter Adı", baslangicKonumu);

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                HaritaGorsel haritaGorsel = new HaritaGorsel(harita.getTiles(), karakter);
                haritaGorsel.setVisible(true);
            }
        });
    }
}

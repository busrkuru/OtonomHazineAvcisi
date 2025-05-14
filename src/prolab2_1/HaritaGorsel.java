package prolab2_1;

import javax.swing.*;
import java.awt.*;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class HaritaGorsel extends javax.swing.JFrame {

    private Tile[][] tiles;
    private static final int TILE_SIZE = 25; 
    private JPanel gridPanel; 
    private Timer timer;
    private Karakter karakter;

    public HaritaGorsel(Tile[][] tiles, Karakter karakter) {
        this.tiles = tiles;
        this.karakter = karakter;
        initializeUI();
        initializeTimer();
    }

    private void initializeUI() {
        setTitle("Otonom Hazine Avcısı Haritası");
        setSize(tiles[0].length * TILE_SIZE + 50, tiles.length * TILE_SIZE + 50);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(tiles.length, tiles[0].length));
        fillGridPanel();

        add(new JScrollPane(gridPanel), BorderLayout.CENTER);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void fillGridPanel() {
        gridPanel.removeAll();
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                JPanel tilePanel = new JPanel();
                tilePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                tilePanel.setPreferredSize(new Dimension(TILE_SIZE, TILE_SIZE));

                if (karakter.getKonum().getX1() == j && karakter.getKonum().getY1() == i) {
                    ImageIcon icon = karakter.getKarakterGorseli();
                    if (icon != null) {
                        JLabel karakterLabel = new JLabel(new ImageIcon(icon.getImage().getScaledInstance(TILE_SIZE, TILE_SIZE, Image.SCALE_SMOOTH)));
                        tilePanel.add(karakterLabel);
                    }
                } else if (tiles[i][j].getEngel() != null) {
                    tilePanel.add(new JLabel(new ImageIcon(tiles[i][j].getEngel().getIcon().getImage().getScaledInstance(TILE_SIZE, TILE_SIZE, Image.SCALE_SMOOTH))));
                } else if (tiles[i][j].hazine_var_mi()) {
                    tilePanel.add(new JLabel(new ImageIcon(tiles[i][j].getHazine().getIcon().getImage().getScaledInstance(TILE_SIZE, TILE_SIZE, Image.SCALE_SMOOTH))));
                } else {
                    tilePanel.setBackground(Color.GREEN);
                }
                gridPanel.add(tilePanel);
            }
        }

        gridPanel.revalidate();
        gridPanel.repaint();
    }

    private void initializeTimer() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveObstacles();
                fillGridPanel();
            }
        });
        timer.start();
    }

    private void moveObstacles() {
        for (Tile[] row : tiles) {
            for (Tile tile : row) {
                if (tile.getEngel() instanceof HareketliEngeller) {
                    ((HareketliEngeller) tile.getEngel()).hareket();
                }
            }
        }
    }

    public HaritaGorsel() {
        initComponents();
    }

    private JPanel createObstaclePanel(Engel obstacle) {
       
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        panel.setPreferredSize(new Dimension(TILE_SIZE, TILE_SIZE)); // Panelin boyutunu sabit bir değere ayarla

        if (obstacle != null) {
            JLabel label = new JLabel();
            ImageIcon icon = null;

            // Obstacle türüne göre ImageIcon ayarla
            if (obstacle instanceof Agac || obstacle instanceof Dag || obstacle instanceof Kaya || obstacle instanceof Duvar || obstacle instanceof Kus) {
                icon = new ImageIcon(obstacle.getIcon().getImage().getScaledInstance(TILE_SIZE, TILE_SIZE, Image.SCALE_SMOOTH));
            }

            if (icon != null) {
                label.setIcon(icon);
                panel.add(label);
            }
        } else {
            panel.setBackground(Color.GREEN); // Eğer engel yoksa, arkaplanı yeşil yap
        }

        return panel;
         /*
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        JLabel label = new JLabel();

        if (obstacle instanceof Tree) {
            label.setIcon(new ImageIcon(((Tree) obstacle).getIcon().getImage().getScaledInstance(TILE_SIZE * obstacle.getLocation().getWidth(), TILE_SIZE * obstacle.getLocation().getHeight(), Image.SCALE_SMOOTH)));
        } else if (obstacle instanceof Mountain) {
            label.setIcon(new ImageIcon(((Mountain) obstacle).getIcon().getImage().getScaledInstance(TILE_SIZE * obstacle.getLocation().getWidth(), TILE_SIZE * obstacle.getLocation().getHeight(), Image.SCALE_SMOOTH)));
        }

        panel.add(label);
        return panel;
         */
    }

    private JPanel createTreasurePanel(Hazine treasure) {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        panel.add(new JLabel(new ImageIcon(treasure.getIcon().getImage().getScaledInstance(TILE_SIZE, TILE_SIZE, Image.SCALE_SMOOTH))));
        return panel;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel1 = new java.awt.Panel();

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HaritaGorsel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HaritaGorsel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HaritaGorsel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HaritaGorsel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HaritaGorsel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Panel panel1;
    // End of variables declaration//GEN-END:variables

}

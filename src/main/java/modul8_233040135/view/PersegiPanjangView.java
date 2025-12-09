/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modul8_233040135.view;

/**
 *
 * @author raiha
 * 
 */

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class PersegiPanjangView extends JFrame {
    // Komponen UI sebagai atribut
    private JTextField txtPanjang = new JTextField(10);
    private JTextField txtLebar = new JTextField(10);
    private JLabel lblHasilluas = new JLabel("-");
    private JButton btnHitung = new JButton("Hitung Luas");
    private JButton btnHitungKel = new JButton("Hitung Keliling");
    private JLabel lblHasilkeliling = new JLabel("-"); 
    private JButton btnReset = new JButton("Reset");
    
    public PersegiPanjangView() {
        // Inisialisasi UI
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(350, 300);
        this.setLayout(new GridLayout(4, 2, 10, 10)); // Grid 4 baris
        this.setTitle("MVC Kalkulator");
        
        this.add(new JLabel("Panjang:"));
        this.add(txtPanjang);
        this.add(new JLabel("Lebar:"));
        this.add(txtLebar);
        this.add(new JLabel("Hasil Luas:"));
        this.add(lblHasilluas);
        this.add(new JLabel("Hasil Keliling:"));
        this.add(lblHasilkeliling);
        this.add(new JLabel("")); // Spacer kosong
        this.add(btnHitung);
        this.add(btnHitungKel);
        this.add(btnReset);
        this.add(new JLabel(""));
    }
    
    
    // Mengambil nilai panjang dari Textfield
        public double getPanjang() {
            return Double.parseDouble(txtPanjang.getText());
        }
        
    // Mengambil nilai lebar dari Textfield
        public double getLebar() {
            return Double.parseDouble(txtLebar.getText());
        }
        
    // Menampilkan hasil ke label
        public void setHasilLuas(double hasil) {
            lblHasilluas.setText(String.valueOf(hasil));
        }
        
        public void setHasilKeliling(double hasil) {
        lblHasilkeliling.setText(String.valueOf(hasil));
        }
        
        public void clearOutput() {
        txtPanjang.setText("");
        txtLebar.setText("");
        lblHasilluas.setText("-");
        lblHasilkeliling.setText("-");
        }   
        
        
    // Menampilkan pesan error (jika input bukan angka)
        public void tampilkanPesanError(String pesan) {
            JOptionPane.showMessageDialog(this, pesan);
        }
        
   // 1. Listener untuk Luas
    public void addHitungLuasListener(ActionListener listener) {
        btnHitung.addActionListener(listener);
    }
    
    // 2. Listener untuk Keliling
    public void addHitungKelilingListener(ActionListener listener) {
        btnHitungKel.addActionListener(listener);
    }
    
    // 3. Listener untuk Reset
    public void addResetListener(ActionListener listener) {
        btnReset.addActionListener(listener);
    }
        
}

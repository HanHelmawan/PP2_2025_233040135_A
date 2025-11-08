/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modul6_233040135;

import java.awt.FlowLayout; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author raiha
 */
 public class LatihanKonverterSuhu {

    public static void main(String[] args) {
        // 1. Buat Frame
        JFrame frame = new JFrame("Konverter Suhu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 150); // Ukuran disesuaikan
        frame.setLayout(new FlowLayout());

        // 2. Buat komponen-komponen yang dibutuhkan 
        // Komponen untuk input Celcius
        JLabel labelCelcius = new JLabel("Celcius:"); 
        JTextField fieldCelcius = new JTextField(10); 
        // Komponen tombol
        JButton buttonKonversi = new JButton("Konversi");

        // Komponen untuk hasil Fahrenheit
        JLabel labelFahrenheit = new JLabel("Fahrenheit:");
        JLabel labelHasil = new JLabel("...");  

        // 3. Buat Event Listener (menggunakan anonymous inner class)
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logika yang dieksekusi saat tombol diklik
                try {
                    // Ambil teks dari JTextField 
                    String inputCelcius = fieldCelcius.getText();
                    
                    // Ubah teks ke double 
                    double celcius = Double.parseDouble(inputCelcius);
                    
                    // Hitung Fahrenheit 
                    double fahrenheit = (celcius * 9.0 / 5.0) + 32;

                    labelHasil.setText(String.format("%.2f", fahrenheit));
                    
                } catch (NumberFormatException ex) {
                    // Penanganan jika input bukan angka 
                    labelHasil.setText("Input salah!");
                }
            }
        };

        // 4. Daftarkan listener ke source (tombol)
        buttonKonversi.addActionListener(listener);

        // 5. Tambahkan semua komponen ke frame
        frame.add(labelCelcius);
        frame.add(fieldCelcius);
        frame.add(buttonKonversi);
        frame.add(labelFahrenheit);
        frame.add(labelHasil);

        // 6. Tampilkan frame
        frame.setVisible(true);
    }
}  

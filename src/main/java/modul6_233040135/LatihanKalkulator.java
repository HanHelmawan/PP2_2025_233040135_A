/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modul6_233040135;

import java.awt.BorderLayout;
import java.awt.GridLayout; 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel; 
import javax.swing.JTextField; 

/**
 *
 * @author raiha
 */
public class LatihanKalkulator {

    public static void main(String[] args) {
        // 1. Buat Frame (Window)
        // Menggunakan BorderLayout secara default 
        JFrame frame = new JFrame("Kalkulator Sederhana");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400); 

        // 2. Buat komponen Layar (JTextField)
        // Ditempatkan di bagian atas (NORTH) 
        JTextField layar = new JTextField();
        layar.setEditable(false); 
        // Menambahkan layar ke frame di wilayah NORTH
        frame.add(layar, BorderLayout.NORTH); 

        // 3. Buat Panel untuk tombol-tombol
        // Panel ini akan diletakkan di CENTER frame
        JPanel panel = new JPanel();
        
        // 4. Atur layout panel tombol menjadi GridLayout 4 baris, 4 kolom
        // Ini untuk menampung 16 tombol 
        panel.setLayout(new GridLayout(4, 4, 5, 5)); // 4 baris, 4 kolom, 5px h-gap, 5px v-gap

        // 5. Tambahkan 16 tombol ke panelTombol
        // (Tombol 0-9 dan operator +, -, *, /) 
        
        // Baris 1
        panel.add(new JButton("7"));
        panel.add(new JButton("8"));
        panel.add(new JButton("9"));
        panel.add(new JButton("/"));
        
        // Baris 2
        panel.add(new JButton("4"));
        panel.add(new JButton("5"));
        panel.add(new JButton("6"));
        panel.add(new JButton("*"));
        
        // Baris 3
        panel.add(new JButton("1"));
        panel.add(new JButton("2"));
        panel.add(new JButton("3"));
        panel.add(new JButton("-"));
        
        // Baris 4
        panel.add(new JButton("0"));
        panel.add(new JButton("C")); // Tombol Clear
        panel.add(new JButton("=")); // Tombol Equals
        panel.add(new JButton("+"));

        // 6. Tambahkan panel tombol ke Frame di wilayah CENTER
        // Wilayah CENTER akan mengisi sisa ruang 
        frame.add(panel, BorderLayout.CENTER); 

        // 7. Tampilkan frame
        frame.setVisible(true); 
    }
}

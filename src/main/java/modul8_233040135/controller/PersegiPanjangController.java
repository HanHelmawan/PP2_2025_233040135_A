/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modul8_233040135.controller;

/**
 *
 * @author raiha
 */


import modul8_233040135.model.PersegiPanjangModel;
import modul8_233040135.view.PersegiPanjangView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PersegiPanjangController {
    // Model dan View sebagai atribut kelas
    private PersegiPanjangModel model;
    private PersegiPanjangView view;
    
    public PersegiPanjangController(PersegiPanjangModel model, PersegiPanjangView view) {
        this.model = model;
        this.view = view;
        
        // Menghubungkan tombol di View dengan logic di Controller
        // Mendaftarkan 3 Listener terpisah
        this.view.addHitungLuasListener(new HitungLuasListener());
        this.view.addHitungKelilingListener(new HitungKelilingListener());
        
        // Listener tombol reset
        this.view.addResetListener(new ResetListener());
    }
         
    
   // --- LOGIKA 1: HANYA MENGHITUNG LUAS ---
    class HitungLuasListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double p = view.getPanjang();
                double l = view.getLebar();

                model.setPanjang(p);
                model.setLebar(l);
                model.hitungLuas(); // Hanya hitung luas

                view.setHasilLuas(model.getLuas()); // Hanya update label luas
            } catch (NumberFormatException ex) {
                view.tampilkanPesanError("Masukkan angka valid!");
            }
        }
    }
    
    // --- LOGIKA 2: HANYA MENGHITUNG KELILING ---
    class HitungKelilingListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double p = view.getPanjang();
                double l = view.getLebar();

                model.setPanjang(p);
                model.setLebar(l);
                model.hitungKeliling(); // Hanya hitung keliling

                view.setHasilKeliling(model.getKeliling()); // Hanya update label keliling
            } catch (NumberFormatException ex) {
                view.tampilkanPesanError("Masukkan angka valid!");
            }
        }
    }
   
    // Inner class untuk reset
        class ResetListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Memanggil method clear di View
            view.clearOutput();
        }
    }
}

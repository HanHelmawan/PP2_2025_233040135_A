/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modul8_233040135.model;

/**
 *
 * @author raiha
 */
public class PersegiPanjangModel {
    
    private double panjang;
    private double lebar;
    private double luas;
    private double keliling;
    
    // Menghitung luas (Logika Bisnis)
    public void hitungLuas() {
        this.luas = this.panjang * this.lebar;
    }
    
    // Menghitung keliling
    public void hitungKeliling() {
        this.keliling = 2 * (this.panjang + this.lebar);
    }
    
    // Getter & Setter
    public void setPanjang(double panjang) {
        this.panjang = panjang;
    }
    
    public void setLebar(double lebar) {
        this.lebar = lebar;
    }
    
    public double getLuas() {
        return luas;
    }
    
    public double getKeliling() {
        return keliling;
    }
}

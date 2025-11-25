/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modul7_233040135; // Sesuaikan dengan nama package di project Anda

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Modifikasi Tugas Modul 7
 * Nama Kelas: TugasManajemenNilaiSiswaApp
 */
public class TugasManajemenNilaiSiswaApp extends JFrame {

    private JTextField txtNama;
    private JTextField txtNilai;
    private JComboBox<String> cmbMatkul;
    private JTable tableData;
    private DefaultTableModel tableModel;
    private JTabbedPane tabbedPane;

    // Method untuk membuat desain Tab Input
    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Komponen Nama
        panel.add(new JLabel("Nama Siswa:"));
        txtNama = new JTextField();
        panel.add(txtNama);

        // Komponen Mata Pelajaran
        panel.add(new JLabel("Mata Pelajaran:"));
        String[] matkul = {"Matematika Dasar", "Bahasa Indonesia",
            "Algoritma dan Pemrograman I", "Praktikum Pemrograman II"};
        cmbMatkul = new JComboBox<>(matkul);
        panel.add(cmbMatkul);

        // Komponen Nilai
        panel.add(new JLabel("Nilai (0-100):"));
        txtNilai = new JTextField();
        panel.add(txtNilai);

        // --- TUGAS NO 4: Tombol Reset ---
        JButton btnReset = new JButton("Reset Form");
        btnReset.addActionListener(e -> resetForm()); 
        panel.add(btnReset); 

        // Tombol Simpan
        JButton btnSimpan = new JButton("Simpan Data");
        panel.add(btnSimpan);

        // Event Handling Tombol Simpan
        btnSimpan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prosesSimpan();
            }
        });

        return panel;
    }

    // Method untuk membuat desain Tab Tabel
    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Setup Model Tabel (kolom)
        String[] kolom = {"Nama Siswa", "Mata Pelajaran", "Nilai", "Grade"};
        tableModel = new DefaultTableModel(kolom, 0);
        tableData = new JTable(tableModel);

        // Membungkus tabel dengan ScrollPane
        JScrollPane scrollPane = new JScrollPane(tableData);
        panel.add(scrollPane, BorderLayout.CENTER);

        // --- TUGAS NO 2: Tombol Hapus ---
        JButton btnHapus = new JButton("Hapus Data Terpilih");
        
        btnHapus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tableData.getSelectedRow();
                if (selectedRow != -1) {
                    int confirm = JOptionPane.showConfirmDialog(null, 
                            "Apakah Anda yakin ingin menghapus data ini?", 
                            "Konfirmasi Hapus", 
                            JOptionPane.YES_NO_OPTION);
                    
                    if (confirm == JOptionPane.YES_OPTION) {
                        tableModel.removeRow(selectedRow);
                        JOptionPane.showMessageDialog(null, "Data berhasil dihapus.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, 
                            "Pilih baris yang ingin dihapus terlebih dahulu!", 
                            "Error", 
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        panel.add(btnHapus, BorderLayout.SOUTH);

        return panel;
    }

    // Logika Validasi dan Penyimpanan Data
    private void prosesSimpan() {
        // 1. Ambil data dari input
        String nama = txtNama.getText();
        String matkul = (String) cmbMatkul.getSelectedItem();
        String strNilai = txtNilai.getText();

        // 2. VALIDASI INPUT
        if (nama.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama tidak boleh kosong!",
                    "Error Validasi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // --- TUGAS NO 3: Validasi Nama Minimal 3 Karakter ---
        if (nama.trim().length() < 3) {
            JOptionPane.showMessageDialog(this, "Nama minimal harus terdiri dari 3 karakter!",
                    "Error Validasi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int nilai;
        try {
            nilai = Integer.parseInt(strNilai);
            if (nilai < 0 || nilai > 100) {
                JOptionPane.showMessageDialog(this, "Nilai harus antara 0 ~ 100!",
                        "Error Validasi", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Nilai harus berupa angka!",
                    "Error Validasi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 3. Logika Bisnis (Menentukan Grade)
        String grade = "";
        
        // --- TUGAS NO 1: Menggunakan Switch Case ---
          int range = nilai / 10;
        
            switch (range) {
                case 10: // Untuk nilai 100
                case 9:  // Untuk nilai 90-99
                case 8:  // Untuk nilai 80-89
                    grade = "A";
                    break; 

                case 7:  // Untuk nilai 70-79
                    grade = "AB";
                    break;

                case 6:  // Untuk nilai 60-69
                    grade = "B";
                    break;

                case 5:  // Untuk nilai 50-59
                    grade = "BC";
                    break;

                case 4:  // Untuk nilai 40-49
                    grade = "C";
                    break;

                case 3:  // Untuk nilai 30-39
                    grade = "D";
                    break;

                default: // Untuk nilai 0-29 (range 0, 1, 2)
                    grade = "E";
                    break;
            }

        // 4. Masukkan ke Tabel
        Object[] dataBaris = {nama, matkul, nilai, grade};
        tableModel.addRow(dataBaris);

        // 5. Reset Form dan Pindah Tab
        resetForm();
        JOptionPane.showMessageDialog(this, "Data Berhasil Disimpan!");
        tabbedPane.setSelectedIndex(1);
    }

    private void resetForm() {
        txtNama.setText("");
        txtNilai.setText("");
        cmbMatkul.setSelectedIndex(0);
        txtNama.requestFocus();
    }

    // --- Constructor disesuaikan dengan nama kelas ---
    public TugasManajemenNilaiSiswaApp() {
        setTitle("Aplikasi Manajemen Nilai Siswa");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 

        tabbedPane = new JTabbedPane();

        JPanel panelInput = createInputPanel();
        tabbedPane.addTab("Input Data", panelInput);

        JPanel panelTabel = createTablePanel();
        tabbedPane.addTab("Daftar Nilai", panelTabel);

        add(tabbedPane);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // --- Instansiasi disesuaikan dengan nama kelas ---
            new TugasManajemenNilaiSiswaApp().setVisible(true);
        });
    }
}
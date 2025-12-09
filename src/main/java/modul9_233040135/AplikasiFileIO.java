/*
 * Perbaikan Latihan 1 - Modul 09
 */
package modul9_233040135; // Sesuaikan dengan package Anda

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class AplikasiFileIO extends JFrame {
    // Komponen UI
    private JTextArea textArea;
    private JButton btnOpenText, btnSaveText, btnAppendText;
    private JButton btnSaveBinary, btnLoadBinary;
    private JButton btnSaveObject, btnLoadObject;
    private JFileChooser fileChooser;
    
    public AplikasiFileIO() {
        super("Tutorial File IO & Exception Handling");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Inisialisasi Komponen
        textArea = new JTextArea();
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        fileChooser = new JFileChooser();
        
        // Panel Tombol
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 4, 5, 5));
        btnOpenText = new JButton("Buka Text");
        btnSaveText = new JButton("Simpan Text");
        btnAppendText = new JButton("Simpan (Append)"); // [LATIHAN 4]
        btnSaveBinary = new JButton("Simpan Config (Binary)");
        btnLoadBinary = new JButton("Muat Config (Binary)");
        btnSaveObject = new JButton("Simpan Objek"); // [LATIHAN 3]
        btnLoadObject = new JButton("Muat Objek");   // [LATIHAN 3]
        
        buttonPanel.add(btnOpenText);
        buttonPanel.add(btnSaveText);
        buttonPanel.add(btnAppendText); // [LATIHAN 4]
        buttonPanel.add(new JLabel());
        buttonPanel.add(btnSaveBinary);
        buttonPanel.add(btnLoadBinary);
        buttonPanel.add(btnSaveObject); // [LATIHAN 3]
        buttonPanel.add(btnLoadObject); // [LATIHAN 3]
        
        // Layout
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        // --- EVENT HANDLING ---
        
        // 1. MEMBACA FILE TEKS (Text Stream) 
        btnOpenText.addActionListener(e -> bukaFileTeks());
        btnSaveText.addActionListener(e -> simpanFileTeks());
        
        // 2. [LATIHAN 4] Text Stream (Append)
        btnAppendText.addActionListener(e -> tambahFileTeks());
        
        // 3. MENULIS FILE BINARY (Byte Stream) 
        btnSaveBinary.addActionListener(e -> simpanConfigBinary());
        
        // 4. MEMBACA FILE BINARY (Byte Stream) 
        btnLoadBinary.addActionListener(e -> muatConfigBinary());
        
        // 5. [LATIHAN 3] Object Stream (Serialized Object)
        btnSaveObject.addActionListener(e -> simpanUserConfig());
        btnLoadObject.addActionListener(e -> muatUserConfig());
        
        // --- [LATIHAN 2] ---
        // Panggil metode untuk membaca last_notes.txt saat aplikasi dimulai
        bukaLastNotes();
    }
    
    
    // Contoh membaca File Teks dengan Try-Catch-Finally Konvensional [cite: 35]
    private void bukaFileTeks() {
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            BufferedReader reader = null; // Deklarasi di luar try agar bisa diakses di finally
            
            try {
                // Membuka Stream
                reader = new BufferedReader(new FileReader(file));
                textArea.setText(""); // Kosongkan area
                
                String line;
                // Baca baris demi baris
                while ((line = reader.readLine()) != null) {
                    textArea.append(line + "\n");
                }
                
                JOptionPane.showMessageDialog(this, "File berhasil dimuat!");
                
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "File tidak ditemukan: " + ex.getMessage());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Gagal membaca file: " + ex.getMessage());
            } finally {
                // Blok Finally: Selalu dijalankan untuk menutup resource [cite: 13]
                try {
                    if (reader != null) {
                        reader.close(); // PENTING: Menutup stream
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    
    // Contoh: Menulis File Teks menggunakan Try-With-Resources (Lebih Modern) [cite: 97]
    // PERBAIKAN: Nama method diganti dari 'simpam' menjadi 'simpan'
    private void simpanFileTeks() {
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            
            // Try-with-resources otomatis menutup stream tanpa blok finally manual [cite: 99]
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(textArea.getText());
                JOptionPane.showMessageDialog(this, "File berhasil disimpan!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Gagal menyimpan file: " + ex.getMessage());
            }
        }
    }
    
    // Contoh: Menulis Binary (Menyimpan ukuran font saat ini ke file .bin) [cite: 110]
    private void simpanConfigBinary() {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("config.bin"))) {
            // Kita simpan ukuran font saat ini (Integer)
            int fontSize = textArea.getFont().getSize();
            dos.writeInt(fontSize);
            
            JOptionPane.showMessageDialog(this, "Ukuran font (" + fontSize + ") disimpan ke config.bin");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan binary: " + ex.getMessage());
        }
    }
    
    // Contoh: Membaca Binary (Mengambil ukuran font dari file .bin) [cite: 129]
    private void muatConfigBinary() {
        try (DataInputStream dis = new DataInputStream(new FileInputStream("config.bin"))) {
            // Membaca data Integer mentah
            int fontSize = dis.readInt();
            
            // Terapkan ke aplikasi
            textArea.setFont(new Font("Monospaced", Font.PLAIN, fontSize));
            JOptionPane.showMessageDialog(this, "Font diubah menjadi ukuran: " + fontSize);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "File config.bin belum dibuat!");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Gagal membaca binary: " + ex.getMessage());
        }
    }
    
    // --- [LATIHAN 2]: Otomatis baca last_notes.txt ---
    private void bukaLastNotes() {
        File file = new File("last_notes.txt");
        // Gunakan try-catch agar jika file tidak ada, aplikasi diam saja (tidak crash) 
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                textArea.setText(""); // Reset area
                while ((line = reader.readLine()) != null) {
                    textArea.append(line + "\n");
                }
                // Tidak perlu JOptionPane, cukup load diam-diam
            } catch (IOException ex) {
                // Biarkan kosong atau print stack trace di console saja
                System.out.println("Gagal memuat last_notes: " + ex.getMessage());
            }
        }
    }
    
    // --- [LATIHAN 3]: Menyimpan & Membaca Objek (UserConfig) ---
    private void simpanUserConfig() {
        // Meminta input username dari pengguna
        String username = JOptionPane.showInputDialog(this, "Masukkan Username:");
        if (username != null && !username.isEmpty()) {
            UserConfig config = new UserConfig();
            config.setUsername(username);
            config.setFontSize(textArea.getFont().getSize()); // Ambil ukuran font saat ini
            
            // Menggunakan ObjectOutputStream 
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("user.cfg"))) {
                oos.writeObject(config); // Tulis objek utuh
                JOptionPane.showMessageDialog(this, "Objek UserConfig berhasil disimpan ke user.cfg");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Gagal menyimpan objek: " + ex.getMessage());
            }
        }
    }
    
    private void muatUserConfig() {
        // Menggunakan ObjectInputStream 
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("user.cfg"))) {
            // Casting objek yang dibaca kembali menjadi UserConfig
            UserConfig config = (UserConfig) ois.readObject();
            
            // Terapkan konfigurasi
            textArea.setFont(new Font("Monospaced", Font.PLAIN, config.getFontSize()));
            JOptionPane.showMessageDialog(this, 
                "Config Dimuat!\nUsername: " + config.getUsername() + 
                "\nFont Size: " + config.getFontSize());
                
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "File user.cfg tidak ditemukan!");
        } catch (IOException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Gagal memuat objek: " + ex.getMessage());
        }
    }
    
    // --- [LATIHAN 4]: Menambahkan Teks (Append) ---
    private void tambahFileTeks() {
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            // Gunakan constructor FileWriter(file, true) untuk mode append [cite: 158]
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                writer.write(textArea.getText());
                JOptionPane.showMessageDialog(this, "Teks berhasil ditambahkan ke file!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Gagal menambahkan teks: " + ex.getMessage());
            }
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AplikasiFileIO().setVisible(true);
        });
    }
}
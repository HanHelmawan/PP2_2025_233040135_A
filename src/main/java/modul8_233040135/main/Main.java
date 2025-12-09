/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modul8_233040135.main;

/**
 *
 * @author raiha
 */

import modul8_233040135.controller.PersegiPanjangController;
import modul8_233040135.model.PersegiPanjangModel;
import modul8_233040135.view.PersegiPanjangView;

public class Main {
    public static void main(String[] args) {
        // 1. Instansiasi Model
        PersegiPanjangModel model = new PersegiPanjangModel();
        
        // 2. Instansiasi View
        PersegiPanjangView view = new PersegiPanjangView();
        
        // 3. Instansiasi Controller
        PersegiPanjangController controller = new PersegiPanjangController(model, view);
        
        // 4. Tampilkan view
        view.setVisible(true);
    }
}

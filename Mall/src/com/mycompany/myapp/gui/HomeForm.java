/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author bhk
 */
public class HomeForm extends Form{

    public HomeForm() {
        
        setTitle("Mall For All");
        setLayout(BoxLayout.y());
        
        //add(new Label("Admin Dashboard "));
       
        Button btnListFournisseur = new Button("List Fournisseur");
        
        btnListFournisseur.addActionListener(e-> new ListFournisseurForm(this).show());
        addAll(btnListFournisseur);
        
    }
    
    
}

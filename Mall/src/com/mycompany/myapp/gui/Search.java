/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;
import com.mycompany.myapp.services.ServiceFournisseur;
import com.codename1.components.MultiButton;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Fournisseur;
import java.util.ArrayList;
/**
 *
 * @author MSI
 */
public class Search extends Form{
    Form current;
    public Search(Form previous, String c) {
        current=this;
        setTitle("Liste des Reclamations");
        setLayout(BoxLayout.y());
        ServiceFournisseur service = ServiceFournisseur.getInstance();
        ArrayList<Fournisseur> rec = service.SearchFournisseur(c);
        Container list = new Container(BoxLayout.y());
        list.setScrollableY(true);
        for (Fournisseur f : rec) {
            MultiButton sp = new MultiButton();
            sp.setTextLine1("Supplier : "+f.getNom());
            //sp.setTextLine2("prenom : "+Reclamation.getPrenom_d()+"cin : "+Reclamation.getCin()+"l'email : "+Reclamation.getEmail());
            //sp.setTextLine3("le commentaire : "+Reclamation.getCommentaire()+"tel : "+Reclamation.getTel()+"Statut : "+Reclamation.getStatut());   
            sp.addActionListener((evt) -> {
                System.out.println("search");
                new ListFournisseurForm(current).show();
            });
            list.add(sp);
        }
        getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            previous.showBack();
        });
        this.add(list);
    }
}
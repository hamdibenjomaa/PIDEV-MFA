/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.io.FileSystemStorage;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Fournisseur;
import com.mycompany.myapp.services.ServiceFournisseur;
import java.util.ArrayList;
import com.codename1.ui.FontImage;
import com.codename1.ui.plaf.Border;
import java.io.IOException;


/**
 *
 * @author Home
 */
// import statements here

public class ListFournisseurForm extends Form {

    Form current;

    public ListFournisseurForm(Form previous) {
        setTitle("List Fournisseur");
        setLayout(BoxLayout.y());
        ArrayList<Fournisseur> tasks = ServiceFournisseur.getInstance().getAllFournisseurs();
        TextField Recherche = new TextField("", "Supplier search");
        Recherche.getStyle().setFgColor(154245);
        Button Voyagesearch = new Button("Supplier search");
        add(Recherche);

        add(Voyagesearch);

        Voyagesearch.addActionListener((evt) -> new Search(current, Recherche.getText()).show());

        for (Fournisseur f : tasks) {
            addElement(f);
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

   
    /*public void addElement(Fournisseur fournisseur) {
    Container c = new Container(new BorderLayout());

    Label nomLabel = new Label(fournisseur.getNom());
    nomLabel.getStyle().setPadding(0, 0, 0, 10); // add padding to the right to separate from the image

    String imageFilename = fournisseur.getImg();
    String imagePath = "file:///C:/xampp/htdocs/aa/" + imageFilename;
    EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(200, 200, 0xffff0000), true);
    Image image = URLImage.createToStorage(placeholder, imageFilename, imagePath);

    Label imageLabel = new Label(image.fill(200, 200));
    imageLabel.setUIID("ImagePreview");
    imageLabel.getStyle().setBorder(Border.createLineBorder(1));
    imageLabel.getStyle().setPadding(1,1,1,1);

            int tel = fournisseur.getTel();
    Dialog popup = new Dialog();
            popup.setLayout(new BorderLayout());
    Container detailsContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    detailsContainer.add(nomLabel);
    Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            container.add(new Label("nom:"));
            container.add(new Label(fournisseur.getNom()));
            container.add(new Label("Tel:"));
            container.add(new Label(String.valueOf(tel)));
            container.add(new Label("Email:"));
            container.add(new Label(fournisseur.getEmail()));
            container.add(new Label("Website:"));
            container.add(new Label(fournisseur.getWebsite()));
            System.out.println(fournisseur.getImg());
            //container.add(new Label(fournisseur.getCategorieId()));

            popup.add(BorderLayout.CENTER, container);
            popup.showPopupDialog(nomLabel);

    c.add(BorderLayout.WEST, imageLabel);
    c.add(BorderLayout.CENTER, detailsContainer);

    add(c);
}*/

    
    public void addElement(Fournisseur fournisseur) {
    Container c = new Container(new BorderLayout());
    int tel = fournisseur.getTel();

    Label nomLabel = new Label(fournisseur.getNom());
    String imageFilename = fournisseur.getImg(); // get the filename from the database
    String imagePath = "file:///C:/xampp/htdocs/aa/" + imageFilename; // construct the path to the image file
    Image image = null;
    try {
        image = Image.createImage(imagePath).scaled(200, 200); // load the image from the file and scale it to 200x200 pixels
    } catch (IOException e) {
        e.printStackTrace();
    }   
    ImageViewer viewer = new ImageViewer(image); // create the ImageViewer component
    c.addComponent(BorderLayout.WEST, viewer); // add the ImageViewer component to the container
    c.addComponent(BorderLayout.CENTER, nomLabel);
    
    nomLabel.addPointerPressedListener(evt -> {
        Dialog popup = new Dialog();
        popup.setLayout(new BorderLayout());

        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        container.add(new Label("Nom:"));
        container.add(new Label(fournisseur.getNom()));
        container.add(new Label("Tel:"));
        container.add(new Label(String.valueOf(tel)));
        container.add(new Label("Email:"));
        container.add(new Label(fournisseur.getEmail()));
        container.add(new Label("Website:"));
        container.add(new Label(fournisseur.getWebsite()));
        //container.add(new Label(fournisseur.getCategorieId()));

        popup.add(BorderLayout.CENTER, container);
        popup.showPopupDialog(nomLabel);
    });

    add(c);
}

}

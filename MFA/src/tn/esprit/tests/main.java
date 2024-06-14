/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.tests;

import tn.esprit.services.CategorieService;
import tn.esprit.entities.CategorieF;
import tn.esprit.entities.Fournisseur;
import tn.esprit.services.FournisseurService;
import tn.esprit.tools.MaConnexion;
/**
 *
 * @author Home
 */
public class main {
     public static void main(String[] args) {
        FournisseurService cf = new FournisseurService();
        CategorieF c = new CategorieF(21 , "aa");
        Fournisseur f = new Fournisseur(38,"nike",12345678,"tunis","ha@gmail.com","haaa","haaa",c);
        //cf.ajouter(f);
        
         System.out.println(c);
}
}

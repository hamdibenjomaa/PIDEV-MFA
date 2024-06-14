/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

/**
 *
 * @author Home
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.esprit.entities.CategorieF;
import tn.esprit.entities.Fournisseur;
import tn.esprit.tools.MaConnexion;

public class FournisseurService implements NewInterface<Fournisseur> {

    Connection cnx;
    String sql = "";

    public FournisseurService() {
        cnx = MaConnexion.getInstance().getCnx();
    }

    @Override
    public void ajouter(Fournisseur f) {
        sql = "insert into fournisseur(nom,tel,address,email,website,img,categorie_id) values (?,?,?,?,?,?,?)";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, f.getNom());
            ste.setInt(2, f.getTel());
            ste.setString(3, f.getAddress());
            ste.setString(4, f.getEmail());
            ste.setString(5, f.getWebsite());
            ste.setString(6, f.getImg());
            ste.setInt(7, f.getCategorieId().getId()); 
            ste.executeUpdate();
            System.out.println("fournisseur Ajoutée !!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

   @Override
public List<Fournisseur> afficher() {
    List<Fournisseur> Fournisseurs = new ArrayList<>();
    sql = "SELECT f.id, f.nom, f.tel, f.address, f.email, f.website, f.img, c.libelle " +
          "FROM fournisseur f JOIN categorie_f c ON f.categorie_id = c.id";
    try {
        Statement ste = cnx.createStatement();
        ResultSet rs = ste.executeQuery(sql);
        while (rs.next()) {
            CategorieF c = new CategorieF(rs.getString("libelle"));
            Fournisseur f = new Fournisseur(rs.getInt("id"), rs.getString("nom"), rs.getInt("tel"),
                                            rs.getString("address"), rs.getString("email"), rs.getString("website"),
                                            rs.getString("img"), c);
            Fournisseurs.add(f);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return Fournisseurs;
    
}


    
    public void update(Fournisseur f) {
        sql = "update fournisseur set nom=?,tel=?,address=?,email=?,website=?,img=?,categorie_id=? where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, f.getNom());
            ste.setInt(2, f.getTel());
            ste.setString(3, f.getAddress());
            ste.setString(4, f.getEmail());
            ste.setString(5, f.getWebsite());
            ste.setString(6, f.getImg());
            ste.setInt(7, f.getCategorieId().getId());
            ste.setInt(8, f.getId());

            int rowsUpdated = ste.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Fournisseur mis à jour !");
            } else {
                System.out.println("Erreur : La mise à jour a échoué.");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @Override
    public void supprimer(Fournisseur f) {
        sql = "delete from fournisseur where id=" + f.getId();
        try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println("fournisseur supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}

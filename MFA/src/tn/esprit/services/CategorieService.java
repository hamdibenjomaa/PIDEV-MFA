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
import tn.esprit.tools.MaConnexion;

public class CategorieService implements NewInterface<CategorieF> {

    Connection cnx;
    String sql = "";
 public CategorieService() {
        cnx=MaConnexion.getInstance().getCnx();
    }
    @Override
    public void ajouter(CategorieF p) {
        sql = "insert into categorie_f(libelle) values (?)";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, p.getLibelle());
            ste.executeUpdate();
            System.out.println("categorie Ajoutée !!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     

    @Override
    public List<CategorieF> afficher() {
        List<CategorieF> Categories = new ArrayList<>();
        sql = "select * from categorie_f";
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {
                CategorieF c = new CategorieF(rs.getInt("id"), rs.getString("libelle"));
                Categories.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Categories;
    }
    public void update(CategorieF c) {
        sql = "update categorie_f set libelle=? where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, c.getLibelle());
            ste.setInt(2, c.getId());

            int rowsUpdated = ste.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Categorie mis à jour !");
            } else {
                System.out.println("Erreur : La mise à jour a échoué.");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(CategorieF c) {
        sql = "delete from categorie_f where id=" + c.getId();
        try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println("categorie supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
public CategorieF getCategorieById(String libelle) {
        CategorieF c = null;
        sql = "select from categorie_f where libelle= " + libelle;
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            c = new CategorieF(rs.getInt("id"), rs.getString("libelle"));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return c;
    }
}

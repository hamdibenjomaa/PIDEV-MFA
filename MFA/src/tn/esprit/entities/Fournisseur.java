/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entities;



/**
 *
 * @author Home
 */
public class Fournisseur {

    
    private Integer id;
    private String nom;
    private int tel;
    private String address;
    private String email;
    private String website;
    private String img;
    private CategorieF categorie;

    public Fournisseur() {
   
    }

    public Fournisseur(Integer id) {
        this.id = id;
    }

    public Fournisseur(Integer id, String nom, int tel, String address, String email, String website, String img) {
        this.id = id;
        this.nom = nom;
        this.tel = tel;
        this.address = address;
        this.email = email;
        this.website = website;
        this.img = img;
    }

    public Fournisseur(Integer id, String nom, int tel, String address, String email, String website, String img , CategorieF category) {
        this.id = id;
        this.nom = nom;
        this.tel = tel;
        this.address = address;
        this.email = email;
        this.website = website;
        this.img = img;
        this.categorie=category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public CategorieF getCategorieId() {
        return categorie;
    }

    public void setCategorieId(CategorieF categorieId) {
        this.categorie = categorieId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fournisseur)) {
            return false;
        }
        Fournisseur other = (Fournisseur) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tn.esprit.entities.Fournisseur[ id=" + id + " ]";
    }
    
}

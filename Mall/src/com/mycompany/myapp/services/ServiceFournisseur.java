/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.CategorieF;
import com.mycompany.myapp.entities.Fournisseur;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 *
 * @author Home
 */
public class ServiceFournisseur {
     public ArrayList<Fournisseur> Fournisseur;

    public static ServiceFournisseur instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceFournisseur() {
        req = new ConnectionRequest();
    }

    public static ServiceFournisseur getInstance() {
        if (instance == null) {
            instance = new ServiceFournisseur();
        }
        return instance;
    }
    
    
    public boolean addFournisseur(Fournisseur f) {

        String nom = f.getNom();
        int tel = f. getTel();
        String email =  f.getEmail();
        String address = f.getAddress();
        String website = f.getWebsite();
        String img = f.getImg();
         
      
       
        
        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
      String url = Statics.BASE_URL1 + "AddSupplier/new?nom=" + nom + "&tel=" + tel + "&email=" + email+"&website=" + website +"&img=" + img+ "&address=" + address ;


        req.setUrl(url);
        req.setPost(false);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    public ArrayList<Fournisseur> parseFournisseur(String jsonText) {
    try {
        Fournisseur = new ArrayList<>();
        JSONParser j = new JSONParser();
        Map<String, Object> fournisseurListJson
                = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

        List<Map<String, Object>> list = (List<Map<String, Object>>) fournisseurListJson.get("root");
        for (Map<String, Object> obj : list) {
            Fournisseur f = new Fournisseur();
            float id = Float.parseFloat(obj.get("id").toString());
            f.setId((int) id);
            f.setTel(((int) Float.parseFloat(obj.get("tel").toString())));
            f.setEmail(obj.get("email").toString());
            f.setAddress(obj.get("address").toString());
            f.setWebsite(obj.get("website").toString());
            f.setImg(obj.get("img").toString());
           if (obj.get("nom") == null) {
                f.setNom("null");
            } else {
                f.setNom(obj.get("nom").toString());

            }
            Fournisseur.add(f);
        }

    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    return Fournisseur;
}

public ArrayList<Fournisseur> getAllFournisseurs() {
    String url = Statics.BASE_URL1 + "AllSuppliers";
    req.setUrl(url);
    req.setPost(false);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            Fournisseur = parseFournisseur(new String(req.getResponseData()));
            req.removeResponseListener(this);
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return Fournisseur;
}

  public boolean deletedFournisseur(int id) {
        
        String url = Statics.BASE_URL1 + "/DeleteFJSON/" + id + "";
        req.setUrl(url);
        req.setPost(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
            NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
  
  
  public ArrayList<Fournisseur> SearchFournisseur(String c) {
    ArrayList<Fournisseur> result = new ArrayList<>();
    String  url = Statics.BASE_URL +"/searchFournisseurJSON?search="+c+"";
    System.out.println(c);
    req.setUrl(url);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            JSONParser jsonp;
            jsonp = new JSONParser();
            try {
                //renvoi une map avec clé = root et valeur le reste
                Map<String, Object> mapVoyage = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));

                List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapVoyage.get("root");

                for (Map<String, Object> obj : listOfMaps) {
                    Fournisseur v = new Fournisseur();
                int id =(int) Float.parseFloat(obj.get("id").toString());
         
                v.setId(id);
            
                v.setNom(obj.get("nom").toString());
            
                
             

               result.add(v);
                  // Call the method to iterate over the list after modification
                handleSearchResult(result);
                }
                
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    });

    NetworkManager.getInstance().addToQueueAndWait(req);

    return result;
}

private void handleSearchResult(ArrayList<Fournisseur> result) {
    for (Fournisseur v : result) {
        System.out.println(v.toString());
    }
}
public ArrayList<Fournisseur> parseTasks(String jsonText){
        try {
            Fournisseur  =new ArrayList<>();  
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Fournisseur v = new Fournisseur();
                int id =(int) Integer.parseInt(obj.get("id").toString());
                int cin = Integer.parseInt(obj.get("cin").toString());
                v.setId(id);
             v.setNom(obj.get("nom").toString());
               
               Fournisseur.add(v);
            }
            
            
        } catch (IOException ex) {
            
        }
        return Fournisseur;
    }
}

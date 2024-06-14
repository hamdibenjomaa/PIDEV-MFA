package com.mycompany.myapp.entities;

import com.mycompany.myapp.entities.Fournisseur;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-05-10T09:53:59")
@StaticMetamodel(CategorieF.class)
public class CategorieF_ { 

    public static volatile SingularAttribute<CategorieF, String> libelle;
    public static volatile CollectionAttribute<CategorieF, Fournisseur> fournisseurCollection;
    public static volatile SingularAttribute<CategorieF, Integer> id;

}
CREATE TABLE CatalogeObjet(
  idcat NUMBER NOT NULL ,
  nomcat VARCHAR(30),
  nbproduit NUMBER,
  CONSTRAINT pk_idcat PRIMARY KEY (idcat)
);

CREATE TABLE ProduitObjet(
  idproduit NUMBER NOT NULL ,
  quantitesstock NUMBER(38),
  nom VARCHAR2(20),
  prixunitaireht FLOAT(63),
  idcat NUMBER,
  CONSTRAINT pk_idproduit PRIMARY KEY (idproduit),
  CONSTRAINT fk_idcat FOREIGN KEY (idcat) REFERENCES CatalogeObjet(idcat)
);
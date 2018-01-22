CREATE TABLE CatalogueObjet(
  idcat NUMBER NOT NULL ,
  nomcat VARCHAR(30),
  nbproduit NUMBER,
  CONSTRAINT pk_idcat PRIMARY KEY (idcat)
);

ALTER TABLE CatalogueObjet ADD CONSTRAINT ck_unique_cat UNIQUE (nomcat);

CREATE TABLE ProduitObjet(
  idproduit NUMBER NOT NULL ,
  quantitesstock NUMBER(38),
  nom VARCHAR2(20),
  prixunitaireht FLOAT(63),
  idcat NUMBER,
  CONSTRAINT pk_idproduit PRIMARY KEY (idproduit),
  CONSTRAINT fk_idcat FOREIGN KEY (idcat) REFERENCES CatalogueObjet(idcat) ON DELETE CASCADE
);

ALTER TABLE ProduitObjet
  DROP CONSTRAINT fk_idcat;

ALTER TABLE ProduitObjet
  ADD CONSTRAINT fk_idcat
FOREIGN KEY (idcat) REFERENCES CatalogueObjet(idcat) ON DELETE CASCADE;

CREATE SEQUENCE ma_sequencecat;

CREATE OR REPLACE PROCEDURE nouveauCatalogueObjet (
  v_nomcat IN CatalogueObjet.nomcat%TYPE) IS
  BEGIN
    INSERT INTO CatalogueObjet (idcat, nomcat, nbproduit) VALUES (ma_sequencecat.NEXTVAL, v_nomcat, 0);
  END;


CREATE SEQUENCE ma_sequenceprod;

CREATE OR REPLACE PROCEDURE nouveauProduitObjet (
  v_qtestock IN ProduitObjet.quantitesstock%TYPE,
  v_nom IN ProduitObjet.nom%TYPE,
  v_prix IN ProduitObjet.prixunitaireht%TYPE,
  v_idcat IN ProduitObjet.idcat%TYPE) IS
  BEGIN
    INSERT INTO ProduitObjet (idproduit, quantitesstock, nom, prixunitaireht, idcat) VALUES (ma_sequenceprod.NEXTVAL,v_qtestock, v_nom, v_prix, v_idcat);
  END;



CREATE OR REPLACE FUNCTION fonc_id_cat(v_nomcat IN CatalogueObjet.nomcat%TYPE)
RETURN NUMBER IS
  v_idcat NUMBER;
  BEGIN
    SELECT  idcat INTO v_idcat
    FROM CatalogueObjet
    WHERE nomcat = v_nomcat;
    RETURN v_idcat;
  END;

CREATE OR REPLACE PROCEDURE proc_supp_prod(v_idcat IN CatalogueObjet.idcat%TYPE)
IS
BEGIN
  DELETE FROM ProduitObjet
  WHERE idcat = v_idcat;
END;

call nouveauCatalogueObjet('lib');

call nouveauProduitObjet(2,'Twix',2.5,22);

CREATE OR REPLACE TRIGGER trig_creation_prod_cat
  AFTER INSERT ON ProduitObjet
  FOR EACH ROW
  DECLARE
    v_nbprod CatalogueObjet.nbproduit%TYPE;
  BEGIN
    SELECT nbproduit INTO v_nbprod
    FROM CatalogueObjet
    WHERE idcat =:NEW.idcat;
    UPDATE CatalogueObjet SET nbproduit = v_nbprod+1
    WHERE idcat =:NEW.idcat;
  END;

CREATE OR REPLACE TRIGGER trig_supp_prod_cat
  AFTER DELETE ON ProduitObjet
  FOR EACH ROW
  DECLARE
    v_nbprod CatalogueObjet.nbproduit%TYPE;
  BEGIN
    SELECT nbproduit INTO v_nbprod
    FROM CatalogueObjet
    WHERE idcat =:OLD.idcat;
    UPDATE CatalogueObjet SET nbproduit = v_nbprod-1
    WHERE idcat =:OLD.idcat;
  END;

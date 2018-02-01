CREATE TABLE Produit(
  IDPRODUIT  NUMBER NOT NULL ,
  QUANTITESTOCK NUMBER(38),
  NOM VARCHAR2(20),
  PRIXUNITAIREHT FLOAT(63),
  CONSTRAINT pk_idproduit PRIMARY KEY (IDPRODUIT));


create PROCEDURE nouveauProduit (p_nom IN Produit.nom%TYPE,p_quantiteStock IN Produit.quantiteStock%TYPE,p_prixUnitaireHT IN Produit.prixUnitaireHT%TYPE)IS
BEGIN
  INSERT INTO Produit VALUES(seqProduit.nextval,p_quantiteStock,p_nom,p_prixUnitaireHT);
END;


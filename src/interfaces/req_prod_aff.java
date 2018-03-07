package interfaces;

import java.util.ArrayList;

import donnees.produit;

public interface req_prod_aff {
	public ArrayList<produit> prodRechercherLibelle(String libelle);
}

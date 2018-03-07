package interfaces;

import donnees.catalogue_fournisseur;

public interface req_cat {
	public boolean catUpdate(catalogue_fournisseur cat, int new_id_produit, int new_id_fournisseur);
	public boolean catDelete(int id_four, int id_prod);
	public boolean catAdd(catalogue_fournisseur cat, int id_produit, int id_fournisseur);
}

package interfaces;

import donnees.produit;

public interface req_prod {
	public boolean prodUpdate(produit prod);
	public boolean prodDelete(int id_prod);
	public boolean prodAdd(produit prod);
}

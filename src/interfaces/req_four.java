package interfaces;

import donnees.fournisseur;

public interface req_four {
	public boolean fourUpdate(fournisseur id);
	public boolean fourDelete(int id_four);
	public boolean fourAdd(fournisseur frs);
}

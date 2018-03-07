package donnees;

import interfaces.req_cat;
import config.Parametres;

/**
 * Elle permet de stocker la relation entre 1 produit et 1 fournisseur
 * @author JonathanGRZ
 *
 */
public class catalogue_fournisseur {
	private double prix_u_ht;
	private boolean reapprovisionnable;
	private fournisseur le_fournisseur;
	private produit le_produit;
	private static req_cat req_bdd;
	
	static{
		req_bdd = Parametres.getReq();
	}
	
	/**
	 * Constructeur d'un objet catalogue reliant 1 fournisseur et 1 produit de la BDD vers l'appli
	 * @param prix_ht : double : prix hors taxe
	 * @param reap : boolean : si le produit peut être reapprovisionnable avec ce fournisseur
	 * @param fourni : fournisseur : le fournisseur qui est relié avec le produit
	 * @param prod : produit : le produit qui est relié avec le fournisseur
	 * @author JonathanGRZ
	 */
	public catalogue_fournisseur(
			double prix_ht,
			boolean reap,
			fournisseur fourni,
			produit prod){
		this.prix_u_ht = prix_ht;
		this.reapprovisionnable = reap;
		this.le_fournisseur = fourni;
		this.le_produit = prod;
	}

	/**
	 * Constructeur d'un objet catalogue reliant 1 fournisseur et 1 produit de l'application vers la BDD
	 * @param prix_ht : double : prix hors taxe
	 * @param reap : boolean : si le produit peut être reapprovisionnable avec ce fournisseur
	 * @param fourni : int : l'id du fournisseur qui est relié avec le produit
	 * @param prod : int : l'id du produit qui est relié avec le fournisseur
	 * @author JonathanGRZ
	 */
	public catalogue_fournisseur(
			double prix_ht,
			boolean reap,
			int id_fourni,
			int id_prod){
		this.prix_u_ht = prix_ht;
		this.reapprovisionnable = reap;
		req_bdd.catAdd(this, id_prod, id_fourni);
	}
	/**
	 * Mettre à jour la relation entre le produit et le fournisseur
	 * @param prix_ht : double : prix hors taxe
	 * @param reap : boolean : si le produit peut être reapprovisionnable avec ce fournisseur
	 * @param fourni : fournisseur : le fournisseur qui est relié avec le produit
	 * @param prod : produit : le produit qui est relié avec le fournisseur
	 * @return : boolean : si la requete avec la base de données à bien fonctionné
	 * @author JonathanGRZ
	 */
	public boolean Update(
			double prix_ht,
			boolean reap,
			fournisseur fourni,
			produit prod){
		this.prix_u_ht = prix_ht;
		this.reapprovisionnable = reap;
		if(req_bdd.catUpdate(this, fourni.getId(), prod.getId())){
			this.le_fournisseur = fourni;
			this.le_produit = prod;
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * Supprime le catalogue dans la base de données
	 * @return : boolean : vrai si la requete a fonctionné
	 * @author JonathanGRZ
	 */
	public boolean Delete(){
		return req_bdd.catDelete(le_fournisseur.getId(), le_produit.getId());
	}
	
	/**
	 * Donne le prix hors taxe du produit du fournisseur
	 * @return : double : le prix hors taxe du produit du fournisseur 
	 * @author JonathanGRZ
	 */
	public double getPrix(){
		return this.prix_u_ht;
	}
	
	/**
	 * Donne si le produit peut être réapprovisionnable via ce fournisseur
	 * @return : boolean : vrai si le produit peut être réapprovisionnable via ce fournisseur
	 * @author JonathanGRZ
	 */
	public boolean getReap(){
		return this.reapprovisionnable;
	}
	
	/**
	 * Donne le fournisseur de ce catalogue
	 * @return : fournisseur : le fournisseur de ce catalogue
	 * @author JonathanGRZ
	 */
	public fournisseur getFournisseur(){
		return this.le_fournisseur;
	}
	
	/**
	 * Donne le produit de ce catalogue
	 * @return : produit : le produit de ce catalogue
	 * @author JonathanGRZ
	 */
	public produit getProduit(){
		return this.le_produit;
	}

	@Override
	public String toString() {
		return "catalogue_fournisseur [prix_u_ht=" + prix_u_ht
				+ ", reapprovisionnable=" + reapprovisionnable
				+ ", le_fournisseur=" + le_fournisseur.getId() + ", le_produit="
				+ le_produit.getId() + "]";
	}
	
	
}

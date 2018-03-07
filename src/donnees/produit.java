package donnees;

import config.Parametres;
import interfaces.req_prod;

/**
 * La classe qui permet de manipuler le produit
 * @author JonathanGRZ
 *
 */
public class produit {
	private int id_produit;
	private String libelle;
	private double prix_de_vente;
	private int quantite_en_stock;
	private int quantite_minimal;
	private static req_prod req_bdd;
	
	static{
		req_bdd = Parametres.getReq();
	}
	
	/**
	 * Constructeur du produit de la base de données vers l'application
	 * @param id : integer : l'id du produit dans la base de données
	 * @param libelle: String : le libelle du produit
	 * @param prix_vente : double : prix de vente du produit
	 * @param qt_stock : integer : quantite en stock du produit
	 * @param qt_mini : integer : la quantite minimum du produit à avoir dans le stock
	 * @author JonathanGRZ
	 */
	public produit(
					int id,
					String libelle,
					double prix_vente,
					int qt_stock,
					int qt_mini
					){
		this.id_produit = id;
		this.libelle = libelle;
		this.prix_de_vente = prix_vente;
		this.quantite_en_stock = qt_stock;
		this.quantite_minimal = qt_mini;
	}
	
	/**
	 * Constructeur du produit de l'application vers la base de données
	 * @param libelle: String : le libelle du produit
	 * @param prix_vente : double : prix de vente du produit
	 * @param qt_stock : integer : quantite en stock du produit
	 * @param qt_mini : integer : la quantite minimum du produit à avoir dans le stock
	 * @author JonathanGRZ
	 */
	public produit(
				String libelle,
				double prix_vente,
				int qt_stock,
				int qt_mini
				){
		this.libelle = libelle;
		this.prix_de_vente = prix_vente;
		this.quantite_en_stock = qt_stock;
		this.quantite_minimal = qt_mini;
		req_bdd.prodAdd(this);
	}
	
	/**
	 * Modifier le produit et le mettre à jour dans la base de données
	 * @param libelle: String : le libelle du produit
	 * @param prix_vente : double : prix de vente du produit
	 * @param qt_stock : integer : quantite en stock du produit
	 * @param qt_mini : integer : la quantite minimum du produit à avoir dans le stock
	 * @return boolean : TRUE si la mise à jour n'a pas rencontré de problème
	 * @author JonathanGRZ
	 */
	public boolean Update(
							String libelle,
							double prix_vente,
							int qt_stock,
							int qt_mini
							){	
		this.libelle = libelle;
		this.prix_de_vente = prix_vente;
		this.quantite_en_stock = qt_stock;
		this.quantite_minimal = qt_mini;
		return req_bdd.prodUpdate(this);
	}
	
	/**
	 * Modifie la quantité en stock en ajoutant le nombre en parametre et le modifie dans la base de données
	 * @param qt_ajout : integer : la valeur modifié par rapport au stock
	 * @return : boolean : TRUE si la modification a été effectuée correctement
	 * @author JonathanGRZ
	 */
	public boolean modifierQT(int qt_ajout){		
		//si le chiffre est négatif alors on enlevera de la quantite en stock
		this.quantite_en_stock += qt_ajout;
		return req_bdd.prodUpdate(this) ;
	}
	
	/**
	 * Supprimer le produit dans la base de données
	 * @return : boolean : TRUE si la suppression a été effectuée correctement
	 * @author JonathanGRZ
	 */
	public boolean Delete(){
		return req_bdd.prodDelete(this.id_produit);
	}
	
	/**
	 * Donne l'id du produit
	 * @return : int : valeur de l'id du produit
	 * @author JonathanGRZ
	 */
	public int getId(){
		return this.id_produit;
	}
	
	/**
	 * Donne le libelle du produit
	 * @return : String : le libelle du produit
	 * @author JonathanGRZ
	 */
	public String getLibelle(){
		return this.libelle;
	}
	
	/**
	 * Donne le prix de vente du produit
	 * @return : double : le prix de vente du produit
	 * @author JonathanGRZ
	 */
	public double getPrixVente(){
		return this.prix_de_vente;
	}
	
	/**
	 * Donne la quantité en stock du produit
	 * @return : int : la quantité en stock du produit
	 * @author JonathanGRZ
	 */
	public int getQtStock(){
		return this.quantite_en_stock;
	}
	
	/**
	 * Donne la quantité minimum à avoir en stock pour le produit
	 * @return : int : la quantité minimum à avoir en stock pour le produit
	 * @author JonathanGRZ
	 */
	public int getQtMini(){
		return this.quantite_minimal;
	}
	
	/**
	 * Méthode voir l'état des variables à des fins de tests avant l'interface graphique
	 * @author JonathanGRZ
	 */
	@Override
	public String toString() {
		return "produit [id_produit=" + id_produit + ", libelle=" + libelle
				+ ", prix_de_vente=" + prix_de_vente + ", quantite_en_stock="
				+ quantite_en_stock + ", quantite_minimal=" + quantite_minimal
				+ "]";
	}
	
	
}

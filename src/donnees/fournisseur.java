package donnees;

import config.Parametres;
import interfaces.req_four;

/**
 * La classe qui permet de manipuler les fournisseurs
 *@author MAIRECH
 *@version v1 :) :)
 *
 */
public class fournisseur{
	private int id_four;
	private String raison_social;
	private String siret;
	private String adresse;
	private String cp;
	private String ville;
	private String email;
	private String tel;
	private static req_four req_bdd;
	
	
	static{
		req_bdd = Parametres.getReq();
	}
	
	/**
	 * Constructeur du fournisseur de la base de données vers l'application
	 * @param id_four : integer : l'id du fournisseur dans la base de données
	 * @param libelle: String : le raison social du fournisseur
	 * @param siret: integer :  le siret du fournisseur
	 * @param adresse: String: l'adresse du fournisseur
	 * @param cp : integer: le code postale du fournisseur
	 * @param ville: String: la ville du fournisseur
	 * @param email: String: l'email du fournisseur
	 * @param tel: String: le téléphone du fournisseur
	 * @author MAIRECH
	 */
	public fournisseur(
					int id_four,
					String raison_social,
					String siret,
					String adresse,
					String cp,
					String ville,
					String email,
					String tel
					){
		this.id_four = id_four;
		this.raison_social = raison_social;
		this.siret = siret;
		this.adresse = adresse;
		this.cp = cp;
		this.ville = ville;
		this.email = email;
		this.tel = tel;
	}
	
	/**
	 * Constructeur du fournisseur de l'application vers la base de données
	 * @param id_four : integer : l'id du fournisseur dans la base de données
	 * @param raison_social: String : le raison social du fournisseur
	 * @param siret: integer :  le siret du fournisseur
	 * @param adresse: String: l'adresse du fournisseur
	 * @param cp : integer: le code postale du fournisseur
	 * @param ville: String: la ville du fournisseur
	 * @param email: String: l'email du fournisseur
	 * @param tel: String: le téléphone du fournisseur
	 * @author MAIRECH
	 */
	public fournisseur(
				String raison_social,
				String siret,
				String adresse,
				String cp,
				String ville,
				String email,
				String tel
				){
		this.raison_social = raison_social;
		this.siret = siret;
		this.adresse = adresse;
		this.cp = cp;
		this.ville = ville;
		this.email = email;
		this.tel = tel;
		req_bdd.fourAdd(this);
	}
	
	/**
	 * Modifier le fournisseur et le mettre à jour dans la base de données
	 * @param id : integer : l'id du fournisseur dans la base de données
	 * @param libelle: String : le raison social du fournisseur
	 * @param siret: integer :  le siret du fournisseur
	 * @param adresse: String: l'adresse du fournisseur
	 * @param cp : integer: le code postale du fournisseur
	 * @param ville: String: la ville du fournisseur
	 * @param email: String: l'email du fournisseur
	 * @param tel: String: le téléphone du fournisseur
	 * @author MAIRECH
	 */
	public boolean Update(
					String raison_social,
					String siret,
					String adresse,
					String cp,
					String ville,
					String email,
					String tel
							){	
		this.raison_social = raison_social;
		this.siret = siret;
		this.adresse = adresse;
		this.cp = cp;
		this.ville = ville;
		this.email = email;
		this.tel = tel;
		return req_bdd.fourUpdate(this);
	}
	
	
	
	/**
	 * Supprimer le fournisseur dans la base de données
	 * @return : boolean : TRUE si la suppression a été effectuée correctement
	 * @author MAIRECH
	 */
	public boolean Delete(){
		return req_bdd.fourDelete(this.id_four);
	}
	
	/**
	 * Donne l'id du fournisseur
	 * @return : int : valeur de l'id du founisseurs
	 * @author MAIRECH
	 */
	public int getId(){
		return this.id_four;
	}
	
		
	/**
	 * Donne le raison social du fournisseurs
	 * @return : String : le raison social du fournisseurs
	 * @author MAIRECH
	 */
	public String getRaison_social(){
		return this.raison_social;
	}
	
	/**
	 * Donne le siret du fournisseurs
	 * @return : Int : le siret du fournisseurs
	 * @author MAIRECH
	 */
	public String getSiret() {
		return siret;
	}

	/**
	 * Donne l'adresse du fournisseurs
	 * @return : String : l'adresse du fournisseurs
	 * @author MAIRECH
	 */
	public String getAdresse() {
		return adresse;
	}
	
	/**
	 * Donne le code postale du fournisseurs
	 * @return : Int : le code postale du fournisseurs
	 * @author MAIRECH
	 */
	public String getCp() {
		return cp;
	}
	
	/**
	 * Donne la ville du fournisseurs
	 * @return : String : la ville du fournisseurs
	 * @author MAIRECH
	 */
	public String getVille() {
		return ville;
	}
	
	/**
	 * Donne l'email du fournisseurs
	 * @return : String : l'email du fournisseurs
	 * @author MAIRECH
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Donne la téléphone du fournisseurs
	 * @return : String : le téléphone du fournisseurs
	 * @author MAIRECH
	 */
	public String getTel() {
		return tel;
	}

	
	/**
	 * Méthode voir l'état des variables à des fins de tests avant l'interface graphique
	 * @author MAIRECH
	 */
	@Override
	public String toString() {
		return "fournisseur [id_four=" + id_four + ", raison_social=" + raison_social
				+ ", siret=" + siret + ", adresse=" + adresse + ", cp=" + cp + ", ville=" + ville + ""
						+ ", email=" + email + ", tel=" + tel + "  ]";
	}
	
	
}

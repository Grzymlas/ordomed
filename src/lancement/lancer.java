package lancement;

import java.util.ArrayList;

import affichage.affichage_produit;
import config.Parametres;
import donnees.catalogue_fournisseur;
import donnees.fournisseur;
import donnees.produit;

public class lancer {

	public static void main(String[] args) {
		Parametres.Initialisation();
		System.out.println("--> Initalisation fin.");


		//test fenêtres
		affichage_produit aff_produit = new affichage_produit();
		
		
		
		
		
		
		
		
		
		
		

		/**
		 * test ajouter :
		 * @author JonathanGRZ
		 */
		/**
		produit p1 = new produit("chocapic", 10.5, 200, 50);
		produit p2 = new produit("dentifrice", 1.2, 10, 5);
		//test OK création des 2 produits
		*/
		
		/**
		 * test récupération de la liste des produits
		 * @author JonathanGRZ
		 */
		/**
		ArrayList<produit> liste_prod = Parametres.getReq().prodRechercherLibelle("c");
		System.out.println("nombre : " + liste_prod.size());
		
		for(produit prod : liste_prod){
			System.out.println(prod.toString());
		}
		//test OK
		*/
		
		/**
		 * teste mise à jour
		 * @author JonathanGRZ
		 */
		/**
		ArrayList<produit> liste_prod = Parametres.getReq().prodRechercherLibelle("chocapic");
		System.out.println("nombre : " + liste_prod.size());
		
		//il y en aura que 1
		for(produit prod : liste_prod){
			System.out.println(prod.toString());
			prod.Update(prod.getLibelle(), 20, 150, 100);
			System.out.println(prod.toString());
		}
		
		liste_prod = Parametres.getReq().prodRechercherLibelle("chocapic");
		System.out.println("nombre : " + liste_prod.size());
		
		//il y en aura que 1
		for(produit prod : liste_prod){
			System.out.println(prod.toString());
			prod.Update(prod.getLibelle(), 10.5, 200, 50);
			System.out.println(prod.toString());
		}
		//test OK
		*/ 
		
		/**
		 * test ajouter puis le recherche et enfin le supprimer :
		 * @author JonathanGRZ
		 */
		/**
		produit p1 = new produit("milka", 10.5, 200, 50);

		ArrayList<produit> liste_prod = Parametres.getReq().prodRechercherLibelle("milka");
		System.out.println("nombre : " + liste_prod.size());
		
		//il y en aura que 1
		for(produit prod : liste_prod){
			prod.Delete();
			prod.Delete();
		}		
		
		//vérification qu'elle n'existe plus
		liste_prod = Parametres.getReq().prodRechercherLibelle("milka");
		System.out.println("nombre : " + liste_prod.size());
		//test : OK
		 */
		

		/////fournisseurs//////
		
		/**
		 //* test ajouter fournisseurs
		// * @author MAIRECH
		 * 
		 */
		 //fournisseur four1 = new fournisseur ("amazon", "123515000444", "rue fictive", "68200", "Mulhouse", "contact@amazon.fr", "0389707070" );
		 //fournisseur four2 = new fournisseur ("darty", "123515000333", "rue angel dolfus", "68400", "kingersheim", "contact@darty.fr", "0389708080");
		//test ok pour la création des deux fournisseurs
		
		

		/**
		 * test récupération de la liste des fournisseurs
		 * @author MAIRECH
		 */
		/**
		ArrayList<fournisseur> liste_four = Parametres.getReq().fourRechercherLibelle("az");
		System.out.println("nombre : " + liste_four.size());
		
		for(fournisseur four : liste_four){
			System.out.println(four.toString());
		}
		//test OK
		*/
		
		/**
		// * test ajouter fournisseur puis le recherche et enfin le supprimer :
		// *@author MAIRECH
		 */
		/**
		fournisseur four3 = new fournisseur("boulanger", "123515000555", "rue l'art", "68100", "Mulhouse", "contact@boulanger.fr", "0389919191" );

		ArrayList<fournisseur> liste_four = Parametres.getReq().fourRechercherLibelle("boulanger");
		System.out.println("nombre : " + liste_four.size());
		
		//il y en aura que 1
		for(fournisseur four : liste_four){
			four.Delete();
			four.Delete();
		}		
		
		//vérification qu'elle n'existe plus
		liste_four = Parametres.getReq().fourRechercherLibelle("boulanger");
		System.out.println("nombre : " + liste_four.size());
		//test : OK
		// */
		
		/**
		 * teste mise à jour des informmations fournisseurs
		 * @author MAIRECH*/
		/**
		ArrayList<fournisseur> liste_four1 = Parametres.getReq().fourRechercherLibelle("amazon");
		System.out.println("nombre : " + liste_four1.size());
		
		//il y en aura que 1
		for(fournisseur four : liste_four1){
			System.out.println(four.toString());
			four.Update(four.getRaison_social(), four.getSiret(),four.getAdresse(), "68200", four.getVille(), "contact@amazon.fr", "11");
			System.out.println(four.toString());
		}
		
		liste_four1 = Parametres.getReq().fourRechercherLibelle("amazon");
		System.out.println("nombre : " + liste_four1.size());
		
		//il y en aura que 1 fournisseur
		for(fournisseur four : liste_four1){
			System.out.println(four.toString());
			four.Update(four.getRaison_social(), four.getSiret(),four.getAdresse(), "68200", four.getVille(), "contact@amazon.fr", "44");
			System.out.println(four.toString());
		}
		//test OK
		*/
		
		//Test catalogue
	    //produit prod1 = new produit("dentif", 10.10, 40, 30);
		//produit prod2 = new produit("ferrari", 10.10, 40, 30);
		//produit prod3 = new produit("BMW", 10.10, 40, 30);
		//produit prod4 = new produit("Peugeot", 99999, 555, 10);
		//produit prod5 = new produit("Citroen", 5555, 7777, 10);
		//id fournisseur au moment du test 
		//6 : amazone
		//2 : darty
		//id produit au moment du test :
		//1 : chocapic
		//2 : dentifrice
		//7 : crunch
		//6 : kitkat
		
		//création d'une relation entre amazone et crunch
		//récupération des objets
		//ArrayList<fournisseur> liste_four1 = Parametres.getReq().fourRechercherLibelle("amazon");
		//ArrayList<produit> liste_prod1 = Parametres.getReq().prodRechercherLibelle("kitkat");
		
		//catalogue_fournisseur cat1 = new catalogue_fournisseur(60.0, true, liste_four1.get(0).getId(), liste_prod1.get(0).getId());
		//==> OK
		//catalogue_fournisseur cat1 = new catalogue_fournisseur(60.0, true, 1, 5);
		//test suppression
		//ArrayList<catalogue_fournisseur> liste_cat = Parametres.getReq().catRechercherLibelle("", "");
		//System.out.println(liste_cat.get(0).toString());
		//==> OK
		//on ajoute 2 autre pour faire des autres test
		//cat1 = new catalogue_fournisseur(60.0, true, 2, 1);
		//cat1 = new catalogue_fournisseur(60.0, true, 2, 6);
		//cat1 = new catalogue_fournisseur(60.0, true, 2, 7);
		//cat1 = new catalogue_fournisseur(60.0, true, 6, 2);
		//catalogue_fournisseur cat1 = new catalogue_fournisseur(60.0, true, 1, 7);
		//catalogue_fournisseur cat1 = new catalogue_fournisseur(60.0, true, 7, 1);
		//cat1 = new catalogue_fournisseur(60.0, true, 7, 2);

		//liste_cat = Parametres.getReq().catRechercherLibelle("ikea", "");
		//for(catalogue_fournisseur cat : liste_cat){
		//	System.out.println(cat.toString());
		//}
		//==> OK
		
		//test suppression
		//Parametres.getReq().catDelete(6, 2);
		//==> OK de supprimer directement le catalogue
		
		//test suppression d'un produit
		//Parametres.getReq().prodDelete(1);
		//==> erreur : le catalogue a garde l'id produit
		//catalogue_fournisseur cat1 = new catalogue_fournisseur(60.0, true, 1, 7);
		//cat1 = new catalogue_fournisseur(60.0, true, 2, 7);
		//Parametres.getReq().prodDelete(7);
		//fournisseur four1 = new fournisseur ("ikea", "123515000444", "rue fictive", "68200", "Mulhouse", "contact@amazon.fr", "0389707070" );
		//catalogue_fournisseur cat1 = new catalogue_fournisseur(60.0, true, 3, 9);
		//cat1 = new catalogue_fournisseur(60.0, true, 3, 8);
		//Parametres.getReq().fourDelete(3);
		//=====> Ok si on supprime un produit ou un fournisseur, le catalogue ayant un des id seront supprimer
		
		//test de l'update
		//fournisseur four1 = new fournisseur ("ikea", "123515000444", "rue fictive", "68200", "Mulhouse", "contact@amazon.fr", "0389707070" );
		//catalogue_fournisseur cat1 = new catalogue_fournisseur(60.0, true, 4, 5);
		//Parametres.getReq().catUpdate(liste_cat.get(0), 3, 1);
		
	}

}

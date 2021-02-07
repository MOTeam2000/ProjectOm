package org.sid.business;

import java.util.List;
import org.sid.dao.CompteRepository;
import org.sid.entities.Compte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImplCompteBusiness implements CompteBusiness {
    
	@Autowired
	private CompteRepository compteRepository;
	
	// Créer un compte
	
	@Override
	public Compte createCompte(Compte compte) {
		
		// Récuperer la liste des comptes
		List<Compte> listCompte= compteRepository.findAll();
		
		//On crée un objet compte non initilisé (Tous les champs ont null comme valeur)
		Compte c = new Compte();
		
		int i=0;
	    int j = listCompte.size();
	    
	   // Vérifier si l'username existe déja dans la base données
		while(i<j) {
			
			if(listCompte.get(i).getUserName().equals(compte.getUserName()) ) {
				// Si oui, on arrete la boucle en affectant à i une valeur supérieur ou égale à j (exemple j+1)
				i=j+1;
				
				//Et on retourne l'objet c qui a des champs null.
				//En recevant un objet null,Le client-Side va comprendre que userName existe déjà
				return c;
				
			// Sinon on continue la boucle	
		     }else {
			
		    i++;
		     }
	
		}
		
		// Enregistrer le compte dans la base et retourner le compte au client-side
		return compteRepository.save(compte);
	}
	
	
	// Login

	@Override
	public Compte login(String userName, String password) {
		
		//Récupérer les comptes
		List<Compte> listCompte= compteRepository.findAll();
		
		// Créer un objet compte avec champs null.
		Compte c = new Compte();
		
		int i=0;
		int k =0;
	    int j = listCompte.size();
	    
	    // Utiliser la boucle pour parcourir la liste et trouver username
		while(i<j) {
			
			// Vérifier l'existence de username
			if(listCompte.get(i).getUserName().equals(userName) ) {
				
				// Si il existe, on affecte à l'objet c Id et username seulement (C'est mon choix)
				c.setIdCompte(listCompte.get(i).getIdCompte());
				c.setUserName(userName);
				
				// On affecte valeur de i à k (qu'on va utiliser après)
				k=i;
				
				// Et on arrete la boucle
				break;
		     }
			
			// on continue la boucle par l'incrémentation
		    i++;
	
		}
		//Fin de la boucle: si k est strictement inférieur à j, alors username existe 
		
		
		// Après avoir trouvé username ,on vérifie si le password correspond au password envoyé par client-side
		if(listCompte.get(k).getPassword().equals(password)& (k<j)) {
			
			// Si oui,on retourne le compte au client
			return compteRepository.getOne(listCompte.get(k).getIdCompte());
		
		}
		
		// Sinon on retourne l'objet compte c. Dans ce cas on a deux possibilités pour c:
		// 1 - Les champs du id et username sont non null, cela veut dire que username existe mais passord est faux
		// 2 - Les champs du id et username sont null, cela veut dire qu username n'existe pas.
		// En fonction du l'objet compte c, client-side va afficher un message : username n'existe pas ou
		// password est faux

		else {
			return c;
		}
		
	}
	
	
	// Changer le password
	
	@Override
	public Compte changePassword(Long id, String password) {
		
		
		Compte c =compteRepository.findById(id).get();
		c.setPassword(password);
		
		return compteRepository.save(c);
	}

	
	// Mettre à jour le compte
	
	@Override
	public Compte updateCompte(Long id,Compte c) {
		
		c.setIdCompte(id);
		
		return compteRepository.save(c);
	}
	
	
    // Supprimer un compte

	@Override
	public Boolean deleteCompte(Long id) {
		compteRepository.deleteById(id);
		return !(compteRepository.existsById(id));
		
	}

	
	

	
	
	
	

}

package org.sid.restService;


import org.sid.business.CompteBusiness;
import org.sid.entities.Compte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RestComptes {
	
	@Autowired
	private CompteBusiness compteBusiness;

	
	//Créer un compte
	
	@RequestMapping(value ="/comptes",method =RequestMethod.POST)
	public Compte createCompte(@RequestBody Compte c) {
		
		return compteBusiness.createCompte(c);
	}
	

	//Login (J'ai choisi Post pour Login pour que le password n'apprait pas sur le lien)
	
	@RequestMapping(value ="/comptes/login",method =RequestMethod.POST)
	public Compte login(@RequestParam String userName,
			            @RequestParam String password) {
		 
			return compteBusiness.login(userName, password);
		}
		
		
	//Changer le password

	@RequestMapping(value ="/comptes",method =RequestMethod.PUT)
	public Compte changePassword(@RequestParam Long id,@RequestParam String password) {
		
		return compteBusiness.changePassword(id, password);
	}
	

	// Mettre à jour le compte
	
	@RequestMapping(value ="/comptes/update",method =RequestMethod.PUT)
	public Compte updateCompte(@RequestParam Long id, @RequestBody Compte c) {
		
	
		return compteBusiness.updateCompte(id,c);
	}

	
	//Supprimer un compte
	
	@RequestMapping(value ="/comptes",method =RequestMethod.DELETE)
	public Boolean deleteCompte(@RequestParam Long id) {
		return compteBusiness.deleteCompte(id);
		
	}


}

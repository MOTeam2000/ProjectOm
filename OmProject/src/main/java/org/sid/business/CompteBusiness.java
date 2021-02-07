//Interface de Business (Service)

package org.sid.business;

import org.sid.entities.Compte;

public interface CompteBusiness {
	
	public Compte createCompte(Compte c);
	
	public Compte login(String userName,String password);
	
	public Compte changePassword(Long id, String password);
	
	public Compte updateCompte(Long id, Compte compte);
	
	public Boolean deleteCompte(Long id);

	
	
	
}

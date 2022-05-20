package bo;

public class Utilisateur {
	
	private int idUtilisateur, credit;
	private String pseudo, motDePasse, nom, prenom, email, telephone, rue, codePostal, ville;
	private boolean administrateur;
	
	public Utilisateur() {}
	
	public Utilisateur(int idUtilisateur, String pseudo) {
		this.idUtilisateur = idUtilisateur;
		this.pseudo = pseudo;
	}

	public Utilisateur(String pseudo, String motDePasse, String nom, String prenom, String email, String telephone, String rue,
			String codePostal, String ville, int credit, boolean administrateur) {
		this.pseudo = pseudo;
		this.motDePasse = motDePasse;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.credit = credit;
		this.administrateur = administrateur;
	}

	public Utilisateur(int idUtilisateur, String pseudo, String motDePasse, String nom, String prenom, String email, String telephone,
			String rue, String codePostal, String ville, int credit, boolean administrateur) {
		this.idUtilisateur = idUtilisateur;
		this.pseudo = pseudo;
		this.motDePasse = motDePasse;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.credit = credit;
		this.administrateur = administrateur;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public boolean isAdministrateur() {
		return administrateur;
	}

	public void setAdministrateur(boolean administrateur) {
		this.administrateur = administrateur;
	}

	public int getIdUtilisateur() {
		return idUtilisateur;
	}
	
	@Override
	public String toString() {
		return "Utilisateur : \nPseudo :" + this.pseudo +
				", \nNom :" + this.nom +
				", \nPrenom :" + this.prenom +
				", \nEmail :" + this.email +
				", \nTelephone :" + this.telephone +
				", \nRue :" + this.rue +
				", \nCode Postal :" + this.codePostal +
				", \nVille :" + this.ville +
				", \nMot de passe :" + this.motDePasse +
				", \nCrédit :" + this.credit +
				", \nAdmin :" + this.administrateur;
	}
}

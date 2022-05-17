package bo;

import java.time.LocalDate;

public class ArticleVendu {
	
	private int idArticle, prixInitial, prixVente;
	private String nom, description, etat, rue, codePostal, ville;
	private LocalDate dateDebut, dateFin;
	private Utilisateur leVendeur;
	private Categorie laCategorie;
	
	public ArticleVendu() {}

	public ArticleVendu(int prixInitial, int prixVente, String nom, String description, String etat,
			String rue, String codePostal, String ville, LocalDate dateDebut, LocalDate dateFin,
			Utilisateur leVendeur, Categorie laCategorie) {
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;
		this.nom = nom;
		this.description = description;
		this.etat = etat;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.leVendeur = leVendeur;
		this.laCategorie = laCategorie;
	}

	public ArticleVendu(int idArticle, int prixInitial, int prixVente, String nom, String description,
			String etat, String rue, String codePostal, String ville, LocalDate dateDebut,
			LocalDate dateFin, Utilisateur leVendeur, Categorie laCategorie) {
		this.idArticle = idArticle;
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;
		this.nom = nom;
		this.description = description;
		this.etat = etat;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.leVendeur = leVendeur;
		this.laCategorie = laCategorie;
	}

	public int getPrixInitial() {
		return prixInitial;
	}

	public void setPrixInitial(int prixInitial) {
		this.prixInitial = prixInitial;
	}

	public int getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
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

	public LocalDate getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	public LocalDate getDateFin() {
		return dateFin;
	}

	public void setDateFinEncheres(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	public Utilisateur getLeVendeur() {
		return leVendeur;
	}

	public void setLeVendeur(Utilisateur leVendeur) {
		this.leVendeur = leVendeur;
	}

	public Categorie getLaCategorie() {
		return laCategorie;
	}

	public void setLaCategorie(Categorie laCategorie) {
		this.laCategorie = laCategorie;
	}

	public int getIdArticle() {
		return idArticle;
	}

}

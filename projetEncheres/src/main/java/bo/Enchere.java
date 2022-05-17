package bo;

import java.time.LocalDate;

public class Enchere {
	
	private int idEnchere, montant;
	private LocalDate date;
	private ArticleVendu leArticle;
	private Utilisateur leAcheteur;
	
	public Enchere() {}

	public Enchere(LocalDate date, int montant, ArticleVendu leArticle, Utilisateur leAcheteur) {		
		this.date = date;
		this.montant = montant;
		this.leArticle = leArticle;
		this.leAcheteur = leAcheteur;
	}

	public Enchere(int idEnchere, LocalDate date, int montant, ArticleVendu leArticle,
			Utilisateur leAcheteur) {
		this.idEnchere = idEnchere;
		this.date = date;
		this.montant = montant;
		this.leArticle = leArticle;
		this.leAcheteur = leAcheteur;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getMontant() {
		return montant;
	}

	public void setMontant(int montant) {
		this.montant = montant;
	}

	public ArticleVendu getLeArticle() {
		return leArticle;
	}

	public void setLeArticle(ArticleVendu leArticle) {
		this.leArticle = leArticle;
	}

	public Utilisateur getLeAcheteur() {
		return leAcheteur;
	}

	public void setLeAcheteur(Utilisateur leAcheteur) {
		this.leAcheteur = leAcheteur;
	}

	public int getIdEnchere() {
		return idEnchere;
	}

}

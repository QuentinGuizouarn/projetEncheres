package bo;

public class Categorie {
	
	private int idCategorie;
	private String libelle;

	public Categorie() {}

	public Categorie(String libelle) {
		this.libelle = libelle;
	}

	public Categorie(int idCategorie, String libelle) {
		this.idCategorie = idCategorie;
		this.libelle = libelle;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public int getIdCategorie() {
		return idCategorie;
	}

	@Override
	public String toString() {
		return "Categorie [idCategorie=" + idCategorie + ", libelle=" + libelle + "]";
	}
	
}

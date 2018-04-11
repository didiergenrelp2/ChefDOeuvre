package co.simplon.chefdoeuvre.modele;

public enum Etat {

	disponible,
	reserve,
	surSite, // TODO faite un getId_Bureau et si id_bureau = magasin => matos disponible
	enReparation,
//	enIntervention,
	jete;
}

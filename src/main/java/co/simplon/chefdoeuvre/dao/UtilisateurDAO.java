package co.simplon.chefdoeuvre.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import co.simplon.chefdoeuvre.modele.Utilisateur;

@Repository
public class UtilisateurDAO {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	public UtilisateurDAO(JdbcTemplate jdbcTemplate) {
		this.dataSource = jdbcTemplate.getDataSource();
	}
	/**
	 * Filtrer les utilisateurs avec un critère de recherche sur tous les champs
	 * 
	 * @param rechercheUtilisateur
	 * @return
	 * @throws Exception
	 */
	public List <Utilisateur> filtrerUtilisateur(String rechercheUtilisateur) throws Exception{
		List <Utilisateur> utilisateurFiltre = new ArrayList<Utilisateur>();
		Utilisateur utilisateur;
		PreparedStatement pstmt = null;
		ResultSet rs;
		String sql;
		Connection connexion = dataSource.getConnection();
		
		try {
			// Requete SQL
			sql = "SELECT * "
					+ "FROM utilisateur "
					+ "WHERE nom LIKE ? "
					+ "OR prenom LIKE ? "
					+ "OR idrh LIKE ? "
					+ "OR fonction LIKE ?;";
			pstmt = connexion.prepareStatement(sql);
			pstmt.setString(1, "%" + rechercheUtilisateur + "%");
			pstmt.setString(2, "%" + rechercheUtilisateur + "%");
			pstmt.setString(3, "%" + rechercheUtilisateur + "%");
			pstmt.setString(4, "%" + rechercheUtilisateur + "%");
			
			// Log info
			logSQL(pstmt);
			// Lancement requete
			rs = pstmt.executeQuery();
			// resultat requete
			while (rs.next()) {
				utilisateur = recupererUtilisateurRS(rs);
				utilisateurFiltre.add(utilisateur);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
			connexion.close();
		}

		return utilisateurFiltre;
		
	}
//	TODO Import enum
	private Utilisateur recupererUtilisateurRS(ResultSet rs) throws SQLException {
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setId_utilisateur(rs.getLong("id"));
		utilisateur.setNom(rs.getString("nom"));
		utilisateur.setPrenom(rs.getString("prenom"));
		utilisateur.setIdrh(rs.getString("idrh"));
//		utilisateur.setFonction(rs.getString("fonction"));
		
		return utilisateur;
	}
	
	private void logSQL(PreparedStatement pstmt) {
		String sql;

		if (pstmt == null)
			return;

		sql = pstmt.toString().substring(pstmt.toString().indexOf(":") + 2);
		log.debug(sql);
	}
	
}

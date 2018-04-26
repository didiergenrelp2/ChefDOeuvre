package co.simplon.chefdoeuvre.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import co.simplon.chefdoeuvre.modele.Materiel;

@Repository
public class MaterielDAO {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private DataSource dataSource;

	@Autowired
	public MaterielDAO(JdbcTemplate jdbcTemplate) {
		this.dataSource = jdbcTemplate.getDataSource();
	}

	/**
	 * Filtrer le materiel avec un critère de recherche sur tous les champs
	 *
	 * @param filtre
	 * @return
	 * @throws Exception
	 */
	public List<Materiel> filtrerMateriel(String filtre) throws Exception {
		List<Materiel> materielFiltre = new ArrayList<Materiel>();

		Materiel materiel;
		PreparedStatement pstmt = null;
		ResultSet rs;
		String sql;
		//TODO corriger probleme tri date
		SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			// Requete SQL
			sql = "SELECT * " 
					+ "FROM materiel " 
					+ "WHERE domaine LIKE ? " 
					+ "OR marque LIKE ? " 
					+ "OR modele LIKE ? "
					+ "OR type LIKE ? " 
					+ "OR code_parc LIKE ? " 
					+ "OR numero_serie LIKE ? " 
					+ "OR code_article LIKE ?; ";
					//+ "OR date_fin_garantie LIKE ?; " ;
					//+ "OR etat LIKE ?;";
			pstmt = dataSource.getConnection().prepareStatement(sql);
			pstmt.setString(1, "%" + filtre + "%");
			pstmt.setString(2, "%" + filtre + "%");
			pstmt.setString(3, "%" + filtre + "%");
			pstmt.setString(4, "%" + filtre + "%");
			pstmt.setString(5, "%" + filtre + "%");
			pstmt.setString(6, "%" + filtre + "%");
			pstmt.setString(7, "%" + filtre + "%");
		//	pstmt.setString(8,"%" + SDF.parse(filtre) + "%"); 
			//pstmt.setString(9, "%" + filtre + "%");

			// Log info
			logSQL(pstmt);
			// Lancement requete
			rs = pstmt.executeQuery();
			// resultat requete
			while (rs.next()) {
				materiel = recupererMaterielRS(rs);
				materielFiltre.add(materiel);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
		}

		return materielFiltre;
	}
		
	//TODO get materielNonAffecte
	
	/**
	 * Lier un materiel à un bureau
	 * 
	 * @param id_bureau
	 * @param id_materiel
	 * @throws Exception
	 */
	public void poserMaterielDansBureau(long id_bureau, long id_materiel) throws Exception {
		PreparedStatement pstmt = null;
		String sql;
		try {
			// Requete SQL
			sql = "UPDATE materiel SET id_bureau = ? WHERE id_materiel = ?;";
			pstmt = dataSource.getConnection().prepareStatement(sql);
			pstmt.setLong(1, id_bureau);
			pstmt.setLong(2, id_materiel);
			// Log info
			logSQL(pstmt);
			// Lancement requete
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
		}
	}
	
	/**
	 * Affiche la liste du materiel du bureau
	 * 
	 * @param id_bureau
	 * @throws Exception
	 */
	
		public void listerMaterielDuBureau(long id_bureau) throws Exception {
			PreparedStatement pstmt = null;
			String sql;
			try {
				// Requete SQL
				sql = "SELECT * FROM materiel WHERE id_bureau = ?;";
				pstmt = dataSource.getConnection().prepareStatement(sql);
				pstmt.setLong(1, id_bureau);
				// Log info
				logSQL(pstmt);
				// Lancement requete
				pstmt.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
				log.error("SQL Error !:" + pstmt.toString(), e);
				throw e;
			} finally {
				pstmt.close();
			}
		}	

	/**
	 * Supprimer le lien entre le materiel et le bureau
	 * 
	 * @param id_bureau
	 * @param id_materiel
	 * @throws Exception
	 */
	public void supprimerMaterielDuBureau(long id_bureau, long id_materiel) throws Exception {
		PreparedStatement pstmt = null;
		String sql;

		try {
			// Requete SQL
			sql = "UPDATE materiel SET id_bureau = null WHERE id_materiel = ?;";
			pstmt = dataSource.getConnection().prepareStatement(sql);
			pstmt.setLong(1, id_materiel);
			// Execution requete
			int resultat = pstmt.executeUpdate();
			if (resultat != 1) {
				throw new Exception("Cette entrée n'existe pas dans la base de donnée.");
			} else {
				System.out.println("Le lien a été supprimé");
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
		}
	}

	/**
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private Materiel recupererMaterielRS(ResultSet rs) throws SQLException {
		Materiel materiel = new Materiel();

		// TODO chercher commment importer une enum
		// TODO penser à rajouter les domaines dans le data.sql après modification
		materiel.setId_materiel(rs.getLong("id_materiel"));
		// materiel.setDomaine(rs.getString("domaine"));
		materiel.setType(rs.getString("type"));
		materiel.setMarque(rs.getString("marque"));
		materiel.setModele(rs.getString("modele"));
		materiel.setNumero_serie(rs.getString("numero_serie"));
		materiel.setCode_parc(rs.getString("code_parc"));
		materiel.setCode_article(rs.getString("code_article"));
		materiel.setDate_fin_garantie(rs.getDate("date_fin_garantie"));
		// materiel.setEtat(rs.getString("etat"));

		return materiel;
	}

	/**
	 * 
	 * @param pstmt
	 */
	private void logSQL(PreparedStatement pstmt) {
		String sql;

		if (pstmt == null)
			return;

		sql = pstmt.toString().substring(pstmt.toString().indexOf(":") + 2);
		log.debug(sql);
	}
}

package co.simplon.chefdoeuvre.dao;

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

import co.simplon.chefdoeuvre.modele.Bureau;
import co.simplon.chefdoeuvre.modele.Materiel;

@Repository
public class BureauDAO {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private DataSource dataSource;

	@Autowired
	public BureauDAO(JdbcTemplate jdbcTemplate) {
		this.dataSource = jdbcTemplate.getDataSource();
	}
	/**
	 * Filtrer le bureau avec un critère de recherche sur tous les champs
	 *
	 * @param rechercheBureau
	 * @return
	 * @throws Exception
	 */
	public List<Bureau> filtrerBureau(String rechercheBureau) throws Exception {
		List<Bureau> bureauFiltre = new ArrayList<Bureau>();

		Bureau bureau;
		PreparedStatement pstmt = null;
		ResultSet rs;
		String sql;

		try {
			// Requete SQL
			sql = "SELECT * "
					+ "FROM bureau "
					+ "WHERE nom_bureau LIKE ? "
					+ "OR code_regate LIKE ? "
					+ "OR adresse LIKE ? "
					+ "OR code_postal LIKE ? "
					+ "OR ville LIKE ? "
					+ "OR telephone LIKE ? ";
			pstmt = dataSource.getConnection().prepareStatement(sql);
			pstmt.setString(1, "%" + rechercheBureau + "%");
			pstmt.setString(2, "%" + rechercheBureau + "%");
			pstmt.setString(3, "%" + rechercheBureau + "%");
			pstmt.setString(4, "%" + rechercheBureau + "%");
			pstmt.setString(5, "%" + rechercheBureau + "%");
			pstmt.setString(6, "%" + rechercheBureau + "%");

			// Log info
			logSQL(pstmt);
			// Lancement requete
			rs = pstmt.executeQuery();
			// resultat requete
			while (rs.next()) {
				bureau = recupererBureauRS(rs);
				bureauFiltre.add(bureau);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
		}

		return bureauFiltre;
	}
	
	/**
	 * Rechercher le materiel lié à un bureau
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<Materiel> recupererMaterielDuBureau(Long id) throws Exception {
		Materiel materiel;
		PreparedStatement pstmt = null;
		ResultSet rs;
		String sql;
		ArrayList<Materiel> listeMateriel = new ArrayList<Materiel>();

		try {
			// TODO modifier requete
			// Requete SQL
			sql = 	"SELECT * " 
					+ "FROM materiel " 
					+ "WHERE id_bureau = ?;";

			pstmt = dataSource.getConnection().prepareStatement(sql);
			pstmt.setLong(1, id);
			// Log info
			logSQL(pstmt);
			// Lancement requete
			rs = pstmt.executeQuery();
			// resultat requete
			while (rs.next()) {
				materiel = recupererMaterielRS(rs);
				listeMateriel.add(materiel);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
		}

		return listeMateriel;
	}

	private void logSQL(PreparedStatement pstmt) {
		String sql;

		if (pstmt == null)
			return;

		sql = pstmt.toString().substring(pstmt.toString().indexOf(":") + 2);
		log.debug(sql);
	}

	private Bureau recupererBureauRS(ResultSet rs) throws SQLException {
		Bureau bureau = new Bureau();
		bureau.setId_bureau(rs.getLong("id_bureau"));
		bureau.setNom_bureau(rs.getString("nom_bureau"));
		bureau.setCode_regate(rs.getLong("code_regate"));
		bureau.setAdresse(rs.getString("adresse"));
		bureau.setCode_postal(rs.getLong("code_postal"));
		bureau.setVille(rs.getString("ville"));
		bureau.setTelephone(rs.getString("telephone"));

		return bureau;
	}
	
	private Materiel recupererMaterielRS(ResultSet rs) throws SQLException {
		Materiel materiel = new Materiel();

		// TODO chercher commment importer une enum
		materiel.setId_materiel(rs.getLong("id"));
//		materiel.setDomaine(rs.getString("domaine"));
		materiel.setType(rs.getString("type"));
		materiel.setMarque(rs.getString("marque"));
		materiel.setModele(rs.getString("modele"));
		materiel.setNumero_serie(rs.getString("numero_serie"));
		materiel.setCode_parc(rs.getString("code_parc"));
		materiel.setCode_article(rs.getString("code_article"));
		materiel.setDate_fin_garantie(rs.getDate("date_fin_garantie"));
//		materiel.setEtat(rs.getString("etat"));

		return materiel;
	}

}

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
	 * Rechercher les materiels liées à une bureau
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<Materiel> recupererMaterielDeBureau(Long id) throws Exception {
		Materiel materiel;
		PreparedStatement pstmt = null;
		ResultSet rs;
		String sql;
		ArrayList<Materiel> listeMateriel = new ArrayList<Materiel>();

		try {
			// TODO utilité table jointure ?
			// Requete SQL
			sql = " SELECT materiel.*\r\n" + "  FROM materiel\r\n" + "INNER JOIN bureau_materiel\r\n"
					+ "  ON materiel.id_materiel = bureau_materiel.id_materiel\r\n" + "INNER JOIN bureau\r\n"
					+ "  ON bureau_materiel.id_bureau = bureau.id_bureau\r\n" + "  WHERE bureau.id_bureau = ?;";

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

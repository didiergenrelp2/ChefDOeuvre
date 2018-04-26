package co.simplon.chefdoeuvre.util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import co.simplon.chefdoeuvre.modele.Domaine;
import co.simplon.chefdoeuvre.modele.Materiel;

public class JSONMaterielDeserializer extends JsonDeserializer<Materiel> {	

	@Override
	public Materiel deserialize(JsonParser jsonParser, DeserializationContext deserializer) throws IOException, JsonProcessingException {
		ObjectCodec objectCodec = jsonParser.getCodec();
		JsonNode jsonNode = objectCodec.readTree(jsonParser);
		
		SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
		
		Materiel materiel = new Materiel();
		materiel.setId_materiel(jsonNode.get("id_materiel").asLong());
		String domaineStr = jsonNode.get("domaine").asText();		
		Domaine domaine = Domaine.valueOf(domaineStr);		
		materiel.setDomaine(domaine);
		materiel.setType(jsonNode.get("type").asText());
		materiel.setMarque(jsonNode.get("marque").asText());
		materiel.setModele(jsonNode.get("modele").asText());
		materiel.setNumero_serie(jsonNode.get("numero_serie").asText());
		materiel.setCode_parc(jsonNode.get("code_parc").asText());
		materiel.setCode_article(jsonNode.get("code_article").asText());
		try {
			materiel.setDate_fin_garantie(SDF.parse(jsonNode.get("date_fin_garantie").asText()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
//		materiel.setEtat(jsonNode.get("etat").asText());

		return materiel;		
	}

}

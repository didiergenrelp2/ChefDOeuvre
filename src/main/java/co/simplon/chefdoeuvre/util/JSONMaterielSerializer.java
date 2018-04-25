package co.simplon.chefdoeuvre.util;

import java.io.IOException;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import co.simplon.chefdoeuvre.modele.Materiel;

public class JSONMaterielSerializer extends JsonSerializer<Materiel> {
		
	@Override
	public void serialize(Materiel materiel, JsonGenerator jsonGenerator, SerializerProvider serializers)
			throws IOException, JsonProcessingException {
		    		
		SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
		
		jsonGenerator.writeStartObject();
		jsonGenerator.writeNumberField("id_materiel", materiel.getId_materiel());
		jsonGenerator.writeStringField("domaine", materiel.getDomaine().toString());
		jsonGenerator.writeStringField("type", materiel.getType());
		jsonGenerator.writeStringField("marque", materiel.getMarque());
		jsonGenerator.writeStringField("modele", materiel.getModele());
		jsonGenerator.writeStringField("numero_serie", materiel.getNumero_serie());
		jsonGenerator.writeStringField("code_parc", materiel.getCode_parc());
		jsonGenerator.writeStringField("code_article", materiel.getCode_article());
		jsonGenerator.writeStringField("date_fin_garantie",SDF.format(materiel.getDate_fin_garantie()));		
//		jsonGenerator.writeStringField("etat", materiel.getEtat());
		jsonGenerator.writeEndObject();
	}

}

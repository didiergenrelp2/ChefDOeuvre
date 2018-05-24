package co.simplon.chefdoeuvre.ChefDOeuvre;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import co.simplon.chefdoeuvre.controleur.UtilisateurControleur;
import co.simplon.chefdoeuvre.dao.UtilisateurDAO;
import co.simplon.chefdoeuvre.modele.Utilisateur;
import co.simplon.chefdoeuvre.service.UtilisateurService;
@RunWith(SpringRunner.class)
@WebMvcTest(value=UtilisateurControleur.class, secure=false)
public class TestUtilisateurCrud {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UtilisateurService utilisateurService;
	
	@MockBean
	private UtilisateurDAO utilisateurDAO;
	
	@Test
	public void recupererUtilisateurTest() throws Exception{
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setNom("Test");
		
		Mockito.when(utilisateurService.recupererUtilisateur((long)1)).thenReturn(utilisateur);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("http://localhost:8080/api/utilisateur/1").accept(MediaType.APPLICATION_JSON);
		MvcResult resultat = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(resultat.getResponse());
        String expected = "{id:1, nom:Test}";
        JSONAssert.assertEquals(expected, resultat.getResponse().getContentAsString(), false);
	}
}

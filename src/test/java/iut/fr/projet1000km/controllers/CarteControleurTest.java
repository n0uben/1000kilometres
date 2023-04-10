package iut.fr.projet1000km.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import iut.fr.projet1000km.models.Carte;
import iut.fr.projet1000km.repository.CarteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class CarteControleurTest {

    private MockMvc mockMvc;

    @MockBean
    private CarteRepository carteRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(new CarteControleur(carteRepository)).build();
    }

    @Test
    void testGetAll() throws Exception {
        //crée le mock de la fonction findal
        List<Carte> cartes = new ArrayList<>();
        cartes.add(new Carte(1L, "carte1", 100, "effet1", 5));
        cartes.add(new Carte(2L, "carte2", 200, "effet2", 10));
        when(carteRepository.findAll()).thenReturn(cartes);

        //fait la requete avec appel au mock
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/carte").accept(MediaType.APPLICATION_JSON)).andReturn();

        //on verifie le resultat de la requete
        Assertions.assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        Assertions.assertEquals("[{\"idCarte\":1,\"nom\":\"carte1\",\"km\":100,\"effet\":\"effet1\",\"nbDispo\":5},{\"idCarte\":2,\"nom\":\"carte2\",\"km\":200,\"effet\":\"effet2\",\"nbDispo\":10}]"
                , mvcResult.getResponse().getContentAsString());
        Mockito.verify(carteRepository, times(1)).findAll();
    }

    @Test
    void testGetById() throws Exception {
        Carte carte = new Carte(1l, "carte 1", 100, "effet 1", 2);
        when(carteRepository.findById(any())).thenReturn(Optional.of(carte));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/carte/1").accept(MediaType.APPLICATION_JSON)).andReturn();

        Assertions.assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        Assertions.assertTrue(mvcResult.getResponse().getContentAsString().contains("\"idCarte\":1"));
        Mockito.verify(carteRepository, times(1)).findById(any());
    }

    @Test
    void testGetByIdNotFound() throws Exception {
        when(carteRepository.findById(any())).thenReturn(Optional.empty());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/carte/1").accept(MediaType.APPLICATION_JSON)).andReturn();

        Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), mvcResult.getResponse().getStatus());
        Mockito.verify(carteRepository, times(1)).findById(any());
    }

    @Test
    void testCreer() throws Exception {
        Carte carte = new Carte(1l, "carte 1", 100, "effet 1", 10);
        when(carteRepository.saveAndFlush(any(Carte.class))).thenReturn(carte);

        String carteJson = "{\"idCarte\":1,\"nom\":\"carte 1\",\"km\":100,\"effet\":\"effet 1\",\"nbDispo\":10}";

        //on fait la requete en envoyant le Json et on pré-test le statut OK de la réponse
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/carte/creer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(carteJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        verify(carteRepository, times(1)).saveAndFlush(any(Carte.class));

        //on transforme le json en objet de la classe carte
        ObjectMapper objectMapper = new ObjectMapper();
        Carte createdCarte = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Carte.class);

        Assertions.assertEquals(carte.getIdCarte(), createdCarte.getIdCarte());
        Assertions.assertEquals(carte.getNom(), createdCarte.getNom());
        Assertions.assertEquals(carte.getKm(), createdCarte.getKm());
        Assertions.assertEquals(carte.getEffet(), createdCarte.getEffet());
        Assertions.assertEquals(carte.getNbDispo(), createdCarte.getNbDispo());
    }

    @Test
    void testModifierCarteNotFound() throws Exception {

        String updatedCarteJson = "{\"idCarte\":1,\"nom\":\"carte 1\",\"km\":100,\"effet\":\"effet 1\",\"nbDispo\":10}";

        when(carteRepository.findById(any())).thenReturn(Optional.empty());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/carte/modifier/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedCarteJson))
                .andReturn();

        verify(carteRepository,  times(1)).findById(1L);
        verify(carteRepository,  never()).saveAndFlush(any(Carte.class));

        Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), mvcResult.getResponse().getStatus());
    }

    @Test
    void testModifierCarteExists() throws Exception {
        Carte existingCarte = new Carte(1l, "carte 1", 100, "effet 1", 10);
        Carte updatedCarte = new Carte(1l, "carte 1", 200, "effet 1", 10);

        String updatedCarteJson = "{\"idCarte\":1,\"nom\":\"carte 1\",\"km\":100,\"effet\":\"effet 1\",\"nbDispo\":10}";

        when(carteRepository.findById(existingCarte.getIdCarte())).thenReturn(Optional.of(existingCarte));
        when(carteRepository.saveAndFlush(existingCarte)).thenReturn(updatedCarte);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/carte/modifier/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedCarteJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        verify(carteRepository, times(1)).findById(existingCarte.getIdCarte());
        verify(carteRepository, times(1)).saveAndFlush(existingCarte);

        ObjectMapper objectMapper = new ObjectMapper();
        Carte carteReturned = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Carte.class);

        Assertions.assertEquals(updatedCarte.getIdCarte(), carteReturned.getIdCarte());
        Assertions.assertEquals(updatedCarte.getNom(), carteReturned.getNom());
        Assertions.assertEquals(updatedCarte.getKm(), carteReturned.getKm());
        Assertions.assertEquals(updatedCarte.getEffet(), carteReturned.getEffet());
        Assertions.assertEquals(updatedCarte.getNbDispo(), carteReturned.getNbDispo());
    }

    @Test
    void testSupprimerCarteExists() throws Exception {
        Long id = 1L;
        Carte carte = new Carte(id, "carte 1", 100, "effet 1", 10);

        when(carteRepository.findById(id)).thenReturn(Optional.of(carte));

        mockMvc.perform(MockMvcRequestBuilders.delete("/carte/supprimer/" + id ))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(""))
                .andReturn();

        verify(carteRepository, times(1)).findById(id);
        verify(carteRepository, times(1)).deleteById(id);
    }

    @Test
    void testSupprimerCarteNotFound() throws Exception {
        when(carteRepository.findById(any())).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.delete("/carte/supprimer/1")).andExpect(MockMvcResultMatchers.status().isNotFound());
        verify(carteRepository, times(1)).findById(any());
        verify(carteRepository, never()).deleteById(any());
    }


}

package iut.fr.projet1000km.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import iut.fr.projet1000km.models.Partie;
import iut.fr.projet1000km.models.Utilisateur;
import iut.fr.projet1000km.repository.PartieRepository;
import iut.fr.projet1000km.repository.UtilisateurRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
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
class PartieControleurTest {

    private MockMvc mockMvc;

    @Mock
    private PartieRepository partieRepository;
    @Mock
    private UtilisateurRepository utilisateurRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(new PartieControleur(partieRepository, utilisateurRepository)).build();
    }

    @Test
    void testGetAll() throws Exception {
        List<Partie> parties = new ArrayList<>();
        parties.add(new Partie((Long) 1L, 4, 30, "code1"));
        parties.add(new Partie((Long) 2L, 3, 40, "code2"));
        String partiesJson = "[{\"idPartie\":1,\"nombreJoueurs\":4,\"dureeTour\":30,\"codePartie\":\"code1\",\"joueurs\":[]},{\"idPartie\":2,\"nombreJoueurs\":3,\"dureeTour\":40,\"codePartie\":\"code2\",\"joueurs\":[]}]";

        when(partieRepository.findAll()).thenReturn(parties);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/partie")).andReturn();

        Assertions.assertEquals(HttpStatus.OK.value(),mvcResult.getResponse().getStatus());
        Assertions.assertEquals(partiesJson, mvcResult.getResponse().getContentAsString());
    }

    @Test
    void testGetByIdPartieExists() throws Exception {
        Long id = (Long) 1L;

        Partie partie = new Partie(id, 4, 30, "code1");

        when(partieRepository.findById(partie.getIdPartie())).thenReturn(Optional.of(partie));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/partie/1")).andReturn();

        Assertions.assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        Assertions.assertTrue(mvcResult.getResponse().getContentAsString().contains("idPartie\":1"));

        verify(partieRepository, times(1)).findById(any());

    }

    @Test
    void testGetByIdPartieNotFound() throws Exception {
        when(partieRepository.findById(any())).thenReturn(Optional.empty());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/partie/1"))
                .andReturn();

        Assertions.assertEquals(HttpStatus.NOT_FOUND.value(),mvcResult.getResponse().getStatus());
        verify(partieRepository, times(1)).findById(any());

    }

    @Test
    void testCreer() throws Exception {
        Partie partie = new Partie();
        partie.setNombreJoueurs(3);
        partie.setDureeTour(60);
        partie.setCodePartie("code1");

        List<Utilisateur> joueurs = new ArrayList<>();
        Utilisateur createur = new Utilisateur();
        createur.setIdUtilisateur((Long) 1L);
        joueurs.add(createur);
        partie.setJoueurs(joueurs);

        when(utilisateurRepository.findById((Long) 1L)).thenReturn(java.util.Optional.of(createur));
        when(partieRepository.saveAndFlush(any())).thenReturn(partie);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/partie/creer")
                        .content("{\"nombreJoueurs\":3,\"dureeTour\":60,\"codePartie\":\"ABC123\",\"joueurs\":[{\"idUtilisateur\":1,\"nom\":\"\",\"prenom\":\"\",\"adresseMail\":\"\",\"motDePasse\":\"\",\"parties\":[]}]}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String returnedPartieJson = mvcResult.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        Partie returnedPartie = objectMapper.readValue(returnedPartieJson, Partie.class);

        Assertions.assertEquals(partie.getIdPartie(), returnedPartie.getIdPartie());
        Assertions.assertEquals(partie.getNombreJoueurs(), returnedPartie.getNombreJoueurs());
        Assertions.assertEquals(partie.getDureeTour(), returnedPartie.getDureeTour());
        Assertions.assertEquals(partie.getCodePartie(), returnedPartie.getCodePartie());

        verify(utilisateurRepository, times(1)).findById(any());
        verify(partieRepository, times(1)).saveAndFlush(any(Partie.class));

    }

    @Test
    void testModifierPartieExists() throws Exception {
        Partie partie = new Partie();
        partie.setIdPartie((Long) 1L);
        partie.setNombreJoueurs(3);
        partie.setDureeTour(60);
        partie.setCodePartie("code1");

        Partie updatedPartie = new Partie();
        updatedPartie.setIdPartie((Long) 1L);
        updatedPartie.setNombreJoueurs(4);
        updatedPartie.setDureeTour(45);
        updatedPartie.setCodePartie("code 1");

        String updatePartieJson = "{\"idPartie\":1,\"nombreJoueurs\":4,\"dureeTour\":45,\"codePartie\":\"code 1\"}";

        when(partieRepository.findById((Long) 1L)).thenReturn(Optional.of(partie));
        when(partieRepository.saveAndFlush(partie)).thenReturn(updatedPartie);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/partie/modifier/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatePartieJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String returnedPartie = mvcResult.getResponse().getContentAsString();

        Assertions.assertTrue(returnedPartie.contains("nombreJoueurs\":4"));
        Assertions.assertTrue(returnedPartie.contains("dureeTour\":45"));
        Assertions.assertTrue(returnedPartie.contains("codePartie\":\"code 1"));


    }

    @Test
    void testModifierPartieNotFound() throws Exception {
        String partieJson = "{\"idPartie\":1,\"nombreJoueurs\":4,\"dureeTour\":30,\"codePartie\":\"code1\",\"joueurs\":[]}";
        when(partieRepository.findById(any())).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.put("/partie/modifier/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(partieJson))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();

        verify(partieRepository, times(1)).findById(any());
        verify(partieRepository, never()).saveAndFlush(any(Partie.class));
    }

    @Test
    void testDeletePartieExists() throws Exception {

        Long id = (Long) 1L;
        Partie partie = new Partie(id, 4, 30, "code1");

        when(partieRepository.findById(id)).thenReturn(Optional.of(partie));
        mockMvc.perform(MockMvcRequestBuilders.delete("/partie/supprimer/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(""))
                .andReturn();
        verify(partieRepository, times(1)).findById(any());
        verify(partieRepository, times(1)).deleteById(any());

    }
    @Test
    void testDeletePartieNotFound() throws Exception {
        when(partieRepository.findById(any())).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.delete("/partie/supprimer/1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();

        verify(partieRepository, times(1)).findById(any());
        verify(partieRepository, never()).deleteById(any());

    }


}

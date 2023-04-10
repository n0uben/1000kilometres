package iut.fr.projet1000km.controllers;

import iut.fr.projet1000km.models.Partie;
import iut.fr.projet1000km.repository.PartieRepository;
import iut.fr.projet1000km.repository.UtilisateurRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
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
        parties.add(new Partie(1L, 4, 30, "code1"));
        parties.add(new Partie(2L, 3, 40, "code2"));
        String partiesJson = "[{\"idPartie\":1,\"nombreJoueurs\":4,\"dureeTour\":30,\"codePartie\":\"code1\",\"joueurs\":[]},{\"idPartie\":2,\"nombreJoueurs\":3,\"dureeTour\":40,\"codePartie\":\"code2\",\"joueurs\":[]}]";

        when(partieRepository.findAll()).thenReturn(parties);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/partie")).andReturn();

        Assertions.assertEquals(HttpStatus.OK.value(),mvcResult.getResponse().getStatus());
        Assertions.assertEquals(partiesJson, mvcResult.getResponse().getContentAsString());
    }

    @Test
    void testGetByIdPartieExists() throws Exception {
        Long id = 1L;
        Partie partie = new Partie(1L, 4, 30, "code1");

        when(partieRepository.findById(partie.getIdPartie())).thenReturn(Optional.of(partie));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/partie/1")).andReturn();

        Assertions.assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        Assertions.assertTrue(mvcResult.getResponse().getContentAsString().contains("idPartie\":1"));
    }

    @Test
    void testGetByIdPartieNotFound() throws Exception {
        when(partieRepository.findById(any())).thenReturn(Optional.empty());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/partie/1"))
                .andReturn();

        Assertions.assertEquals(HttpStatus.NOT_FOUND.value(),mvcResult.getResponse().getStatus());
    }

    @Test
    void testDeletePartieExists() throws Exception {

        Long id = 1L;
        Partie partie = new Partie(1L, 4, 30, "code1");

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

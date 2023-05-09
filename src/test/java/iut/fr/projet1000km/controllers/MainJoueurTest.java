package iut.fr.projet1000km.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import iut.fr.projet1000km.models.MainJoueur;
import iut.fr.projet1000km.repository.MainJoueurRepository;
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

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class MainJoueurTest {

    private MockMvc mockMvc;
    @Mock
    private MainJoueurRepository mainJoueurRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(new MainJoueurControleur(mainJoueurRepository)).build();
    }

    @Test
    void testGetByIdMainExists() throws Exception {
        MainJoueur mainJoueur = new MainJoueur();
        mainJoueur.setIdMain(1L);
        when(mainJoueurRepository.findById(mainJoueur.getIdMain())).thenReturn(Optional.of(mainJoueur));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/main/" + mainJoueur.getIdMain()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Assertions.assertTrue(mvcResult.getResponse().getContentAsString().contains("idMain\":1"));
    }

    @Test
    void testGetByIdMainNotFound() throws Exception {
        when(mainJoueurRepository.findById(any())).thenReturn(Optional.empty());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/main/1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();
    }

    @Test
    void testCreer() throws Exception {
        MainJoueur mainJoueur = new MainJoueur();
        mainJoueur.setIdMain(1L);

        String mainJson = "{\"idMain\":1,\"partie\":null,\"utilisateur\":null,\"cartes\":null}";

        when(mainJoueurRepository.saveAndFlush(any(MainJoueur.class))).thenReturn(mainJoueur);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/main/creer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mainJson)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        verify(mainJoueurRepository, times(1)).saveAndFlush(any(MainJoueur.class));

        Assertions.assertTrue(mvcResult.getResponse().getContentAsString().contains("idMain\":1"));
    }

    @Test
    void testModifierMainExists() throws Exception {
        MainJoueur existingMainJoueur = new MainJoueur();
        existingMainJoueur.setIdMain(1L);
        MainJoueur updatedMainJoueur = new MainJoueur();
        existingMainJoueur.setIdMain(1L);

        String updatedMainJoueurJson = "{\"idMain\":1,\"partie\":null,\"utilisateur\":null,\"cartes\":null}";

        when(mainJoueurRepository.findById(existingMainJoueur.getIdMain())).thenReturn(Optional.of(existingMainJoueur));
        when(mainJoueurRepository.saveAndFlush(existingMainJoueur)).thenReturn(updatedMainJoueur);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/main/modifier/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedMainJoueurJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        verify(mainJoueurRepository, times(1)).findById(existingMainJoueur.getIdMain());
        verify(mainJoueurRepository, times(1)).saveAndFlush(existingMainJoueur);

        ObjectMapper objectMapper = new ObjectMapper();
        MainJoueur returnedMainJoueur = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), MainJoueur.class);

        Assertions.assertEquals(updatedMainJoueur.getIdMain(), returnedMainJoueur.getIdMain());

    }

    @Test
    void testModifierMainNotFound() throws Exception {

        String mainJson = "{\"idMain\":1,\"partie\":null,\"utilisateur\":null,\"cartes\":null}";

        when(mainJoueurRepository.findById(any())).thenReturn(Optional.empty());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/main/modifier/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mainJson))
                .andReturn();

        verify(mainJoueurRepository, times(1)).findById(1L);
        verify(mainJoueurRepository, never()).saveAndFlush(any(MainJoueur.class));

        Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), mvcResult.getResponse().getStatus());

    }

    @Test
    void testDeleteMainNotFound() throws Exception {
        Long id = 1L;
        when(mainJoueurRepository.findById(any())).thenReturn(Optional.empty());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/main/supprimer/" + id))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();

        verify(mainJoueurRepository, times(1)).findById(id);
        verify(mainJoueurRepository, never()).deleteById(id);
    }

    @Test
    void testDeleteMainExists() throws Exception {
        Long id = 1L;
        MainJoueur mainJoueur = new MainJoueur();
        mainJoueur.setIdMain(id);

        when(mainJoueurRepository.findById(any())).thenReturn(Optional.of(mainJoueur));

        mockMvc.perform(MockMvcRequestBuilders.delete("/main/supprimer/" + id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(""))
                .andReturn();

        verify(mainJoueurRepository, times(1)).findById(id);
        verify(mainJoueurRepository, times(1)).deleteById(id);
    }
}

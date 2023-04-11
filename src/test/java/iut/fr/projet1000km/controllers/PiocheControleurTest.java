package iut.fr.projet1000km.controllers;

import iut.fr.projet1000km.models.Pioche;
import iut.fr.projet1000km.repository.PiocheRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PiocheControleurTest {

    private MockMvc mockMvc;

    @Mock
    private PiocheRepository piocheRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(new PiocheControleur(piocheRepository)).build();
    }

    @Test
    void testGetAll() throws Exception {
        Pioche pioche1 = new Pioche();
        pioche1.setIdPioche(1L);
        Pioche pioche2 = new Pioche();
        pioche2.setIdPioche(2L);

        List<Pioche> pioches = new ArrayList<>();
        pioches.add(pioche1);
        pioches.add(pioche2);

        String expectedJson = "[{\"idPioche\":1,\"partie\":null,\"cartes\":null},{\"idPioche\":2,\"partie\":null,\"cartes\":null}]";

        when(piocheRepository.findAll()).thenReturn(pioches);

        mockMvc.perform(MockMvcRequestBuilders.get("/pioche"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedJson))
                .andReturn();
    }

    @Test
    void testGetByIdPiocheExists() throws Exception {
        Pioche pioche = new Pioche();
        pioche.setIdPioche(1L);

        String expectedJson = "{\"idPioche\":1,\"partie\":null,\"cartes\":null}";

        when(piocheRepository.findById(any())).thenReturn(Optional.of(pioche));

        mockMvc.perform(MockMvcRequestBuilders.get("/pioche/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedJson))
                .andReturn();

        verify(piocheRepository, times(1)).findById(any());
    }

    @Test
    void testGetByIdPiocheNotFound() throws Exception {
        when(piocheRepository.findById(any())).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/pioche/1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();

        verify(piocheRepository, times(1)).findById(any());
    }
}

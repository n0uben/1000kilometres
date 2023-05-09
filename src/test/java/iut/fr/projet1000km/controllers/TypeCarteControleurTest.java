package iut.fr.projet1000km.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import iut.fr.projet1000km.models.TypeCarte;
import iut.fr.projet1000km.repository.TypeCarteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
class TypeCarteControleurTest {

    private MockMvc mockMvc;

    @Mock
    private TypeCarteRepository typeCarteRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(new TypeCarteControleur(typeCarteRepository)).build();
    }

    @Test
    void testGetAll() throws Exception {
        TypeCarte typeCarte1 = new TypeCarte();
        typeCarte1.setIdTypeCarte((Long) 1L);
        typeCarte1.setNomTypeCarte("type carte 1");

        TypeCarte typeCarte2 = new TypeCarte();
        typeCarte2.setIdTypeCarte((Long) 2L);
        typeCarte2.setNomTypeCarte("type carte 2");

        List<TypeCarte> typeCarteList = new ArrayList<>();
        typeCarteList.add(typeCarte1);
        typeCarteList.add(typeCarte2);

        when(typeCarteRepository.findAll()).thenReturn(typeCarteList);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/typecarte"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();


        String expectedReturn = "[{\"idTypeCarte\":1,\"nomTypeCarte\":\"type carte 1\"},{\"idTypeCarte\":2,\"nomTypeCarte\":\"type carte 2\"}]";

        Assertions.assertEquals(expectedReturn, mvcResult.getResponse().getContentAsString());

        verify(typeCarteRepository, times(1)).findAll();

    }

    @Test
    void testGetByIdTypeCarteExists() throws Exception {
        TypeCarte typeCarte = new TypeCarte();
        typeCarte.setIdTypeCarte((Long) 1L);
        typeCarte.setNomTypeCarte("type carte 1");

        String expectedResult = "{\"idTypeCarte\":1,\"nomTypeCarte\":\"type carte 1\"}";

        when(typeCarteRepository.findById(any())).thenReturn(Optional.of(typeCarte));

        mockMvc.perform(MockMvcRequestBuilders.get("/typecarte/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedResult))
                .andReturn();

        verify(typeCarteRepository, times(1)).findById(any());
    }

    @Test
    void testGetByIdCarteNotFound() throws Exception {
        when(typeCarteRepository.findById(any())).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/typecarte/1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();

        verify(typeCarteRepository, times(1)).findById(any());
    }

    @Test
    void testCreer() throws Exception {
        TypeCarte typeCarte = new TypeCarte();
        typeCarte.setIdTypeCarte((Long) 1L);
        typeCarte.setNomTypeCarte("type carte 1");

        String typeCarteJson = "{\"idTypeCarte\":1,\"nomTypeCarte\":\"type carte 1\"}";

        when(typeCarteRepository.saveAndFlush(any(TypeCarte.class))).thenReturn(typeCarte);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/typecarte/creer")
                        .content(typeCarteJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(typeCarteJson))
                .andReturn();

        verify(typeCarteRepository, times(1)).saveAndFlush(any(TypeCarte.class));
    }

    @Test
    void testModifierTypeCarteExists() throws Exception {
        TypeCarte typeCarte = new TypeCarte();
        typeCarte.setIdTypeCarte((Long) 1L);
        typeCarte.setNomTypeCarte("type carte 1");

        String typeCarteJson = "{\"idTypeCarte\":1,\"nomTypeCarte\":\"type carte 1\"}";

        when(typeCarteRepository.findById(any())).thenReturn(Optional.of(typeCarte));
        when(typeCarteRepository.saveAndFlush(any(TypeCarte.class))).thenReturn(typeCarte);

        mockMvc.perform(MockMvcRequestBuilders.put("/typecarte/modifier/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(typeCarteJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(typeCarteJson))
                .andReturn();

        verify(typeCarteRepository, times(1)).findById(any());
        verify(typeCarteRepository, times(1)).saveAndFlush(any(TypeCarte.class));

    }

    @Test
    void testModifierTypeCarteNotFound() throws Exception {

        String typeCarteJson = "{\"idTypeCarte\":1,\"nomTypeCarte\":\"type carte 1\"}";

        when(typeCarteRepository.findById(any())).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.put("/typecarte/modifier/1")
                        .content(typeCarteJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();

        verify(typeCarteRepository, times(1)).findById(any());
        verify(typeCarteRepository, never()).saveAndFlush(any(TypeCarte.class));
    }

    @Test
    void testSupprimerTypeCarteExists() throws Exception {
        TypeCarte typeCarte = new TypeCarte();
        typeCarte.setIdTypeCarte((Long) 1L);
        typeCarte.setNomTypeCarte("type carte 1");

        when(typeCarteRepository.findById(any())).thenReturn(Optional.of(typeCarte));

        mockMvc.perform(MockMvcRequestBuilders.delete("/typecarte/supprimer/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(""))
                .andReturn();

        verify(typeCarteRepository, times(1)).findById(any());
        verify(typeCarteRepository, times(1)).deleteById(any());
    }

    @Test
    void testSupprimerTypeCarteNotFound() throws Exception {
        when(typeCarteRepository.findById(any())).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.delete("/typecarte/supprimer/1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();

        verify(typeCarteRepository, times(1)).findById(any());
        verify(typeCarteRepository, never()).deleteById(any());
    }
}

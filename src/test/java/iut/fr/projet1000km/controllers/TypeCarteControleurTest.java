package iut.fr.projet1000km.controllers;

import iut.fr.projet1000km.models.TypeCarte;
import iut.fr.projet1000km.repository.TypeCarteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

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
}

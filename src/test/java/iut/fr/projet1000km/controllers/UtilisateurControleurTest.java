package iut.fr.projet1000km.controllers;

import iut.fr.projet1000km.models.Utilisateur;
import iut.fr.projet1000km.repository.UtilisateurRepository;
import iut.fr.projet1000km.services.UtilisateurService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
class UtilisateurControleurTest {

    private MockMvc mockMvc;

    @Mock
    private UtilisateurRepository utilisateurRepository;

    @Mock
    private UtilisateurService utilisateurService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(new UtilisateurControleur(utilisateurService, utilisateurRepository)).build();
    }

    @Test
    void testGetAll() throws Exception {
        Utilisateur utilisateur1 = new Utilisateur();
        utilisateur1.setIdUtilisateur(1L);
        utilisateur1.setPseudo("pseudo1");

        Utilisateur utilisateur2 = new Utilisateur();
        utilisateur2.setIdUtilisateur(2L);
        utilisateur2.setPseudo("pseudo2");

        List<Utilisateur> utilisateurs = new ArrayList<>();
        utilisateurs.add(utilisateur1);
        utilisateurs.add(utilisateur2);

        String expectedJson = "[{\"idUtilisateur\":1,\"pseudo\":\"pseudo1\",\"motDePasse\":null,\"nbPartiesJouees\":0,\"nbPartiesGagnees\":0,\"kmParcourus\":0,\"peutAvancer\":false,\"amis\":null},{\"idUtilisateur\":2,\"pseudo\":\"pseudo2\",\"motDePasse\":null,\"nbPartiesJouees\":0,\"nbPartiesGagnees\":0,\"kmParcourus\":0,\"peutAvancer\":false,\"amis\":null}]";

        when(utilisateurRepository.findAll()).thenReturn(utilisateurs);

        mockMvc.perform(MockMvcRequestBuilders.get("/utilisateur"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedJson))
                .andReturn();

        verify(utilisateurRepository, times(1)).findAll();
    }

    @Test
    void testGetByIdUtilisateurExists() throws Exception {

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setIdUtilisateur(1L);
        utilisateur.setPseudo("pseudo1");

        String expectedJson = "{\"idUtilisateur\":1,\"pseudo\":\"pseudo1\",\"motDePasse\":null,\"nbPartiesJouees\":0,\"nbPartiesGagnees\":0,\"kmParcourus\":0,\"peutAvancer\":false,\"amis\":null}";

        when(utilisateurRepository.findById(utilisateur.getIdUtilisateur())).thenReturn(Optional.of(utilisateur));

        mockMvc.perform(MockMvcRequestBuilders.get("/utilisateur/" + utilisateur.getIdUtilisateur()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedJson))
                .andReturn();

        verify(utilisateurRepository, times(1)).findById(utilisateur.getIdUtilisateur());
    }

    @Test
    void testGetByIdUtilisateurNotFound() throws Exception {
        when(utilisateurRepository.findById(any())).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/utilisateur/1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();

        verify(utilisateurRepository, times(1)).findById(any());
    }

    @Test
    void testCreer() throws Exception {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setIdUtilisateur(1L);
        utilisateur.setPseudo("pseudo1");

        String utilisateurJson = "{\"idUtilisateur\":1,\"pseudo\":\"pseudo1\",\"motDePasse\":null,\"nbPartiesJouees\":0,\"nbPartiesGagnees\":0,\"kmParcourus\":0,\"peutAvancer\":false,\"amis\":null}";

        when(utilisateurRepository.saveAndFlush(any(Utilisateur.class))).thenReturn(utilisateur);

        mockMvc.perform(MockMvcRequestBuilders.post("/utilisateur/creer" )
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(utilisateurJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(utilisateurJson))
                .andReturn();

        verify(utilisateurRepository, times(1)).saveAndFlush(any(Utilisateur.class));
    }

    @Test
    void testModifierUtilisateurExists() throws Exception {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setIdUtilisateur(1L);
        utilisateur.setPseudo("pseudo1");

        Utilisateur updatedUtilisateur = new Utilisateur();
        updatedUtilisateur.setIdUtilisateur(1L);
        updatedUtilisateur.setPseudo("pseudo 1");

        String utilisateurJson = "{\"idUtilisateur\":1,\"pseudo\":\"pseudo 1\",\"motDePasse\":null,\"nbPartiesJouees\":0,\"nbPartiesGagnees\":0,\"kmParcourus\":0,\"peutAvancer\":false,\"amis\":null}";

        when(utilisateurRepository.findById(any())).thenReturn(Optional.of(utilisateur));
        when(utilisateurRepository.saveAndFlush(utilisateur)).thenReturn(updatedUtilisateur);

        mockMvc.perform(MockMvcRequestBuilders.put("/utilisateur/modifier/1" )
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(utilisateurJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(utilisateurJson))
                .andReturn();

        verify(utilisateurRepository, times(1)).findById(any());
        verify(utilisateurRepository, times(1)).saveAndFlush(any(Utilisateur.class));
    }

    @Test
    void testModifierUtilisateurNotFound() throws Exception {

        String utilisateurJson = "{\"idUtilisateur\":1,\"pseudo\":\"pseudo1\",\"motDePasse\":null,\"nbPartiesJouees\":0,\"nbPartiesGagnees\":0,\"kmParcourus\":0,\"peutAvancer\":false,\"amis\":null}";

        when(utilisateurRepository.findById(any())).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.put("/utilisateur/modifier/1" )
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(utilisateurJson))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();

        verify(utilisateurRepository, times(1)).findById(any());
        verify(utilisateurRepository, never()).saveAndFlush(any(Utilisateur.class));
    }

    @Test
    void testSupprimerUtilisateurExists() throws Exception {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setIdUtilisateur(1L);
        utilisateur.setPseudo("pseudo1");

        when(utilisateurRepository.findById(utilisateur.getIdUtilisateur())).thenReturn(Optional.of(utilisateur));

        mockMvc.perform(MockMvcRequestBuilders.delete("/utilisateur/supprimer/" + utilisateur.getIdUtilisateur()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(""))
                .andReturn();

        verify(utilisateurRepository, times(1)).findById(utilisateur.getIdUtilisateur());
        verify(utilisateurRepository, times(1)).deleteById(utilisateur.getIdUtilisateur());
    }

    @Test
    void testSupprimerUtilisateurNotFound() throws Exception {
        when(utilisateurRepository.findById(any())).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.delete("/utilisateur/supprimer/1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();

        verify(utilisateurRepository, times(1)).findById(any());
        verify(utilisateurRepository, never()).deleteById(any());

    }
}

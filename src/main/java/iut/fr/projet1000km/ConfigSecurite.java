package iut.fr.projet1000km;

import iut.fr.projet1000km.models.Utilisateur;
import iut.fr.projet1000km.repository.UtilisateurRepository;
import iut.fr.projet1000km.services.UtilisateurService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Configuration
@EnableWebSecurity
public class ConfigSecurite {
    private final UtilisateurRepository utilisateurRepository;

    public ConfigSecurite(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        Collection<UserDetails> users = new ArrayList<>();
        List<Utilisateur> utilisateursDb = utilisateurRepository.findAll();

        for (Utilisateur utilisateur : utilisateursDb) {
            users.add(User.withDefaultPasswordEncoder()
                    .username(utilisateur.getPseudo())
                    .password(utilisateur.getMotDePasse())
                    .roles("USER")
                    .build()
            );
        }

        return new InMemoryUserDetailsManager(users);
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated()
                ).cors(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web -> web.ignoring()
                .requestMatchers("/utilisateur/connexion")
                .requestMatchers("/utilisateur/creer")
                .requestMatchers("/**")
        );
    }
}
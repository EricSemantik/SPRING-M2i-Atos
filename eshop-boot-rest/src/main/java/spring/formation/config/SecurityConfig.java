package spring.formation.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import spring.formation.config.jwt.JwtHeaderAuthorizationFilter;

@Configuration
@EnableMethodSecurity(prePostEnabled = true) // Activer les annotations PrePost pour les accès
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http, JwtHeaderAuthorizationFilter jwtFilter) throws Exception {
		// Mise en place des authorisations
		http.authorizeHttpRequests(authorize -> {
//			authorize.requestMatchers("/api/hello").permitAll(); // Autorisé à tout le monde
//			authorize.requestMatchers("/api/utilisateur/**").permitAll(); // Autorisé à tout le monde

			authorize.requestMatchers("/api/hello", "/api/utilisateur/**").permitAll(); // Autorisé à tout le monde

			// Les accès seront configurés via les annotations PrePost
			authorize.requestMatchers("/api/fournisseur/**").hasRole("ADMIN"); // Autorisé aux utilisateurs "admin"

			authorize.requestMatchers("/**").authenticated(); // Autorisé aux utilisateurs connectés
		});

		// Méthode d'authentification par formulaire HTML
//		http.formLogin(Customizer.withDefaults());

		// Méthode d'authentification par HTTP Basic
//		http.httpBasic(Customizer.withDefaults());

		// Désactiver la protection CSRF
		http.csrf(c -> c.disable());

		// Configurer les CORS (Cross-Origine Resources Sharing)
		http.cors(c -> {
			CorsConfigurationSource source = request -> {
				CorsConfiguration config = new CorsConfiguration();

				// On autorise tout le monde
				config.setAllowedOrigins(List.of("*"));

				// On autorise toutes les commandes HTTP (GET, POST, PUT, ...)
				config.setAllowedMethods(List.of("*"));

				// On autorise toutes les en-têtes HTTP
				config.setAllowedHeaders(List.of("*"));

				return config;
			};

			c.configurationSource(source);
		});

		// Positionner le filtre JWT AVANT le filter
		// UsernamePasswordAuthenticationFilter
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

		// Désactiver la session utilisateur par cookie, puisque c'est plus utilisé avec
		// JWT
		http.sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

//	// Grace à ce Bean, on pourra injecter un AuthenticationManager directement
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

//	@Bean
//	public UserDetailsService inMemory(PasswordEncoder passwordEncoder) {
//		UserBuilder users = User.builder();
//		UserDetails user = users
//			.username("user")
//			.password(passwordEncoder.encode("123456"))
//			.roles("USER")
//			.build();
//		UserDetails admin = users
//			.username("admin")
//			.password(passwordEncoder.encode("123456"))
//			.roles("USER", "ADMIN")
//			.build();
//		return new InMemoryUserDetailsManager(user, admin);
//	}
}

package com.springsecurity.securtest.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private JWTAuthenticationFilter filter;
	
	@Bean
	public SecurityFilterChain applicationsecurity(HttpSecurity http) throws Exception{
		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		http
			.cors((cors)->cors.disable())
			.csrf(csrf->csrf.disable())
			.sessionManagement(sessionManagement->sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.formLogin(formLogin->formLogin.disable())		//прибираємо форму для логіну
			.securityMatcher("/**")			//за якими посиланнями працює секюріті
			.authorizeHttpRequests(			//дозволяємо доступ всім??
					(registry) -> registry
						.requestMatchers("/").permitAll()
						.requestMatchers("/auth/login").permitAll()
						.anyRequest().authenticated()
					);
		return http.build();
		
	}
}


/*		фільтри безпеки
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(Customizer.withDefaults())		//фільтри csrf атак
            .httpBasic(Customizer.withDefaults())	// фільтри автентифікації викликаються для автентифікації запиту. 
            .formLogin(Customizer.withDefaults())
            .authorizeHttpRequests(authorize -> authorize
                .anyRequest().authenticated()
            );

        return http.build();
    }

}

*
*
*
*
*
*
*
*
*
*
*
*    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter, 
                          JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Налаштування CORS
                .csrf(csrf -> csrf.disable()) // Вимикаємо CSRF, оскільки працюємо з JWT
                .exceptionHandling(exceptionHandling -> 
                    exceptionHandling.authenticationEntryPoint(jwtAuthenticationEntryPoint)) // Обробка помилок аутентифікації
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/auth/**").permitAll() // Дозволяємо доступ до ендпоінтів авторизації
                        .requestMatchers("/api/admin/**").hasRole("ADMIN") // Тільки для ADMIN
                        .anyRequest().authenticated() // Усі інші запити потребують аутентифікації
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) // Додаємо JWT-фільтр
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Для хешування паролів
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager(); // Налаштування AuthenticationManager
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*"); // Дозволяємо усі домени (налаштуйте відповідно до потреб)
        configuration.addAllowedMethod("*"); // Дозволяємо усі HTTP-методи
        configuration.addAllowedHeader("*"); // Дозволяємо усі заголовки
        source.registerCorsConfiguration("/**", configuration);
        return new CorsFilter(source);
    }

    private UrlBasedCorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOriginPattern("*");
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        source.registerCorsConfiguration("/**", config);
        return source;
    }*/

package com.daw.cardmarket.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                                // ActorController
                                .requestMatchers("/login").permitAll()
                                .requestMatchers("/userLogin").permitAll()
                                .requestMatchers("/actor/**").authenticated()

                                // AdminController
                                .requestMatchers("/admin").hasAuthority("ADMIN")
                                .requestMatchers("/admin/editar").hasAuthority("ADMIN")
                                .requestMatchers("/admin/{id}").hasAuthority("ADMIN")

                                // CategoriaController
                                .requestMatchers(HttpMethod.GET, "/categorias").permitAll()
                                .requestMatchers(HttpMethod.POST, "/categorias").authenticated()
                                .requestMatchers(HttpMethod.GET, "/categorias/{id}").permitAll()
                                .requestMatchers(HttpMethod.DELETE, "/categorias/{id}").authenticated()
                                .requestMatchers("/categorias/{id}/editar").authenticated()
                                .requestMatchers("/categorias/nombre/{nombre}").permitAll()

                                // DireccionController
                                .requestMatchers("/direcciones").authenticated()
                                .requestMatchers("/direcciones/{id}").authenticated()
                                .requestMatchers("/direcciones/{id}/editar").authenticated()

                                // PedidoController
                                .requestMatchers("/pedidos").authenticated()
                                .requestMatchers("/pedidos/{id}").authenticated()
                                .requestMatchers("/pedidos/{id}/editar").authenticated()
                                .requestMatchers("/pedidos/usuario").authenticated()

                                // ProductoController
                                .requestMatchers(HttpMethod.GET, "/productos").permitAll()
                                .requestMatchers(HttpMethod.POST, "/productos").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/productos/{id}").permitAll()
                                .requestMatchers(HttpMethod.POST, "/productos/{id}").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/productos/{id}").hasAuthority("ADMIN")
                                .requestMatchers("/productos/{id}/editar").hasAuthority("ADMIN")
                                .requestMatchers("/productos/categoria/{idCategoria}").permitAll()

                                // UsuarioController
                                .requestMatchers(HttpMethod.POST, "/usuario").permitAll()
                                .requestMatchers(HttpMethod.GET, "/usuario").authenticated()
                                .requestMatchers("/usuario/{id}").authenticated()
                                .requestMatchers("/usuario/editar").authenticated()

                                // ValoracionController
                                .requestMatchers(HttpMethod.GET, "/productos/{idProducto}/valoraciones").permitAll()
                                .requestMatchers(HttpMethod.POST, "/productos/{idProducto}/valoraciones").authenticated()
                                .requestMatchers(HttpMethod.DELETE, "/productos/{idProducto}/valoraciones/{id}").authenticated()
                                .requestMatchers("/valoraciones/usuario").authenticated()
                                .requestMatchers("/valoraciones/{id}").permitAll()
                                .requestMatchers("/valoraciones/{id}/usuario").permitAll()

                                // TestController
                                .requestMatchers("/status").permitAll()

                                .anyRequest().authenticated()
                );

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:4200"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        config.setAllowCredentials(true);
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}

package com.miu.minionlinemarkert.config;

import com.miu.minionlinemarkert.filter.AuthorizationFilter;
import com.miu.minionlinemarkert.service.AppUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Value("#{'${allowed.origins}'.split(',')}")
    private List<String> allowedOrigins;

    @Autowired
    private AppUserService appUserService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //TODO:Narayan - Remove inmemory auth for accessing db
       auth.inMemoryAuthentication().withUser("admin").password(getPasswordEncoder().encode("admin"))
                .roles("ADMIN");

        auth.inMemoryAuthentication().withUser("user").password(getPasswordEncoder().encode("user"))
                .roles("USER");
        auth.userDetailsService(appUserService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().
                authorizeRequests().antMatchers("/auth").permitAll()
                .antMatchers("/","/h2-console/**").permitAll()
                .antMatchers("/api/public/**").permitAll()
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/webjars/**", "/swagger-resources/**").permitAll()
                .antMatchers("/v2/**").permitAll()
                .antMatchers("/api/users/**").hasAnyAuthority("ADMIN","USER")
                .antMatchers("/admin/**").hasAuthority("ADMIN").anyRequest().authenticated()
                .and().csrf().disable()
                .addFilter(new AuthorizationFilter(authenticationManager()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
      http.headers().frameOptions().disable();
      http.headers().frameOptions().sameOrigin();

    }


    @Bean
    public AuthenticationManager getAuthentication() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.applyPermitDefaultValues();
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT", "OPTIONS"));
        source.registerCorsConfiguration("/**", corsConfiguration);
        corsConfiguration.setAllowedOrigins(allowedOrigins);
        corsConfiguration.setAllowCredentials(true);
        return source;
    }

    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }


}

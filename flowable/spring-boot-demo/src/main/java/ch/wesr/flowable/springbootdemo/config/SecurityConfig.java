package ch.wesr.flowable.springbootdemo.config;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.actuate.info.InfoEndpoint;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .requestMatchers(EndpointRequest.to(InfoEndpoint.class, HealthEndpoint.class)).permitAll()
                .requestMatchers(EndpointRequest.toAnyEndpoint()).hasRole("ACTUATOR")
                .antMatchers("/*-api/**").hasRole("REST")
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER).and()
                .csrf().disable()
                .httpBasic();

        //  Refused to display 'http://localhost:8080/xxx/xxx/upload-image?CKEditor=text&CKEditorFuncNum=1&langCode=ru'
        //  in a frame because it set 'X-Frame-Options' to 'DENY'.
        //  in h2-console
        http.headers().frameOptions().sameOrigin();
    }

    private CorsConfigurationSource corsConfiguratonSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
//        corsConfiguration.setAllowedOrigins(frontendUrls);
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedMethod(HttpMethod.PUT);
        corsConfiguration.addAllowedMethod(HttpMethod.POST);
        corsConfiguration.addAllowedMethod(HttpMethod.DELETE);
        corsConfiguration.addAllowedMethod(HttpMethod.GET);
        corsConfiguration.addAllowedMethod(HttpMethod.OPTIONS);
        corsConfiguration.addAllowedMethod(HttpMethod.HEAD);
        corsConfiguration.addAllowedMethod(HttpMethod.PATCH);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }


    //@Bean
    public UserDetailsService customUserDetailsService() {
        UserDetails user1 = User.withUsername("custom-actuator")
                .password("{noop}test")
                .roles("ACTUATOR")
                .build();

        UserDetails user2 = User.withUsername("custom-rest")
                .password("{noop}test")
                .roles("REST")
                .build();
        return new InMemoryUserDetailsManager(user1, user2);
    }
}

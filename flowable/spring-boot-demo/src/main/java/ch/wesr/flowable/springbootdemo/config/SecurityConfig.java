package ch.wesr.flowable.springbootdemo.config;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.actuate.info.InfoEndpoint;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/h2-console/**").permitAll()  // h2-console permitted
        ;
        http.csrf().ignoringAntMatchers("/h2-console/**");//don't apply CSRF protection to /h2-console
        http.csrf().disable(); //don't apply CSRF protection

        //  Refused to display 'http://localhost:8080/xxx/xxx/upload-image?CKEditor=text&CKEditorFuncNum=1&langCode=ru'
        //  in a frame because it set 'X-Frame-Options' to 'DENY'.
        http.headers().frameOptions().sameOrigin();
        // keine Sessions
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // cors
        http.cors().configurationSource(corsConfiguratonSource());
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

}

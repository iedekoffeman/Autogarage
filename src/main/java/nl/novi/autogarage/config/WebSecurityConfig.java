package nl.novi.autogarage.config;

import nl.novi.autogarage.security.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

import static org.springframework.http.HttpMethod.PATCH;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


        private DataSource  dataSource;
        private JwtRequestFilter jwtRequestFilter;

        @Autowired
        WebSecurityConfig(@Lazy DataSource dataSource, @Lazy JwtRequestFilter jwtRequestFilter) {
            this.dataSource = dataSource;
            this.jwtRequestFilter = jwtRequestFilter;
        }

        @Bean
        public PasswordEncoder passwordEncoder() {

            return new BCryptPasswordEncoder();
        }

        @Autowired
        public void ConfigureGlobal(AuthenticationManagerBuilder auth) throws Exception {

            auth.jdbcAuthentication().dataSource(dataSource)
                    .usersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username=?")
                    .authoritiesByUsernameQuery("SELECT username, authority FROM authorities AS a WHERE username=?");


        }

        @Bean
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }

        @Bean
        @Override
        public UserDetailsService userDetailsServiceBean() throws Exception {
            return super.userDetailsServiceBean();
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    //HTTP Basic authentication
                    .httpBasic()
                    .and()
                    .authorizeRequests()
                     .antMatchers(PATCH,"/users/{^[\\w]$}/password").authenticated()
                    .antMatchers("/users/**").hasRole("ADMINISTRATIEFMEDEWERKER")
                    .antMatchers("/api/v1/customers/**").hasRole("ADMINISTRATIEFMEDEWERKER")
                    .antMatchers("/api/v1/cars/**").hasRole("ADMINISTRATIEFMEDEWERKER")
                    .antMatchers("/api/v1/repairs/**").hasAnyRole("MONTEUR", "ADMINISTRATIEFMEDEWERKER")
                    .antMatchers("/api/v1/inspections/**").hasAnyRole("MONTEUR", "ADMINISTRATIEFMEDEWERKER")
                    .antMatchers("/api/v1/deficiencies/**").hasAnyRole("MONTEUR")
                    .antMatchers("/api/v1/items/**").hasAnyRole("MONTEUR")
                    .and()
                    .csrf().disable()
                    .formLogin().disable()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);


            http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        }



}

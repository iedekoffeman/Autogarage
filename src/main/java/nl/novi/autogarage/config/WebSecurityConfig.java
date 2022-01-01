package nl.novi.autogarage.config;

import nl.novi.autogarage.security.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
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


        //Deze klasse is in staat om een AuthenticationManagerBuilder te gebruiken
        //In de auth kan je de manier van authentication opgeven. Je geeft hierin je users op.
        //Met de throws Exception geef je aan dat deze methode een exeception zou kunnen genereren. Je genereert de exception daar niet mee.
        //Authentication
        @Autowired
        public void ConfigureGlobal(AuthenticationManagerBuilder auth) throws Exception {

            //user + authority uit database
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

        //antMatchers kijkt naar het path en bekijkt welke users toegang hebben
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    //HTTP Basic authentication
                    .httpBasic()
                    .and()
                    .authorizeRequests()
                    .antMatchers(PATCH,"/users/{^[\\w]$}/password").authenticated()
                    .antMatchers("/users/**").hasRole("ADMIN")
                    .antMatchers("/customers/**").hasRole("USER") //Als user mag je bij customers en alles wat erachter komt
                    .antMatchers("/admin/**").hasAnyRole("USER", "ADMIN")//Als admin mag je bij admin en alles wat erachter komt als waar de user rol toegang toe heeft.
                    .antMatchers(HttpMethod.GET, "Car").authenticated() //Zodra je een geldige naam en ww hebt opgegeven onafhankelijk van welke rol mag je bij deze endpoint, maar alleen maar een get doen.
                    //.antMatchers(HttpMethod.GET, "Afspraak").permitAll()//Iedereen ongeacht of je geauthenticeerd bent mag hier de get afspraak opgeven.
                    .anyRequest().permitAll() //De rest mag iedereen zien.
                    //.anyRequest().denyAll()// De rest mag niemand zien.
                    .and()
                    .csrf().disable() //cross side forgery token, zorgt ervoor dat er niet een kwetsbaarheid gebruikt kan worden om te hacken. Vooral bij websites van belang. Disablen we daarom.
                    .formLogin().disable()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);


            http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        }



}

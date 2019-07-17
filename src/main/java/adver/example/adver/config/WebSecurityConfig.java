package adver.example.adver.config;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *04.06.2019
 */

import adver.example.adver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;
    @Bean
    public AuthenticationSuccessHandler successHandler() {
        SimpleUrlAuthenticationSuccessHandler handler = new SimpleUrlAuthenticationSuccessHandler();
        handler.setUseReferer(true);
        return handler;
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .ignoringAntMatchers("/test/category","/admin_category/category/{id}", "/admin_city/city",  "/admin_city/city/{id}" , "/admin_status/status",  "/admin_status/status/{id}", "/admin_role/role",  "/admin_role/role/{id}")
                .and()
                .authorizeRequests()
                .antMatchers("/", "/registration","/advers/**",
                       // , "/advers/lost", "/advers/lost_city", "/advers/lost_category",
                       // "/advers/found", "/advers/found_city", "/advers/found_category",
                        "/static/**", "/img/**",
                        "/advers/message", "/advers/messageUser","/test","/test{/id}").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .successHandler(successHandler())
                .permitAll()
                .and()

//                .formLogin()
////                .loginPage("/login")
////
////                .permitAll()
////                .and()

                .logout()
                .permitAll()
//                .and()
//                .csrf().disable()
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());

    }
}
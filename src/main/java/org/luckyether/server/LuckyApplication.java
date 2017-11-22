package org.luckyether.server;

import org.luckyether.server.repository.UserRepository;
import org.luckyether.server.security.PasswordEncoder;
import org.luckyether.server.security.UserDetailsImpl;
import org.luckyether.server.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.hateoas.HypermediaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Andre on July 2017.
 */
@SpringBootApplication(exclude = {HypermediaAutoConfiguration.class})
@PropertySource("classpath:lucky.properties")
@ComponentScan("org.luckyether.server")
@EntityScan(basePackages = "org.luckyether.server.model")
@EnableJpaRepositories(basePackages = "org.luckyether.server.repository")
@EnableTransactionManagement
@EnableScheduling
@EnableAsync
public class LuckyApplication extends SpringBootServletInitializer {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationProvider authProvider;

    @Autowired
    private TransactionService transactionService;

    // this is needed for war packaging of the applicaiton
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(LuckyApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(LuckyApplication.class, args);
    }

    /**
     * Create ether transaction listener.
     */
    @Autowired
    public void init(AuthenticationManagerBuilder auth, UserRepository repository) throws Exception {
        final Thread transactions = new Thread(() -> transactionService.listeningTransaction());
        transactions.setDaemon(true);
        transactions.start();

        auth.authenticationProvider(authProvider);
    }

    @Bean
    public DaoAuthenticationProvider authProvider(final UserRepository repository) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService(repository));
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    @Bean
    public UserDetailsService userDetailsService(final UserRepository repository) {
        return username -> new UserDetailsImpl(repository.findByEmail(username));
    }

    /**
     * Configure token.
     */
    @Configuration
    @EnableResourceServer
    protected static class ResourceServer extends ResourceServerConfigurerAdapter {

        @Autowired
        private TokenStore tokenStore;

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
            resources.tokenStore(tokenStore);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/signup")
                    .permitAll()
                    .antMatchers("/storage/**")
                    .permitAll()
                    .antMatchers("/health*")
                    .permitAll()
                    .and()
                    .authorizeRequests()
                    .anyRequest()
                    .authenticated();
        }
    }
}

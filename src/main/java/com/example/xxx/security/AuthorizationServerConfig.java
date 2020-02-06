package com.example.xxx.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    /**
     * 该对象用来支持password模式
     */
    @Autowired
    AuthenticationManager authenticationManager;

    /**
     * 该对象用来将令牌信息存储到内存中
     */
    @Autowired(required = false)
    TokenStore inMemoryTokenStore;

    /**
     * 该对象将为刷新token提供支持
     */
    @Autowired
    UserService userDetailsService;

    /**
     * 指定密码的加密方式
     *
     * @return
     */
    @Autowired
    PasswordEncoder passwordEncoder() {
        // 使用BCrypt强哈希函数加密方案（密钥迭代次数默认为10）
        return new BCryptPasswordEncoder();
    }

    /**
     * 配置password授权模式
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("client1")
                .authorizedGrantTypes("password", "refresh_token")
                .resourceIds("rid")
                .scopes("all")
                .secret(new BCryptPasswordEncoder().encode("123"))
                .accessTokenValiditySeconds(86400) // 有效期一天
                .refreshTokenValiditySeconds(604800); // 有效期一个星期
    }

    /**
     * 配置令牌获得与存储
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(inMemoryTokenStore)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
                // reuseRefreshTokens(false) 默认为true表示不更新refresh_token 即只有第一次有用
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 表示支持client_id 和 client_secret 做登录认证 不设置将出现登录提示
        security.allowFormAuthenticationForClients();
    }
}

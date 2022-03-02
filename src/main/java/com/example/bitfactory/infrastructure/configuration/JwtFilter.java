package com.example.bitfactory.infrastructure.configuration;

import com.example.bitfactory.infrastructure.exception.UnauthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public class JwtFilter extends GenericFilterBean {
    private final RedisDao redisDao;

    public JwtFilter(RedisDao redisDao) {
        this.redisDao = redisDao;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String jwtToken = req.getHeader("authorization");
        System.out.println(jwtToken);
        //403
        try {
            Claims claims = Jwts.parser().setSigningKey("cctv@123").parseClaimsJws(jwtToken.replace("Bearer", ""))
                    .getBody();
            String username = claims.getSubject();//获取当前登录用户名
            if (!redisDao.isExpire(username)) {
                throw new UnauthorizedException("UNAUTHORIZED!");
            }

            List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList((String) claims.get("authorities"));
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(token);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new UnauthorizedException("UNAUTHORIZED!");
        }
        filterChain.doFilter(req, servletResponse);
    }
}

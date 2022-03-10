package com.example.bitfactory.user;

import com.example.bitfactory.infrastructure.configuration.RedisDao;
import com.example.bitfactory.infrastructure.exception.BadRequestException;
import com.example.bitfactory.infrastructure.exception.NotFoundException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RedisDao redisDao;

    public UserServiceImpl(UserRepository userRepository, RedisDao redisDao) {
        this.userRepository = userRepository;
        this.redisDao = redisDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("User is not Found!"));
    }

    @Override
    public String login(String username, String password) {
        UserDetails userDetails = loadUserByUsername(username);//username/password
        if (!password.equals(userDetails.getPassword())) {
            throw new BadRequestException("密码错误！");
        }
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        StringBuffer as = new StringBuffer();
        for (GrantedAuthority authority : authorities) {
            as.append(authority.getAuthority())
                    .append(",");
        }
        return Jwts.builder()
                .claim("authorities", as)//配置用户角色
                .setSubject(userDetails.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + 10 * 60 * 1000))//过期时间
                .signWith(SignatureAlgorithm.HS512, "cctv@123")//加密算法、密钥
                .compact();

        //2、非对称加密算法
    }

    @Override
    public void logout(String authorization) {
        Claims claims = Jwts.parser().setSigningKey("cctv@123").parseClaimsJws(authorization.replace("Bearer", ""))
                .getBody();
        String username = claims.getSubject();//获取当前登录用户名
        redisDao.setKey(authorization, username);
    }
}

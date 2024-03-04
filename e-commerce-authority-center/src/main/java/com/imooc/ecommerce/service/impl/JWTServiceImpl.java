package com.imooc.ecommerce.service.impl;

import com.alibaba.fastjson.JSON;
import com.imooc.ecommerce.constant.AuthorityConstant;
import com.imooc.ecommerce.constant.CommonConstant;
import com.imooc.ecommerce.dao.EcommerceUserDao;
import com.imooc.ecommerce.entity.EcommerceUser;
import com.imooc.ecommerce.service.IJWTService;
import com.imooc.ecommerce.vo.LoginUserInfo;
import com.imooc.ecommerce.vo.UserAndPassword;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Decoder;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;


@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class JWTServiceImpl implements IJWTService {

    @Autowired
    private EcommerceUserDao ecommerceUserDao;

    public JWTServiceImpl(EcommerceUserDao ecommerceUserDao){
        this.ecommerceUserDao = ecommerceUserDao;
    }

    @Override
    public String generateToken(String username, String password) throws Exception {
        return null;
    }

    @Override
    public String generateToken(String username, String password, int expire) throws Exception {

        // 首先需要验证用户是否能够通过校验, 即输入的用户名和密码能够匹配数据表记录
        EcommerceUser ecommerceUser = ecommerceUserDao.findByUsernameAndPassword(
                username, password
        );
        if( null == ecommerceUser){
            log.error("can not find user: [{}], [{}]", username, password);
            return null;
        }

        // Token 中塞入对象， 即JWT中存储的信息， 后端拿到这些信息就可以知道是哪个用户在操作
        LoginUserInfo loginUserInfo = new LoginUserInfo(
                ecommerceUser.getId(), ecommerceUser.getUsername()
        );
        if(expire <= 0) {
            expire = AuthorityConstant.DEFAULT_EXPIRE_DAY;
        }

        // 计算超时时间
        ZonedDateTime zdt = LocalDate.now().plus(expire, ChronoUnit.DAYS)
                .atStartOfDay(ZoneId.systemDefault());
        Date expireDate = Date.from(zdt.toInstant());

        return Jwts.builder()
                .claim(CommonConstant.JWT_USER_INFO_KEY, JSON.toJSONString(loginUserInfo))
                .setId(UUID.randomUUID().toString())
                .setExpiration(expireDate)
                .signWith(getPrivateKey(), SignatureAlgorithm.RS256)
                .compact();
    }

    @Override
    public String registerUserAndGenerateToken(UserAndPassword userAndPassword) throws Exception {
        return null;
    }

    /** 根据本地存储的私钥获取到i Privatekey 对象 */
    private PrivateKey getPrivateKey() throws Exception{
        PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(new BASE64Decoder().decodeBuffer(AuthorityConstant.PRIVATE_KEY));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return  keyFactory.generatePrivate(priPKCS8);
    }
}

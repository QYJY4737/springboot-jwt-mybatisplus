package cn.congee.api.util;

import cn.congee.api.entity.UserKmmy;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

/**
 * @Author: yang
 * @Date: 2020-12-10 7:22
 */
public class TokenUtil {

    private static final long EXPIRE_TIME= 10*60*60*1000;
    //密钥盐
    private static final String TOKEN_SECRET="yovb";

    /**
     * 签名生成
     * @param user
     * @return
     */
    public static String sign(UserKmmy user){
        String token = null;
        try {
            Date expiresAt = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            token = JWT.create()
                    .withIssuer("auth0")
                    .withClaim("username", user.getUname())
                    .withExpiresAt(expiresAt)
                    // 使用了HMAC256加密算法。
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));
            //Algorithm.HMAC256():使用HS256生成token,密钥则是用户的密码，唯一密钥的话可以保存在服务端。
        } catch (Exception e){
            e.printStackTrace();
        }
        return token;
    }

    /**
     * 签名验证
     * @param token
     * @return
     */
    public static boolean verify(String token){
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("auth0").build();
            DecodedJWT jwt = verifier.verify(token);
            System.out.println("认证通过：");
            System.out.println("username: " + jwt.getClaim("username").asString());
            System.out.println("过期时间：      " + jwt.getExpiresAt());
            return true;
        } catch (Exception e){
            return false;
        }
    }


}

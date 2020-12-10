# springboot-jwt-mybatisplus
SpringBoot+JWT+MybatisPlus，使用Token登陆注册。

什么是JWT
====
Json web token (JWT), 是为了在网络应用环境间传递声明而执行的一种基于JSON的开放标准（(RFC 7519).定义了一种简洁的，自包含的方法用于通信双方之间以JSON对象的形式安全的传递信息。因为数字签名的存在，这些信息是可信的，JWT可以使用HMAC算法或者是RSA的公私秘钥对进行签名。

JWT请求流程
====
1. 用户使用账号和面发出post请求；
2. 服务器使用私钥创建一个jwt；
3. 服务器返回这个jwt给浏览器；
4. 浏览器将该jwt串在请求头中像服务器发送请求；
5. 服务器验证该jwt；
6. 返回响应的资源给浏览器。

JWT的主要应用场景
====
身份认证在这种场景下，一旦用户完成了登陆，在接下来的每个请求中包含JWT，可以用来验证用户身份以及对路由，服务和资源的访问权限进行验证。由于它的开销非常小，可以轻松的在不同域名的系统中传递，所有目前在单点登录（SSO）中比较广泛的使用了该技术。 信息交换在通信的双方之间使用JWT对数据进行编码是一种非常安全的方式，由于它的信息是经过签名的，可以确保发送者发送的信息是没有经过伪造的。

优点
====
1.简洁(Compact): 可以通过URL，POST参数或者在HTTP header发送，因为数据量小，传输速度也很快
2.自包含(Self-contained)：负载中包含了所有用户所需要的信息，避免了多次查询数据库
3.因为Token是以JSON加密的形式保存在客户端的，所以JWT是跨语言的，原则上任何web形式都支持。
4.不需要在服务端保存会话信息，特别适用于分布式微服务。

JWT的结构
====
JWT是由三段信息构成的，将这三段信息文本用.链接一起就构成了JWT字符串。
就像这样:
eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9.TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ

JWT包含了三部分：
====
Header 头部(标题包含了令牌的元数据，并且包含签名和/或加密算法的类型)
Payload 负载 (类似于飞机上承载的物品)
Signature 签名/签证

Header
====
    JWT的头部承载两部分信息：token类型和采用的加密算法。
    { 
      "alg": "HS256",
      "typ": "JWT"
    } 
声明类型:这里是jwt
声明加密的算法:通常直接使用 HMAC SHA256

    加密算法是单向函数散列算法，常见的有MD5、SHA、HAMC。
    MD5(message-digest algorithm 5) （信息-摘要算法）缩写，广泛用于加密和解密技术，常用于文件校验。校验？不管文件多大，经过MD5后都能生成唯一的MD5值
    SHA (Secure Hash Algorithm，安全散列算法），数字签名等密码学应用中重要的工具，安全性高于MD5
    HMAC (Hash Message Authentication Code)，散列消息鉴别码，基于密钥的Hash算法的认证协议。用公开函数和密钥产生一个固定长度的值作为认证标识，用这个标识鉴别消息的完整性。常用于接口签名验证

Payload
====
载荷就是存放有效信息的地方。
    有效信息包含三个部分
    1.标准中注册的声明
    2.公共的声明
    3.私有的声明

    标准中注册的声明 (建议但不强制使用) ：
    iss: jwt签发者
    sub: 面向的用户(jwt所面向的用户)
    aud: 接收jwt的一方
    exp: 过期时间戳(jwt的过期时间，这个过期时间必须要大于签发时间)
    nbf: 定义在什么时间之前，该jwt都是不可用的.
    iat: jwt的签发时间
    jti: jwt的唯一身份标识，主要用来作为一次性token,从而回避重放攻击。

公共的声明 ：
公共的声明可以添加任何的信息，一般添加用户的相关信息或其他业务需要的必要信息.但不建议添加敏感信息，因为该部分在客户端可解密.

私有的声明 ：
私有声明是提供者和消费者所共同定义的声明，一般不建议存放敏感信息，因为base64是对称解密的，意味着该部分信息可以归类为明文信息。

Signature
====
    jwt的第三部分是一个签证信息，这个签证信息由三部分组成：
    header (base64后的)
    payload (base64后的)
    secret
这个部分需要base64加密后的header和base64加密后的payload使用.连接组成的字符串，然后通过header中声明的加密方式进行加盐secret组合加密，然后就构成了jwt的第三部分。
密钥secret是保存在服务端的，服务端会根据这个密钥进行生成token和进行验证，所以需要保护好。

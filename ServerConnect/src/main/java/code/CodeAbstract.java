package code;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.Key;

public abstract class CodeAbstract {

    public static final String ALGORITHM = "PBEWITHMD5andDES";

    public static final int ITERATION_COUNT = 100;

    public static final String password = "jwqejnfiwq";

    public static final byte[] SALF = new byte[]{1, -100, 120, -10, 9, 7, 0, 10};

    /***
     * 转换密钥
     * @param password 密码
     * @return 密钥
     * @throws Exception
     */
    Key toKey(String password) throws Exception{
        //密钥材料
        PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
        //实例化
        SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);
        //生成密钥
        return factory.generateSecret(keySpec);
    }


    public byte[] initSalt() throws Exception{
//        //实例化安全随机数
//        SecureRandom random = new SecureRandom();
//        byte[] bytes = random.generateSeed(8);
        return SALF;
    }


}

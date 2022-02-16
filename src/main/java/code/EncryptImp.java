package code;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.security.Key;

public class EncryptImp extends CodeAbstract implements Encrypt {


    /***
     * 转换密钥
     * @param password 密码
     * @return 密钥
     * @throws Exception
     */
    public Key toKey(String password) throws Exception{
        //密钥材料
        PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
        //实例化
        SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);
        //生成密钥
        return factory.generateSecret(keySpec);
    }


    @Override
    public byte[] encrypt(byte[] data) throws Exception{
        return encrypt(data, password, initSalt());
    }



    /***
        * 加密
        * @param data 待加密数据
        * @param password 密钥
        * @param salt
        * @return
        * @throws Exception
        */

    private  byte[] encrypt(byte[] data, String password, byte[] salt) throws Exception{
        //转换密钥
        Key key = toKey(password);
        //实例化PBE参数材料
        PBEParameterSpec spec = new PBEParameterSpec(salt, ITERATION_COUNT);
        //实例化
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        //初始化
        cipher.init(Cipher.ENCRYPT_MODE, key, spec);
        return cipher.doFinal(data);
    }



    @Override
    public boolean isEncrypt() {
        return true;
    }
}

package code;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.security.Key;

public class DecryImp extends CodeAbstract implements Decrypt {




    @Override
    public boolean isDecry() {
        return true;
    }




    public byte[] decrypt(byte[] data) throws Exception {
        return decrypt(data, password, initSalt());
    }

    /***
     * 解密
     * @param data 待解密数据
     * @param password 密钥
     * @param salt
     * @return
     * @throws Exception
     */
    private byte[] decrypt(byte[] data, String password, byte[] salt) throws Exception{
        //转换密钥
        Key key = toKey(password);
        //实例化PBE参数材料
        PBEParameterSpec spec = new PBEParameterSpec(salt, ITERATION_COUNT);
        //实例化
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        //初始化
        cipher.init(Cipher.DECRYPT_MODE, key, spec);
        //执行操作
        return cipher.doFinal(data);
    }


}

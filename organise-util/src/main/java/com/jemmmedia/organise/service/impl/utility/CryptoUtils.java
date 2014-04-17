/**
 * 
 */
package com.jemmmedia.organise.service.impl.utility;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

/**
 * @author harjinder
 *
 */
public class CryptoUtils
{
    private static MessageDigest mda;

    static
    {
        Security.addProvider(new BouncyCastleProvider());

        try
        {
            mda = MessageDigest.getInstance("SHA-512");
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();// no dependence to log file
        }
    }

    public static final char[] FRIENDLY_CHARS = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
            'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3',
            '4', '5', '6', '7', '8', '9' };

    public static String encrypt(String s) throws NoSuchAlgorithmException, NoSuchProviderException,
            UnsupportedEncodingException
    {
        byte[] ret = s.getBytes();

        for (int i = 0; i < 20; i++)
        {
            ret = Hex.encode(mda.digest(ret));
        }

        return new String(ret);
    }

    public static String friendlyToken()
    {
        java.util.Random random = new Random();
        StringBuilder newPass = new StringBuilder();
        for (int i = 0; i < 20; i++)
        {
            newPass.append(FRIENDLY_CHARS[random.nextInt(FRIENDLY_CHARS.length)]);
        }
        return newPass.toString();

    }

    /**
     * returns a value for users table's persistence_token field
     * 
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String hexToken() throws NoSuchAlgorithmException
    {
        // Authlogic::CryptoProviders::Sha512.encrypt(Time.now.to_s + (1..10).collect{ rand.to_s }.join)
        // - Time.now.to_s #=> "2007-10-05 16:09:51 +0900"
        // - rand.to_s returns a value between 0.0 and 1.0
        // - (1..10).collect{ rand.to_s }.join returns 10 concatenated rand.to_s

        /* Time.now.to_s */
        String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z").format(new Date());

        /* (1..10).collect{ rand.to_s }.join */
        Random random = new Random();
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < 10; i++)
        {
            b.append(String.valueOf(random.nextFloat()));
        }

        /* Time.now.to_s + (1..10).collect{ rand.to_s }.join */
        String token = now + b.toString();

        /* CryptoProviders::Sha512.encrypt(Time.now.to_s + (1..10).collect{ rand.to_s }.join) */

        return new String(Hex.encode(mda.digest(token.getBytes())));

    }

    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException,
            UnsupportedEncodingException
    {
        // String data = "tlamjemm" + "ZZ77G-TKUxhTSCPMioHb";
        // String data = "testuser" + "testuserpass";
        // String data = "toto" + "toto";

        // System.out.println(encrypt(data));

        //
        for (int i = 0; i < 10; i++)
            System.out.println(friendlyToken());

        //
        // System.out.println(hexToken());

    }
}

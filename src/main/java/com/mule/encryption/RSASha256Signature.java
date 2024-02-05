package com.mule.encryption;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;

public class RSASha256Signature {
	
	
	public static void main(String[] args) throws Exception { 		
		
		RSASha256Signature sign= new RSASha256Signature();
		sign.execute("1" ,"/");
	}

    public static String execute(String message , String filePath) throws Exception {      

        // Read Public Key from File       
        String privateKeyPEM = new String(Files.readAllBytes(Paths.get(filePath + "/pk-csw-01-09-18-52.pem")));
        String lineSeparator=System.lineSeparator();
        // Format the PEM file
        privateKeyPEM = privateKeyPEM.replace("-----BEGIN PRIVATE KEY-----", "");
        privateKeyPEM = privateKeyPEM.replace("-----END PRIVATE KEY-----", "");
        privateKeyPEM = privateKeyPEM.replaceAll("\\s+", "");
        privateKeyPEM = privateKeyPEM.replaceAll(lineSeparator, "");      
        // Decode the base64-encoded private key
        
        byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyPEM.trim());

        // Create the PKCS8EncodedKeySpec with the decoded bytes
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);

        // Use KeyFactory to generate the RSA private key
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");      
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
       // PublicKey publicKey = keyFactory.generatePublic(keySpec);
        
        System.out.println("Private Key: " +privateKey);
        System.out.println(Base64.getEncoder().encodeToString(privateKey.getEncoded()));

        // Original Data
        String originalData = message;
        System.out.println("Original Data: " + originalData);
        //Sign the Data 
        byte[] signature = signMessage(originalData, privateKey);
        System.out.println("Signature: " + new String(signature));
        System.out.println("Base64EncodedSignature: " +Base64.getEncoder().encodeToString(signature));  
        
        return Base64.getEncoder().encodeToString(signature);
       //System.out.println("Verify Signature Result = " + verifySignature(originalData,signature,publicKey));
    }
    
    


  
    
    private static byte[] signMessage(String message, PrivateKey privateKey) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(message.getBytes());
        return signature.sign();
    }
    
    private static boolean verifySignature(String message, byte[] signature, PublicKey publicKey) throws Exception {
        Signature verifier = Signature.getInstance("SHA256withRSA");
        verifier.initVerify(publicKey);
        verifier.update(message.getBytes());
        return verifier.verify(signature);
    }
}

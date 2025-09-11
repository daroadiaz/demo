package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import jakarta.annotation.PostConstruct;
import java.io.File;

@Configuration
public class DatabaseConfig {
    
    static {
        String walletPath = "C:/Users/esnup/OneDrive/Documentos/cc/t1/m1/demo/src/main/resources/wallet";
        System.setProperty("oracle.net.tns_admin", walletPath);
        System.setProperty("oracle.net.wallet_location", walletPath);
        System.setProperty("javax.net.ssl.trustStore", walletPath + "/truststore.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", "Pass1163Meth");
        System.setProperty("javax.net.ssl.keyStore", walletPath + "/keystore.jks");
        System.setProperty("javax.net.ssl.keyStorePassword", "Pass1163Meth");
    }
    
    @PostConstruct
    public void init() {
        String walletPath = "C:/Users/esnup/OneDrive/Documentos/cc/t1/m1/demo/src/main/resources/wallet";
        File walletDir = new File(walletPath);
        if (walletDir.exists()) {
            System.out.println("Wallet directory found: " + walletPath);
        } else {
            System.out.println("WARNING: Wallet directory not found: " + walletPath);
        }
    }
}
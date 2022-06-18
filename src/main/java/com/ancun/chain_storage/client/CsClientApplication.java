package com.ancun.chain_storage.client;

import static org.fisco.bcos.sdk.model.CryptoType.SM_TYPE;

import com.ancun.chain_storage.client.contracts.ChainStorage;
import com.ancun.chain_storage.client.contracts.UserStorage;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;
import org.fisco.bcos.sdk.BcosSDK;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.config.Config;
import org.fisco.bcos.sdk.config.ConfigOption;
import org.fisco.bcos.sdk.crypto.CryptoSuite;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CsClientApplication implements CommandLineRunner {
  private static Logger logger = LoggerFactory.getLogger(CsClientApplication.class);

  @Value("${ConfigFilePath}")
  private String configFilePath;

  @Value("${GroupId}")
  private Integer groupId;

  @Value("${ChainStorageAddress}")
  private String chainStorageAddress;

  @Value("${UserStorageAddress}")
  private String userStorageAddress;

  @Value("${Ext}")
  private String ext;

  private UserAddFileCallback userAddFileCallback = new UserAddFileCallback();

  public static void main(String[] args) {
    SpringApplication.run(CsClientApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    ConfigOption configOption = Config.load(configFilePath, SM_TYPE);
    BcosSDK bcosSDK = new BcosSDK(configOption);
    Client client = bcosSDK.getClient(groupId);

    UserStorage userStorage =
            UserStorage.load(userStorageAddress, client, client.getCryptoSuite().getCryptoKeyPair());

    CryptoSuite cryptoSuite = client.getCryptoSuite();

    Set<String> userAddresses = new HashSet<>();
    for (int i = 0; i < 100; i++) {
      CryptoKeyPair keyPair = cryptoSuite.createKeyPair();
      ChainStorage chainStorage = ChainStorage.load(chainStorageAddress, client, keyPair);

      if (!userStorage.exist(keyPair.getAddress())) {
        chainStorage.userRegister(ext);
        userAddresses.add(keyPair.getAddress());
      }

      for (int j = 1; j <= 1000; j++) {
        String mockCid = String.format("%s:%04d", keyPair.getAddress(), j);
        chainStorage.userAddFile(mockCid, BigInteger.valueOf(3600), mockCid, userAddFileCallback);
      }
      logger.info("----i={}----", i);
    }

    logger.info(userAddresses.toString());
  }
}

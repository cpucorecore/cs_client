package com.ancun.chain_storage.client;

import static org.fisco.bcos.sdk.model.CryptoType.SM_TYPE;

import com.ancun.chain_storage.client.contracts.ChainStorage;
import com.ancun.chain_storage.client.contracts.UserStorage;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.fisco.bcos.sdk.BcosSDK;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple2;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.config.Config;
import org.fisco.bcos.sdk.config.ConfigOption;
import org.fisco.bcos.sdk.config.exceptions.ConfigException;
import org.fisco.bcos.sdk.crypto.CryptoSuite;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.transaction.model.exception.ContractException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

  private TxCallback txCallback = new TxCallback();

  public static void main(String[] args) {
    SpringApplication.run(CsClientApplication.class, args);
  }

  public void addUsersAndFiles(int userCount, int userFileCount)
      throws ContractException, ConfigException {
    ConfigOption configOption = Config.load(configFilePath, SM_TYPE);
    BcosSDK bcosSDK = new BcosSDK(configOption);
    Client client = bcosSDK.getClient(groupId);

    UserStorage userStorage =
        UserStorage.load(userStorageAddress, client, client.getCryptoSuite().getCryptoKeyPair());

    CryptoSuite cryptoSuite = client.getCryptoSuite();

    Set<String> userAddresses = new HashSet<>();
    for (int i = 1; i <= userCount; i++) {
      CryptoKeyPair keyPair = cryptoSuite.createKeyPair();
      keyPair.storeKeyPairWithPemFormat();

      ChainStorage chainStorage = ChainStorage.load(chainStorageAddress, client, keyPair);

      if (!userStorage.exist(keyPair.getAddress())) {
        chainStorage.userRegister(ext);
        userAddresses.add(keyPair.getAddress());
      }

      for (int j = 1; j <= userFileCount; j++) {
        String mockCid = String.format("%s:%06d", keyPair.getAddress(), j);
        chainStorage.userAddFile(mockCid, BigInteger.valueOf(3600), mockCid, txCallback);
      }
      logger.info(
          "user[{}/{}] {} finish add {} files", i, userCount, keyPair.getAddress(), userFileCount);
    }

    logger.info(userAddresses.toString());
  }

  public void deleteFiles() throws ConfigException, ContractException {
    ConfigOption configOption = Config.load(configFilePath, SM_TYPE);
    BcosSDK bcosSDK = new BcosSDK(configOption);
    Client client = bcosSDK.getClient(groupId);

    UserStorage userStorage =
        UserStorage.load(userStorageAddress, client, client.getCryptoSuite().getCryptoKeyPair());

    BigInteger pageSize = BigInteger.valueOf(50);
    BigInteger pageNumber = BigInteger.valueOf(1);

    Set<String> userAddresses = new HashSet<>();
    Tuple2<List<String>, Boolean> result = null;
    do {
      result = userStorage.getAllUserAddresses(pageSize, pageNumber);
      for (String userAddress : result.getValue1()) {
        userAddresses.add(userAddress);
      }
    } while (false == result.getValue2());

    Map<String, Set<String>> user2files = new HashMap<>(userAddresses.size());
    BigInteger getFilesPageNumber = BigInteger.valueOf(1);
    for (String userAddress : userAddresses) {
      Set<String> userFiles = new HashSet<>();
      do {
        result = userStorage.getFiles(userAddress, pageSize, getFilesPageNumber);
        for (String file : result.getValue1()) {
          userFiles.add(file);
        }
      } while (false == result.getValue2());

      user2files.put(userAddress, userFiles);
    }

    CryptoSuite cryptoSuite = client.getCryptoSuite();
    final CryptoKeyPair keyPair = cryptoSuite.getCryptoKeyPair();

    for (String userAddress : user2files.keySet()) {
      String keyStoreFilePath = keyPair.getPemKeyStoreFilePath(userAddress);
      cryptoSuite.loadAccount("pem", keyStoreFilePath, null);

      ChainStorage chainStorage =
          ChainStorage.load(chainStorageAddress, client, cryptoSuite.getCryptoKeyPair());

      for (String file : user2files.get(userAddress)) {
        logger.info("{} delete file {}", userAddress, file);
        chainStorage.userDeleteFile(file, txCallback);
      }
    }
  }

  @Override
  public void run(String... args) throws ContractException, ConfigException {
    int userCount = 1;
    int userFileCount = 10;

    if (0 == args.length) {
      System.out.println("Usage: bash start.sh (add/delete) [userCount] [userFileCount]");
      return;
    }

    if (args[0].equals("add")) {
      if (2 == args.length) {
        userCount = Integer.valueOf(args[1]);
      } else if (3 == args.length) {
        userCount = Integer.valueOf(args[1]);
        userFileCount = Integer.valueOf(args[2]);
      }

      addUsersAndFiles(userCount, userFileCount);
    } else if (args[0].equals("delete")) {
      deleteFiles();
    }
  }
}

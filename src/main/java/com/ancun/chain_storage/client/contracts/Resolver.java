package com.ancun.chain_storage.client.contracts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.fisco.bcos.sdk.abi.FunctionReturnDecoder;
import org.fisco.bcos.sdk.abi.TypeReference;
import org.fisco.bcos.sdk.abi.datatypes.Address;
import org.fisco.bcos.sdk.abi.datatypes.DynamicArray;
import org.fisco.bcos.sdk.abi.datatypes.Event;
import org.fisco.bcos.sdk.abi.datatypes.Function;
import org.fisco.bcos.sdk.abi.datatypes.Type;
import org.fisco.bcos.sdk.abi.datatypes.Utf8String;
import org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple1;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple2;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.contract.Contract;
import org.fisco.bcos.sdk.crypto.CryptoSuite;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.eventsub.EventCallback;
import org.fisco.bcos.sdk.model.CryptoType;
import org.fisco.bcos.sdk.model.TransactionReceipt;
import org.fisco.bcos.sdk.model.callback.TransactionCallback;
import org.fisco.bcos.sdk.transaction.model.exception.ContractException;

@SuppressWarnings("unchecked")
public class Resolver extends Contract {
  public static final String[] BINARY_ARRAY = {
    "608060405234801561001057600080fd5b5033600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555033600260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506100cb7f5265736f6c7665720000000000000000000000000000000000000000000000006100d0640100000000026401000000009004565b6101ae565b806040516020018082815260200191505060405160208183030381529060405260009080519060200190610105929190610109565b5050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061014a57805160ff1916838001178555610178565b82800160010185558215610178579182015b8281111561017757825182559160200191906001019061015c565b5b5090506101859190610189565b5090565b6101ab91905b808211156101a757600081600090555060010161018f565b5090565b90565b610e86806101bd6000396000f3fe6080604052600436106100ae576000357c0100000000000000000000000000000000000000000000000000000000900480638da5cb5b116100765780638da5cb5b1461027d5780639dddb54d146102d4578063ca446dd9146103af578063cb3617271461040a578063d0ebdbe714610445576100ae565b806313af4035146100b35780631c9fd1201461010457806321f8a7211461011b578063481c6a751461019657806375d0c0dc146101ed575b600080fd5b3480156100bf57600080fd5b50610102600480360360208110156100d657600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610496565b005b34801561011057600080fd5b50610119610603565b005b34801561012757600080fd5b506101546004803603602081101561013e57600080fd5b81019080803590602001909291905050506106ca565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156101a257600080fd5b506101ab6106dc565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156101f957600080fd5b50610202610702565b6040518080602001828103825283818151815260200191508051906020019080838360005b83811015610242578082015181840152602081019050610227565b50505050905090810190601f16801561026f5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561028957600080fd5b506102926107a0565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156102e057600080fd5b506103ad600480360360408110156102f757600080fd5b810190808035906020019064010000000081111561031457600080fd5b82018360208201111561032657600080fd5b8035906020019184602083028401116401000000008311171561034857600080fd5b90919293919293908035906020019064010000000081111561036957600080fd5b82018360208201111561037b57600080fd5b8035906020019184602083028401116401000000008311171561039d57600080fd5b90919293919293905050506107c6565b005b3480156103bb57600080fd5b50610408600480360360408110156103d257600080fd5b8101908080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505061089b565b005b34801561041657600080fd5b506104436004803603602081101561042d57600080fd5b810190808035906020019092919050505061091a565b005b34801561045157600080fd5b506104946004803603602081101561046857600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610afb565b005b61049e610603565b600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff1614151515610543576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252601d8152602001807f6e6577206f776e657220697320746865207a65726f206164647265737300000081525060200191505060405180910390fd5b8073ffffffffffffffffffffffffffffffffffffffff16600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff167fb532073b38c83145e3e5135377a08bf9aab55bc0fd7c1179cd4fb995d2a5159c60405160405180910390a380600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161415156106c8576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260178152602001807f63616c6c6572206973206e6f7420746865206f776e657200000000000000000081525060200191505060405180910390fd5b565b60006106d582610c68565b9050919050565b600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60008054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156107985780601f1061076d57610100808354040283529160200191610798565b820191906000526020600020905b81548152906001019060200180831161077b57829003601f168201915b505050505081565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b6107ce610603565b818190508484905014151561082e576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401808060200182810382526028815260200180610e336028913960400191505060405180910390fd5b60008090505b8484905081101561089457610887858583818110151561085057fe5b90506020020135848484818110151561086557fe5b9050602002013573ffffffffffffffffffffffffffffffffffffffff1661089b565b8080600101915050610834565b5050505050565b6108a3610603565b60006108ae83610c68565b90508173ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff16847f59cff797773a13d5269a5bb23cdc636f102c95002a8305e19db9a255211cf82160405160405180910390a46109158383610cd6565b505050565b600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614610a4f6040805190810160405280601481526020017f3a2063616c6c6572206973206e6f7420746865200000000000000000000000008152508360008054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610a3b5780601f10610a1057610100808354040283529160200191610a3b565b820191906000526020600020905b815481529060010190602001808311610a1e57829003601f168201915b5050505050610d5d9092919063ffffffff16565b901515610af7576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825283818151815260200191508051906020019080838360005b83811015610abc578082015181840152602081019050610aa1565b50505050905090810190601f168015610ae95780820380516001836020036101000a031916815260200191505b509250505060405180910390fd5b5050565b610b03610603565b600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff1614151515610ba8576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252601f8152602001807f6e6577206d616e6167657220697320746865207a65726f20616464726573730081525060200191505060405180910390fd5b8073ffffffffffffffffffffffffffffffffffffffff16600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff167f605c2dbf762e5f7d60a546d42e7205dcb1b011ebc62a61736a57c9089d3a435060405160405180910390a380600260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050565b6000600360007f2d000000000000000000000000000000000000000000000000000000000000008152602001908152602001600020600083815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050919050565b80600360007f2d000000000000000000000000000000000000000000000000000000000000008152602001908152602001600020600084815260200190815260200160002060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505050565b60608383836040516020018084805190602001908083835b602083101515610d9a5780518252602082019150602081019050602083039250610d75565b6001836020036101000a03801982511681845116808217855250505050505090500183805190602001908083835b602083101515610ded5780518252602082019150602081019050602083039250610dc8565b6001836020036101000a03801982511681845116808217855250505050505090500182815260200193505050506040516020818303038152906040529050939250505056fe5265736f6c7665723a206e616d652061",
    "6e642076616c7565206c656e677468206d69736d61746368a165627a7a723058204fab61cf9e2c320f3f4241a0d46660b5d3d7d85b4fc92081e5fcd63dc08e46ec0029"
  };

  public static final String BINARY =
      org.fisco.bcos.sdk.utils.StringUtils.joinAll("", BINARY_ARRAY);

  public static final String[] SM_BINARY_ARRAY = {
    "608060405234801561001057600080fd5b5033600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555033600260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506100cb7f5265736f6c7665720000000000000000000000000000000000000000000000006100d0640100000000026401000000009004565b6101ae565b806040516020018082815260200191505060405160208183030381529060405260009080519060200190610105929190610109565b5050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061014a57805160ff1916838001178555610178565b82800160010185558215610178579182015b8281111561017757825182559160200191906001019061015c565b5b5090506101859190610189565b5090565b6101ab91905b808211156101a757600081600090555060010161018f565b5090565b90565b610e86806101bd6000396000f3fe6080604052600436106100ae576000357c01000000000000000000000000000000000000000000000000000000009004806391726a991161007657806391726a99146102485780639ebd334414610299578063b1eb3c2e14610374578063c0c22a621461038b578063c229316914610406576100ae565b806304558f0b146100b357806305282c701461010e5780630ec991c21461015f5780635089e2c8146101b65780638b6ceb4f1461020d575b600080fd5b3480156100bf57600080fd5b5061010c600480360360408110156100d657600080fd5b8101908080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610496565b005b34801561011a57600080fd5b5061015d6004803603602081101561013157600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610515565b005b34801561016b57600080fd5b50610174610682565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156101c257600080fd5b506101cb6106a8565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561021957600080fd5b506102466004803603602081101561023057600080fd5b81019080803590602001909291905050506106ce565b005b34801561025457600080fd5b506102976004803603602081101561026b57600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506108af565b005b3480156102a557600080fd5b50610372600480360360408110156102bc57600080fd5b81019080803590602001906401000000008111156102d957600080fd5b8201836020820111156102eb57600080fd5b8035906020019184602083028401116401000000008311171561030d57600080fd5b90919293919293908035906020019064010000000081111561032e57600080fd5b82018360208201111561034057600080fd5b8035906020019184602083028401116401000000008311171561036257600080fd5b9091929391929390505050610a1c565b005b34801561038057600080fd5b50610389610af1565b005b34801561039757600080fd5b506103c4600480360360208110156103ae57600080fd5b8101908080359060200190929190505050610bb8565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561041257600080fd5b5061041b610bca565b6040518080602001828103825283818151815260200191508051906020019080838360005b8381101561045b578082015181840152602081019050610440565b50505050905090810190601f1680156104885780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b61049e610af1565b60006104a983610c68565b90508173ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff16847f0469af4a968d7ef71a2eb6e950cb0b28135890c3d0c93e3c12a9162175a9123760405160405180910390a46105108383610cd6565b505050565b61051d610af1565b600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff16141515156105c2576040517fc703cb1200000000000000000000000000000000000000000000000000000000815260040180806020018281038252601d8152602001807f6e6577206f776e657220697320746865207a65726f206164647265737300000081525060200191505060405180910390fd5b8073ffffffffffffffffffffffffffffffffffffffff16600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff167f7725da6dd96c2abfc02db25a96954769333dd2f6bbb9fe1b549e24da7a12ff1160405160405180910390a380600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050565b600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16146108036040805190810160405280601481526020017f3a2063616c6c6572206973206e6f7420746865200000000000000000000000008152508360008054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156107ef5780601f106107c4576101008083540402835291602001916107ef565b820191906000526020600020905b8154815290600101906020018083116107d257829003601f168201915b5050505050610d5d9092919063ffffffff16565b9015156108ab576040517fc703cb120000000000000000000000000000000000000000000000000000000081526004018080602001828103825283818151815260200191508051906020019080838360005b83811015610870578082015181840152602081019050610855565b50505050905090810190601f16801561089d5780820380516001836020036101000a031916815260200191505b509250505060405180910390fd5b5050565b6108b7610af1565b600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff161415151561095c576040517fc703cb1200000000000000000000000000000000000000000000000000000000815260040180806020018281038252601f8152602001807f6e6577206d616e6167657220697320746865207a65726f20616464726573730081525060200191505060405180910390fd5b8073ffffffffffffffffffffffffffffffffffffffff16600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff167f14397a8aac1101859dac7252e4f8469991b3a9851ccfc8b48628f729e8f8a05a60405160405180910390a380600260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050565b610a24610af1565b8181905084849050141515610a84576040517fc703cb12000000000000000000000000000000000000000000000000000000008152600401808060200182810382526028815260200180610e336028913960400191505060405180910390fd5b60008090505b84849050811015610aea57610add8585838181101515610aa657fe5b905060200201358484848181101515610abb57fe5b9050602002013573ffffffffffffffffffffffffffffffffffffffff16610496565b8080600101915050610a8a565b5050505050565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141515610bb6576040517fc703cb120000000000000000000000000000000000000000000000000000000081526004018080602001828103825260178152602001807f63616c6c6572206973206e6f7420746865206f776e657200000000000000000081525060200191505060405180910390fd5b565b6000610bc382610c68565b9050919050565b60008054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610c605780601f10610c3557610100808354040283529160200191610c60565b820191906000526020600020905b815481529060010190602001808311610c4357829003601f168201915b505050505081565b6000600360007f2d000000000000000000000000000000000000000000000000000000000000008152602001908152602001600020600083815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050919050565b80600360007f2d000000000000000000000000000000000000000000000000000000000000008152602001908152602001600020600084815260200190815260200160002060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505050565b60608383836040516020018084805190602001908083835b602083101515610d9a5780518252602082019150602081019050602083039250610d75565b6001836020036101000a03801982511681845116808217855250505050505090500183805190602001908083835b602083101515610ded5780518252602082019150602081019050602083039250610dc8565b6001836020036101000a03801982511681845116808217855250505050505090500182815260200193505050506040516020818303038152906040529050939250505056fe5265736f6c7665723a206e616d652061",
    "6e642076616c7565206c656e677468206d69736d61746368a165627a7a72305820902540c2be55a7a3f1ee5b09f6690b681ff0c783ae5741a3311d45a75544de5a0029"
  };

  public static final String SM_BINARY =
      org.fisco.bcos.sdk.utils.StringUtils.joinAll("", SM_BINARY_ARRAY);

  public static final String[] ABI_ARRAY = {
    "[{\"constant\":false,\"inputs\":[{\"name\":\"_owner\",\"type\":\"address\"}],\"name\":\"setOwner\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[],\"name\":\"mustOwner\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"name\",\"type\":\"bytes32\"}],\"name\":\"getAddress\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"manager\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"contractName\",\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"owner\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"name\",\"type\":\"bytes32[]\"},{\"name\":\"value\",\"type\":\"address[]\"}],\"name\":\"importAddress\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"name\",\"type\":\"bytes32\"},{\"name\":\"value\",\"type\":\"address\"}],\"name\":\"setAddress\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"managerName\",\"type\":\"bytes32\"}],\"name\":\"mustManager\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_manager\",\"type\":\"address\"}],\"name\":\"setManager\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"name\":\"name\",\"type\":\"bytes32\"},{\"indexed\":true,\"name\":\"previousValue\",\"type\":\"address\"},{\"indexed\":true,\"name\":\"newValue\",\"type\":\"address\"}],\"name\":\"AddressChanged\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"name\":\"previousValue\",\"type\":\"address\"},{\"indexed\":true,\"name\":\"newValue\",\"type\":\"address\"}],\"name\":\"OwnerChanged\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"name\":\"previousValue\",\"type\":\"address\"},{\"indexed\":true,\"name\":\"newValue\",\"type\":\"address\"}],\"name\":\"ManagerChanged\",\"type\":\"event\"}]"
  };

  public static final String ABI = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", ABI_ARRAY);

  public static final String FUNC_SETOWNER = "setOwner";

  public static final String FUNC_MUSTOWNER = "mustOwner";

  public static final String FUNC_GETADDRESS = "getAddress";

  public static final String FUNC_MANAGER = "manager";

  public static final String FUNC_CONTRACTNAME = "contractName";

  public static final String FUNC_OWNER = "owner";

  public static final String FUNC_IMPORTADDRESS = "importAddress";

  public static final String FUNC_SETADDRESS = "setAddress";

  public static final String FUNC_MUSTMANAGER = "mustManager";

  public static final String FUNC_SETMANAGER = "setManager";

  public static final Event ADDRESSCHANGED_EVENT =
      new Event(
          "AddressChanged",
          Arrays.<TypeReference<?>>asList(
              new TypeReference<Bytes32>(true) {},
              new TypeReference<Address>(true) {},
              new TypeReference<Address>(true) {}));
  ;

  public static final Event OWNERCHANGED_EVENT =
      new Event(
          "OwnerChanged",
          Arrays.<TypeReference<?>>asList(
              new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
  ;

  public static final Event MANAGERCHANGED_EVENT =
      new Event(
          "ManagerChanged",
          Arrays.<TypeReference<?>>asList(
              new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
  ;

  protected Resolver(String contractAddress, Client client, CryptoKeyPair credential) {
    super(getBinary(client.getCryptoSuite()), contractAddress, client, credential);
  }

  public static String getBinary(CryptoSuite cryptoSuite) {
    return (cryptoSuite.getCryptoTypeConfig() == CryptoType.ECDSA_TYPE ? BINARY : SM_BINARY);
  }

  public TransactionReceipt setOwner(String _owner) {
    final Function function =
        new Function(
            FUNC_SETOWNER,
            Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Address(_owner)),
            Collections.<TypeReference<?>>emptyList());
    return executeTransaction(function);
  }

  public byte[] setOwner(String _owner, TransactionCallback callback) {
    final Function function =
        new Function(
            FUNC_SETOWNER,
            Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Address(_owner)),
            Collections.<TypeReference<?>>emptyList());
    return asyncExecuteTransaction(function, callback);
  }

  public String getSignedTransactionForSetOwner(String _owner) {
    final Function function =
        new Function(
            FUNC_SETOWNER,
            Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Address(_owner)),
            Collections.<TypeReference<?>>emptyList());
    return createSignedTransaction(function);
  }

  public Tuple1<String> getSetOwnerInput(TransactionReceipt transactionReceipt) {
    String data = transactionReceipt.getInput().substring(10);
    final Function function =
        new Function(
            FUNC_SETOWNER,
            Arrays.<Type>asList(),
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
    List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
    return new Tuple1<String>((String) results.get(0).getValue());
  }

  public TransactionReceipt mustOwner() {
    final Function function =
        new Function(
            FUNC_MUSTOWNER, Arrays.<Type>asList(), Collections.<TypeReference<?>>emptyList());
    return executeTransaction(function);
  }

  public byte[] mustOwner(TransactionCallback callback) {
    final Function function =
        new Function(
            FUNC_MUSTOWNER, Arrays.<Type>asList(), Collections.<TypeReference<?>>emptyList());
    return asyncExecuteTransaction(function, callback);
  }

  public String getSignedTransactionForMustOwner() {
    final Function function =
        new Function(
            FUNC_MUSTOWNER, Arrays.<Type>asList(), Collections.<TypeReference<?>>emptyList());
    return createSignedTransaction(function);
  }

  public String getAddress(byte[] name) throws ContractException {
    final Function function =
        new Function(
            FUNC_GETADDRESS,
            Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(name)),
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
    return executeCallWithSingleValueReturn(function, String.class);
  }

  public String manager() throws ContractException {
    final Function function =
        new Function(
            FUNC_MANAGER,
            Arrays.<Type>asList(),
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
    return executeCallWithSingleValueReturn(function, String.class);
  }

  public String contractName() throws ContractException {
    final Function function =
        new Function(
            FUNC_CONTRACTNAME,
            Arrays.<Type>asList(),
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
    return executeCallWithSingleValueReturn(function, String.class);
  }

  public String owner() throws ContractException {
    final Function function =
        new Function(
            FUNC_OWNER,
            Arrays.<Type>asList(),
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
    return executeCallWithSingleValueReturn(function, String.class);
  }

  public TransactionReceipt importAddress(List<byte[]> name, List<String> value) {
    final Function function =
        new Function(
            FUNC_IMPORTADDRESS,
            Arrays.<Type>asList(
                name.isEmpty()
                    ? org.fisco.bcos.sdk.abi.datatypes.DynamicArray.empty("bytes32[]")
                    : new org.fisco.bcos.sdk.abi.datatypes.DynamicArray<
                        org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32>(
                        org.fisco.bcos.sdk.abi.Utils.typeMap(
                            name, org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32.class)),
                value.isEmpty()
                    ? org.fisco.bcos.sdk.abi.datatypes.DynamicArray.empty("address[]")
                    : new org.fisco.bcos.sdk.abi.datatypes.DynamicArray<
                        org.fisco.bcos.sdk.abi.datatypes.Address>(
                        org.fisco.bcos.sdk.abi.Utils.typeMap(
                            value, org.fisco.bcos.sdk.abi.datatypes.Address.class))),
            Collections.<TypeReference<?>>emptyList());
    return executeTransaction(function);
  }

  public byte[] importAddress(List<byte[]> name, List<String> value, TransactionCallback callback) {
    final Function function =
        new Function(
            FUNC_IMPORTADDRESS,
            Arrays.<Type>asList(
                name.isEmpty()
                    ? org.fisco.bcos.sdk.abi.datatypes.DynamicArray.empty("bytes32[]")
                    : new org.fisco.bcos.sdk.abi.datatypes.DynamicArray<
                        org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32>(
                        org.fisco.bcos.sdk.abi.Utils.typeMap(
                            name, org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32.class)),
                value.isEmpty()
                    ? org.fisco.bcos.sdk.abi.datatypes.DynamicArray.empty("address[]")
                    : new org.fisco.bcos.sdk.abi.datatypes.DynamicArray<
                        org.fisco.bcos.sdk.abi.datatypes.Address>(
                        org.fisco.bcos.sdk.abi.Utils.typeMap(
                            value, org.fisco.bcos.sdk.abi.datatypes.Address.class))),
            Collections.<TypeReference<?>>emptyList());
    return asyncExecuteTransaction(function, callback);
  }

  public String getSignedTransactionForImportAddress(List<byte[]> name, List<String> value) {
    final Function function =
        new Function(
            FUNC_IMPORTADDRESS,
            Arrays.<Type>asList(
                name.isEmpty()
                    ? org.fisco.bcos.sdk.abi.datatypes.DynamicArray.empty("bytes32[]")
                    : new org.fisco.bcos.sdk.abi.datatypes.DynamicArray<
                        org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32>(
                        org.fisco.bcos.sdk.abi.Utils.typeMap(
                            name, org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32.class)),
                value.isEmpty()
                    ? org.fisco.bcos.sdk.abi.datatypes.DynamicArray.empty("address[]")
                    : new org.fisco.bcos.sdk.abi.datatypes.DynamicArray<
                        org.fisco.bcos.sdk.abi.datatypes.Address>(
                        org.fisco.bcos.sdk.abi.Utils.typeMap(
                            value, org.fisco.bcos.sdk.abi.datatypes.Address.class))),
            Collections.<TypeReference<?>>emptyList());
    return createSignedTransaction(function);
  }

  public Tuple2<List<byte[]>, List<String>> getImportAddressInput(
      TransactionReceipt transactionReceipt) {
    String data = transactionReceipt.getInput().substring(10);
    final Function function =
        new Function(
            FUNC_IMPORTADDRESS,
            Arrays.<Type>asList(),
            Arrays.<TypeReference<?>>asList(
                new TypeReference<DynamicArray<Bytes32>>() {},
                new TypeReference<DynamicArray<Address>>() {}));
    List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
    return new Tuple2<List<byte[]>, List<String>>(
        convertToNative((List<Bytes32>) results.get(0).getValue()),
        convertToNative((List<Address>) results.get(1).getValue()));
  }

  public TransactionReceipt setAddress(byte[] name, String value) {
    final Function function =
        new Function(
            FUNC_SETADDRESS,
            Arrays.<Type>asList(
                new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(name),
                new org.fisco.bcos.sdk.abi.datatypes.Address(value)),
            Collections.<TypeReference<?>>emptyList());
    return executeTransaction(function);
  }

  public byte[] setAddress(byte[] name, String value, TransactionCallback callback) {
    final Function function =
        new Function(
            FUNC_SETADDRESS,
            Arrays.<Type>asList(
                new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(name),
                new org.fisco.bcos.sdk.abi.datatypes.Address(value)),
            Collections.<TypeReference<?>>emptyList());
    return asyncExecuteTransaction(function, callback);
  }

  public String getSignedTransactionForSetAddress(byte[] name, String value) {
    final Function function =
        new Function(
            FUNC_SETADDRESS,
            Arrays.<Type>asList(
                new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(name),
                new org.fisco.bcos.sdk.abi.datatypes.Address(value)),
            Collections.<TypeReference<?>>emptyList());
    return createSignedTransaction(function);
  }

  public Tuple2<byte[], String> getSetAddressInput(TransactionReceipt transactionReceipt) {
    String data = transactionReceipt.getInput().substring(10);
    final Function function =
        new Function(
            FUNC_SETADDRESS,
            Arrays.<Type>asList(),
            Arrays.<TypeReference<?>>asList(
                new TypeReference<Bytes32>() {}, new TypeReference<Address>() {}));
    List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
    return new Tuple2<byte[], String>(
        (byte[]) results.get(0).getValue(), (String) results.get(1).getValue());
  }

  public TransactionReceipt mustManager(byte[] managerName) {
    final Function function =
        new Function(
            FUNC_MUSTMANAGER,
            Arrays.<Type>asList(
                new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(managerName)),
            Collections.<TypeReference<?>>emptyList());
    return executeTransaction(function);
  }

  public byte[] mustManager(byte[] managerName, TransactionCallback callback) {
    final Function function =
        new Function(
            FUNC_MUSTMANAGER,
            Arrays.<Type>asList(
                new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(managerName)),
            Collections.<TypeReference<?>>emptyList());
    return asyncExecuteTransaction(function, callback);
  }

  public String getSignedTransactionForMustManager(byte[] managerName) {
    final Function function =
        new Function(
            FUNC_MUSTMANAGER,
            Arrays.<Type>asList(
                new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(managerName)),
            Collections.<TypeReference<?>>emptyList());
    return createSignedTransaction(function);
  }

  public Tuple1<byte[]> getMustManagerInput(TransactionReceipt transactionReceipt) {
    String data = transactionReceipt.getInput().substring(10);
    final Function function =
        new Function(
            FUNC_MUSTMANAGER,
            Arrays.<Type>asList(),
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
    List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
    return new Tuple1<byte[]>((byte[]) results.get(0).getValue());
  }

  public TransactionReceipt setManager(String _manager) {
    final Function function =
        new Function(
            FUNC_SETMANAGER,
            Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Address(_manager)),
            Collections.<TypeReference<?>>emptyList());
    return executeTransaction(function);
  }

  public byte[] setManager(String _manager, TransactionCallback callback) {
    final Function function =
        new Function(
            FUNC_SETMANAGER,
            Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Address(_manager)),
            Collections.<TypeReference<?>>emptyList());
    return asyncExecuteTransaction(function, callback);
  }

  public String getSignedTransactionForSetManager(String _manager) {
    final Function function =
        new Function(
            FUNC_SETMANAGER,
            Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Address(_manager)),
            Collections.<TypeReference<?>>emptyList());
    return createSignedTransaction(function);
  }

  public Tuple1<String> getSetManagerInput(TransactionReceipt transactionReceipt) {
    String data = transactionReceipt.getInput().substring(10);
    final Function function =
        new Function(
            FUNC_SETMANAGER,
            Arrays.<Type>asList(),
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
    List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
    return new Tuple1<String>((String) results.get(0).getValue());
  }

  public List<AddressChangedEventResponse> getAddressChangedEvents(
      TransactionReceipt transactionReceipt) {
    List<Contract.EventValuesWithLog> valueList =
        extractEventParametersWithLog(ADDRESSCHANGED_EVENT, transactionReceipt);
    ArrayList<AddressChangedEventResponse> responses =
        new ArrayList<AddressChangedEventResponse>(valueList.size());
    for (Contract.EventValuesWithLog eventValues : valueList) {
      AddressChangedEventResponse typedResponse = new AddressChangedEventResponse();
      typedResponse.log = eventValues.getLog();
      typedResponse.name = (byte[]) eventValues.getIndexedValues().get(0).getValue();
      typedResponse.previousValue = (String) eventValues.getIndexedValues().get(1).getValue();
      typedResponse.newValue = (String) eventValues.getIndexedValues().get(2).getValue();
      responses.add(typedResponse);
    }
    return responses;
  }

  public void subscribeAddressChangedEvent(
      String fromBlock, String toBlock, List<String> otherTopics, EventCallback callback) {
    String topic0 = eventEncoder.encode(ADDRESSCHANGED_EVENT);
    subscribeEvent(ABI, BINARY, topic0, fromBlock, toBlock, otherTopics, callback);
  }

  public void subscribeAddressChangedEvent(EventCallback callback) {
    String topic0 = eventEncoder.encode(ADDRESSCHANGED_EVENT);
    subscribeEvent(ABI, BINARY, topic0, callback);
  }

  public List<OwnerChangedEventResponse> getOwnerChangedEvents(
      TransactionReceipt transactionReceipt) {
    List<Contract.EventValuesWithLog> valueList =
        extractEventParametersWithLog(OWNERCHANGED_EVENT, transactionReceipt);
    ArrayList<OwnerChangedEventResponse> responses =
        new ArrayList<OwnerChangedEventResponse>(valueList.size());
    for (Contract.EventValuesWithLog eventValues : valueList) {
      OwnerChangedEventResponse typedResponse = new OwnerChangedEventResponse();
      typedResponse.log = eventValues.getLog();
      typedResponse.previousValue = (String) eventValues.getIndexedValues().get(0).getValue();
      typedResponse.newValue = (String) eventValues.getIndexedValues().get(1).getValue();
      responses.add(typedResponse);
    }
    return responses;
  }

  public void subscribeOwnerChangedEvent(
      String fromBlock, String toBlock, List<String> otherTopics, EventCallback callback) {
    String topic0 = eventEncoder.encode(OWNERCHANGED_EVENT);
    subscribeEvent(ABI, BINARY, topic0, fromBlock, toBlock, otherTopics, callback);
  }

  public void subscribeOwnerChangedEvent(EventCallback callback) {
    String topic0 = eventEncoder.encode(OWNERCHANGED_EVENT);
    subscribeEvent(ABI, BINARY, topic0, callback);
  }

  public List<ManagerChangedEventResponse> getManagerChangedEvents(
      TransactionReceipt transactionReceipt) {
    List<Contract.EventValuesWithLog> valueList =
        extractEventParametersWithLog(MANAGERCHANGED_EVENT, transactionReceipt);
    ArrayList<ManagerChangedEventResponse> responses =
        new ArrayList<ManagerChangedEventResponse>(valueList.size());
    for (Contract.EventValuesWithLog eventValues : valueList) {
      ManagerChangedEventResponse typedResponse = new ManagerChangedEventResponse();
      typedResponse.log = eventValues.getLog();
      typedResponse.previousValue = (String) eventValues.getIndexedValues().get(0).getValue();
      typedResponse.newValue = (String) eventValues.getIndexedValues().get(1).getValue();
      responses.add(typedResponse);
    }
    return responses;
  }

  public void subscribeManagerChangedEvent(
      String fromBlock, String toBlock, List<String> otherTopics, EventCallback callback) {
    String topic0 = eventEncoder.encode(MANAGERCHANGED_EVENT);
    subscribeEvent(ABI, BINARY, topic0, fromBlock, toBlock, otherTopics, callback);
  }

  public void subscribeManagerChangedEvent(EventCallback callback) {
    String topic0 = eventEncoder.encode(MANAGERCHANGED_EVENT);
    subscribeEvent(ABI, BINARY, topic0, callback);
  }

  public static Resolver load(String contractAddress, Client client, CryptoKeyPair credential) {
    return new Resolver(contractAddress, client, credential);
  }

  public static Resolver deploy(Client client, CryptoKeyPair credential) throws ContractException {
    return deploy(Resolver.class, client, credential, getBinary(client.getCryptoSuite()), "");
  }

  public static class AddressChangedEventResponse {
    public TransactionReceipt.Logs log;

    public byte[] name;

    public String previousValue;

    public String newValue;
  }

  public static class OwnerChangedEventResponse {
    public TransactionReceipt.Logs log;

    public String previousValue;

    public String newValue;
  }

  public static class ManagerChangedEventResponse {
    public TransactionReceipt.Logs log;

    public String previousValue;

    public String newValue;
  }
}

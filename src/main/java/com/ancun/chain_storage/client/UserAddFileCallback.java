package com.ancun.chain_storage.client;

import org.fisco.bcos.sdk.model.TransactionReceipt;
import org.fisco.bcos.sdk.model.callback.TransactionCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserAddFileCallback extends TransactionCallback {
  private static Logger logger = LoggerFactory.getLogger(UserAddFileCallback.class);

  @Override
  public void onResponse(TransactionReceipt receipt) {
    if (!receipt.isStatusOK()) {
      logger.error("err={}", receipt.getMessage());
    }
  }
}

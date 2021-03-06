/*
Copyright IBM Corp. All Rights Reserved.

SPDX-License-Identifier: Apache-2.0
*/

package org.hyperledger.fabric.shim.impl;

import org.hyperledger.fabric.protos.peer.Chaincode.ChaincodeID;
import org.hyperledger.fabric.protos.peer.ChaincodeEventPackage.ChaincodeEvent;
import org.hyperledger.fabric.protos.peer.ChaincodeShim.ChaincodeMessage;
import org.hyperledger.fabric.protos.peer.ChaincodeShim.ChaincodeMessage.Type;
import org.hyperledger.fabric.shim.Chaincode.Response;
import org.hyperledger.fabric.shim.ResponseUtils;
import org.junit.jupiter.api.Test;

import com.google.protobuf.ByteString;

class ChaincodeMessageFactoryTest {

	private String txId = "txid";
	private String key = "key";
	private String channelId = "channelid";
	private String collection = "collectionId";
	private ByteString value  = ByteString.copyFromUtf8("Hello");;
	private String metakey = "metakey";
	private Throwable throwable = new Throwable();
	private String message ="message";
	private ChaincodeEvent event;
	private Response response = ResponseUtils.newSuccessResponse();;
	private ByteString payload = ByteString.copyFromUtf8("Hello");
	private ChaincodeID chaincodeId =ChaincodeID.newBuilder().setName("test").build();
	private Type type = ChaincodeMessage.Type.COMPLETED;

	@Test
	void testNewGetPrivateDataHashEventMessage() {
		ChaincodeMessageFactory.newGetPrivateDataHashEventMessage(channelId, txId, collection, key);
	}

	@Test
	void testNewGetStateEventMessage() {
		ChaincodeMessageFactory.newGetStateEventMessage(channelId, txId, collection, key);
	}

	@Test
	void testNewGetStateMetadataEventMessage() {
		ChaincodeMessageFactory.newGetStateMetadataEventMessage(channelId, txId, collection, key);
	}

	@Test
	void testNewPutStateEventMessage() {
		ChaincodeMessageFactory.newPutStateEventMessage(channelId, txId, collection, key, value);
	}

	@Test
	void testNewPutStateMatadateEventMessage() {
		ChaincodeMessageFactory.newPutStateMatadateEventMessage(channelId, txId, collection, key, metakey, value);
	}

	@Test
	void testNewDeleteStateEventMessage() {
		ChaincodeMessageFactory.newDeleteStateEventMessage(channelId, txId, collection, key);
	}

	@Test
	void testNewErrorEventMessage() {
		ChaincodeMessageFactory.newErrorEventMessage(channelId, txId, message);
		ChaincodeMessageFactory.newErrorEventMessage(channelId, txId, throwable);
		ChaincodeMessageFactory.newErrorEventMessage(channelId, txId, message, event);
	}

	@Test
	void testNewCompletedEventMessage() {

		ChaincodeMessageFactory.newCompletedEventMessage(channelId, txId, response, event);
	}

	@Test
	void testNewInvokeChaincodeMessage() {
		ChaincodeMessageFactory.newInvokeChaincodeMessage(channelId, txId, payload);
	}

	@Test
	void testNewRegisterChaincodeMessage() {
		ChaincodeMessageFactory.newRegisterChaincodeMessage(chaincodeId);
	}

	@Test
	void testNewEventMessageTypeStringStringByteString() {
		ChaincodeMessageFactory.newEventMessage(type, channelId, txId, payload);
		ChaincodeMessageFactory.newEventMessage(type, channelId, txId, payload, event);
	}

}

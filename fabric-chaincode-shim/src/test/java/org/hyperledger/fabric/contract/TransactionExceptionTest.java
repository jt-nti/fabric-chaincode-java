/*
Copyright IBM Corp. All Rights Reserved.

SPDX-License-Identifier: Apache-2.0
*/
package org.hyperledger.fabric.contract;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TransactionExceptionTest {

    class MyTransactionException extends TransactionException {

        private static final long serialVersionUID = 1L;

        private int errorCode;

        public MyTransactionException(int errorCode) {
            super("MyTransactionException");
            this.errorCode = errorCode;
        }

        @Override
        public byte[] getPayload() {
            String payload = String.format("E%03d", errorCode);
            return payload.getBytes();
        }
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testNoArgConstructor() {
        TransactionException e = new TransactionException();
        assertThat(e.getMessage(), is(nullValue()));
        assertThat(e.getPayload(), is(nullValue()));
    }

    @Test
    public void testMessageArgConstructor() {
        TransactionException e = new TransactionException("Failure");
        assertThat(e.getMessage(), is("Failure"));
        assertThat(e.getPayload(), is(nullValue()));
    }

    @Test
    public void testCauseArgConstructor() {
        TransactionException e = new TransactionException(new Error("Cause"));
        assertThat(e.getMessage(), is("java.lang.Error: Cause"));
        assertThat(e.getPayload(), is(nullValue()));
        assertThat(e.getCause().getMessage(), is("Cause"));
    }

    @Test
    public void testMessageAndCauseArgConstructor() {
        TransactionException e = new TransactionException("Failure", new Error("Cause"));
        assertThat(e.getMessage(), is("Failure"));
        assertThat(e.getPayload(), is(nullValue()));
        assertThat(e.getCause().getMessage(), is("Cause"));
    }

    @Test
    public void testMessageAndPayloadArgConstructor() {
        TransactionException e = new TransactionException("Failure", new byte[] { 'P', 'a', 'y', 'l', 'o', 'a', 'd' });
        assertThat(e.getMessage(), is("Failure"));
        assertThat(e.getPayload(), is(new byte[] { 'P', 'a', 'y', 'l', 'o', 'a', 'd' }));
    }

    @Test
    public void testMessagePayloadAndCauseArgConstructor() {
        TransactionException e = new TransactionException("Failure", new byte[] { 'P', 'a', 'y', 'l', 'o', 'a', 'd' }, new Error("Cause"));
        assertThat(e.getMessage(), is("Failure"));
        assertThat(e.getPayload(), is(new byte[] { 'P', 'a', 'y', 'l', 'o', 'a', 'd' }));
        assertThat(e.getCause().getMessage(), is("Cause"));
    }

    @Test
    public void testMessageAndStringPayloadArgConstructor() {
        TransactionException e = new TransactionException("Failure", "Payload");
        assertThat(e.getMessage(), is("Failure"));
        assertThat(e.getPayload(), is(new byte[] { 'P', 'a', 'y', 'l', 'o', 'a', 'd' }));
    }

    @Test
    public void testMessageStringPayloadAndCauseArgConstructor() {
        TransactionException e = new TransactionException("Failure", "Payload", new Error("Cause"));
        assertThat(e.getMessage(), is("Failure"));
        assertThat(e.getPayload(), is(new byte[] { 'P', 'a', 'y', 'l', 'o', 'a', 'd' }));
        assertThat(e.getCause().getMessage(), is("Cause"));
    }

    @Test
    public void testSubclass() {
        TransactionException e = new MyTransactionException(1);
        assertThat(e.getMessage(), is("MyTransactionException"));
        assertThat(e.getPayload(), is(new byte[] { 'E', '0', '0', '1' }));
    }
}

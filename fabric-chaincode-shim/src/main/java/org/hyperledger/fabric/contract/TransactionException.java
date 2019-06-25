/*
Copyright IBM Corp. All Rights Reserved.

SPDX-License-Identifier: Apache-2.0
*/
package org.hyperledger.fabric.contract;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Contracts should use {@code TransactionException} to indicate when an error
 * occurs in Smart Contract logic.
 *
 * <p>
 * When a {@code TransactionException} is thrown an error response will be
 * returned from the chaincode container containing the exception message and
 * payload, if specified.
 *
 * <p>
 * {@code TransactionException} may be extended to provide application specific
 * error information. Subclasses should ensure that {@link #getPayload} returns
 * a serialized representation of the error in a suitable format for client
 * applications to process.
 */
public class TransactionException extends RuntimeException {

    private static final long serialVersionUID = -584018941365674646L;

    private byte[] payload;

    /**
     * Constructs a new {@code TransactionException} with no detail message.
     */
    public TransactionException() {
        super();
    }

    /**
     * Constructs a new {@code TransactionException} with the specified detail
     * message.
     *
     * @param message the detail message.
     */
    public TransactionException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code TransactionException} with the specified cause.
     *
     * @param cause the cause.
     */
    public TransactionException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new {@code TransactionException} with the specified detail
     * message and cause.
     *
     * @param message the detail message.
     * @param cause   the cause.
     */
    public TransactionException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new {@code TransactionException} with the specified detail
     * message and response payload.
     *
     * @param message the detail message.
     * @param payload the response payload.
     */
    public TransactionException(String message, byte[] payload) {
        super(message);

        this.payload = payload;
    }

    /**
     * Constructs a new {@code TransactionException} with the specified detail
     * message, response payload and cause.
     *
     * @param message the detail message.
     * @param payload the response payload.
     * @param cause   the cause.
     */
    public TransactionException(String message, byte[] payload, Throwable cause) {
        super(message, cause);

        this.payload = payload;
    }

    /**
     * Constructs a new {@code TransactionException} with the specified detail
     * message and response payload.
     *
     * @param message the detail message.
     * @param payload the response payload.
     */
    public TransactionException(String message, String payload) {
        super(message);

        this.payload = payload.getBytes(UTF_8);
    }

    /**
     * Constructs a new {@code TransactionException} with the specified detail
     * message, response payload and cause.
     *
     * @param message the detail message.
     * @param payload the response payload.
     * @param cause   the cause.
     */
    public TransactionException(String message, String payload, Throwable cause) {
        super(message, cause);

        this.payload = payload.getBytes(UTF_8);
    }

    /**
     * Returns the response payload or {@code null} if there is no response.
     *
     * <p>
     * The payload should represent the transaction error in a way that client
     * applications written in different programming languages can interpret. For
     * example it could include a domain specific error code, in addition to any
     * state information which would allow client applications to respond
     * appropriately.
     *
     * @return the response payload or {@code null} if there is no response.
     */
    public byte[] getPayload() {
        return payload;
    }
}

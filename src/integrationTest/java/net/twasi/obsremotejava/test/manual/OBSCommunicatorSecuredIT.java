package net.twasi.obsremotejava.test.manual;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;
import net.twasi.obsremotejava.OBSCommunicator;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Read comment instructions before each test
 */
class OBSCommunicatorSecuredIT {

    /**
     * - Set these two values before running these tests
     * - Make sure your OBS is running and available for connection
     */
    private final String obsAddress = "ws://localhost:4444";
    private final String obsPassword = "password";

    /**
     * Before running this test:
     * - Start OBS locally
     * - Enable websocket authentication
     * - Run test
     */
    @Test
    void testConnectToSecuredServerWithoutPasswordInvokesConnectionFailedCallback() throws Exception {
        AtomicReference<Integer> closeCode = new AtomicReference<>();
        AtomicReference<String> closeReason = new AtomicReference<>();

        // Given we have ws client and communicator with no password
        WebSocketClient client = new WebSocketClient();
        OBSCommunicator communicator = OBSCommunicator.builder()
          .password(null)
          .build();

        // Given we register a callback on error
        communicator.registerOnClose((code, reason) -> {
            closeCode.set(code);
            closeReason.set(reason);
        });

        // When we connect
        connectToObs(client, communicator, obsAddress);

        // Then we expect an error
        // Connection closed: 4006 - Your `Identify` payload is missing an `authentication` string, however authentication is required.
        assertThat(closeCode.get()).isEqualTo(4006);
        assertThat(closeReason.get()).containsIgnoringCase("authentication is required");
    }

    /**
     * Before running this test:
     * - Start OBS locally
     * - Enable websocket authentication
     * - Run test
     */
    @Test
    void testConnectToSecuredServerWithInCorrectPassword() throws Exception {

        AtomicReference<Integer> closeCode = new AtomicReference<>();
        AtomicReference<String> closeReason = new AtomicReference<>();

        // Given we have ws client and communicator with a bad password
        String websocketPassword = obsPassword + "gibberish";

        WebSocketClient client = new WebSocketClient();
        OBSCommunicator communicator = OBSCommunicator.builder()
          .password(websocketPassword)
          .build();

        // Given we register a callback on error
        communicator.registerOnClose((code, reason) -> {
            closeCode.set(code);
            closeReason.set(reason);
        });

        // When we connect
        connectToObs(client, communicator, obsAddress);

        // Then we expect an error
        // Connection closed: 4005 - Authentication failed.
        assertThat(closeCode.get()).isEqualTo(4005);
        assertThat(closeReason.get()).containsIgnoringCase("Authentication failed");

    }

    /**
     * Before running this test:
     * - Start OBS locally
     * - Enable websocket authentication
     * - Set password to 'password'
     * - Run test
     */
    @Test
    void testConnectToSecuredServerWithCorrectPassword() {

        AtomicReference<String> testFailedReason = new AtomicReference<>();
        AtomicReference<Boolean> connectorIdentified = new AtomicReference<>(false);

        // Given we have a websocket client and annotated websocket communicator
        WebSocketClient client = new WebSocketClient();
        OBSCommunicator communicator = OBSCommunicator.builder()
          .password(obsPassword)
          .build();

        // And given we have registered callbacks to disconnect once connected & identified
        communicator.registerOnIdentified(identified -> {
            System.out.println("(Test) Authenticated successfully");
            connectorIdentified.set(true);
            closeConnectionAndStopClient(client, communicator);
        });
        communicator.registerOnError((message, throwable) -> {
            testFailedReason.set("(Test) Connection failed:" + message);
            closeConnectionAndStopClient(client, communicator);
        });

        // When we connect to OBS
        connectToObs(client, communicator, obsAddress);

        // Then there should be no errors
        if (testFailedReason.get() != null) {
            fail(testFailedReason.get());
        }
        // And the client should have been identified
        if (!connectorIdentified.get()) {
            fail("Did not successfully identify the communicator");
        }
    }

    private void closeConnectionAndStopClient(WebSocketClient client, OBSCommunicator connector) {
        // wait for closed socket connection
        try {
            System.out.println("Closing connection");
            connector.awaitClose(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (!client.isStopped() && !client.isStopping()) {
            try {
                System.out.println("Stopping client");
                client.stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void connectToObs(WebSocketClient client, OBSCommunicator communicator, String obsAddress) {
        try {
            client.start();
            URI echoUri = new URI(obsAddress);
            ClientUpgradeRequest request = new ClientUpgradeRequest();
            Future<Session> connection = client.connect(communicator, echoUri, request);
            System.out.printf("Connecting to : %s%n", echoUri);
            System.out.println("Connected at " + connection.get().getRemoteAddress());
            communicator.await();
        } catch (Exception e) {
            fail("Could not connect to OBS", e);
        } finally {
            closeConnectionAndStopClient(client, communicator);
        }
    }

}

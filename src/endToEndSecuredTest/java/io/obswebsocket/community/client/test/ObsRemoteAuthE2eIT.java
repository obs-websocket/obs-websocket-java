package io.obswebsocket.community.client.test;

import io.obswebsocket.community.client.OBSCommunicator;
import io.obswebsocket.community.client.OBSRemoteController;
import io.obswebsocket.community.client.listener.lifecycle.communicator.CommunicatorLifecycleListener.CodeReason;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicReference;
import org.junit.jupiter.api.Timeout;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Read comment instructions before each test
 */
@Timeout(5)
class ObsRemoteAuthE2eIT {

    private final String PASSWORD = "password";

    /**
     * Before running this test:
     * - Start OBS locally
     * - Enable websocket authentication
     * - Run test
     */
    @Test
    void testConnectToSecuredServerWithoutPasswordInvokesConnectionFailedCallback() throws Exception {
        AtomicReference<String> failReason = new AtomicReference<>();
        AtomicReference<CodeReason> closeCodeReason = new AtomicReference<>();

        // Given we have ws client and communicator with no password
        OBSCommunicator communicator = OBSCommunicator.builder()
          .password(null)
          // Given we register a callback on close
          .lifecycle()
            .onClose((comm, codeReason) -> {
                closeCodeReason.set(codeReason);
            })
            .onHello((comm, hello) -> {
                if(hello.getAuthentication() == null) {
                    failReason.set("Authentication wasn't enabled");
                }
            })
            .and()
          .build();
        OBSRemoteController remoteController = OBSRemoteController.builder()
          .communicator(communicator)
          .build();

        // When we connect
        remoteController.connect();
        Thread.sleep(1000);

        // Then authentication was enabled
        if(failReason.get() != null) {
            fail(failReason.get());
        }

        // Then we expect an error
        // Connection closed: 4006 - Your `Identify` payload is missing an `authentication` string, however authentication is required.
        assertThat(closeCodeReason.get().getCode()).isEqualTo(4006);
        assertThat(closeCodeReason.get().getReason()).containsIgnoringCase("authentication is required");
    }

    /**
     * Before running this test:
     * - Start OBS locally
     * - Enable websocket authentication
     * - Run test
     */
    @Test
    void testConnectToSecuredServerWithInCorrectPassword() throws Exception {
        AtomicReference<String> failReason = new AtomicReference<>();
        AtomicReference<CodeReason> closeCodeReason = new AtomicReference<>();

        // Given we have ws client and communicator with a bad password
        OBSCommunicator communicator = OBSCommunicator.builder()
          .password(PASSWORD + "gibberish")
          // Given we register a callback on error
          .lifecycle()
            .onClose((comm, codeReason) -> {
                closeCodeReason.set(codeReason);
            })
            .onHello((comm, hello) -> {
                if(hello.getAuthentication() == null) {
                    failReason.set("Authentication wasn't enabled");
                }
            })
          .and()
          .build();
        OBSRemoteController remoteController = OBSRemoteController.builder()
          .communicator(communicator)
          .build();

        // When we connect
        remoteController.connect();
        Thread.sleep(1000);

        // Then authentication was enabled
        if(failReason.get() != null) {
            fail(failReason.get());
        }

        // Then we expect an error
        // Connection closed: 4005 - Authentication failed.
        assertThat(closeCodeReason.get().getCode()).isEqualTo(4005);
        assertThat(closeCodeReason.get().getReason()).containsIgnoringCase("Authentication failed");

    }

    /**
     * Before running this test:
     * - Start OBS locally
     * - Enable websocket authentication
     * - Set password to 'password'
     * - Run test
     */
    @Test
    void testConnectToSecuredServerWithCorrectPassword() throws Exception {

        AtomicReference<String> failReason = new AtomicReference<>();
        AtomicReference<Boolean> connectorIdentified = new AtomicReference<>(false);

        // Given we have a websocket client and annotated websocket communicator
        OBSCommunicator communicator = OBSCommunicator.builder()
          .password(PASSWORD)
          // And given we have registered callbacks to disconnect once connected & identified
          .lifecycle()
            .onIdentified((comm,identified) -> {
                System.out.println("(Test) Authenticated successfully");
                connectorIdentified.set(true);
            })
            .onHello((comm, hello) -> {
                if(hello.getAuthentication() == null) {
                    failReason.set("Authentication wasn't enabled");
                }
            })
          .and()
          .build();
        OBSRemoteController remoteController = OBSRemoteController.builder()
          .communicator(communicator)
          .build();

        // When we connect to/from OBS
        remoteController.connect();
        Thread.sleep(1000);
        remoteController.disconnect();

        // Then there should be no errors
        if (failReason.get() != null) {
            fail(failReason.get());
        }
        // And the client should have been identified
        if (!connectorIdentified.get()) {
            fail("Did not successfully identify the communicator");
        }
    }

}
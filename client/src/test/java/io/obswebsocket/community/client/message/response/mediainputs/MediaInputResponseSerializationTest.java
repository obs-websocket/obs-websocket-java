package io.obswebsocket.community.client.message.response.mediainputs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.obswebsocket.community.client.message.response.AbstractResponseSerializationTest;
import io.obswebsocket.community.client.message.response.mediainputs.GetMediaInputStatusResponse.MediaState;
import org.junit.jupiter.api.Test;

public class MediaInputResponseSerializationTest extends AbstractResponseSerializationTest {

  public static final String TYPE = "mediainputs";

  @Test
  void getStudioModeEnabledResponse() {
    assertResponse(TYPE, GetMediaInputStatusResponse.class, d -> {
      assertEquals(MediaState.BUFFERING, d.getMediaState());
      assertEquals(123, d.getMediaDuration());
      assertEquals(321, d.getMediaCursor());
    });
  }
}

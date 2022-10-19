package io.obswebsocket.community.client.message.request.scenes;

import io.obswebsocket.community.client.message.request.Request;
import io.obswebsocket.community.client.message.request.RequestType;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * This class is generated, do not edit!
 */
@Getter
@ToString(
    callSuper = true
)
public class GetCurrentPreviewSceneRequest extends Request<Void> {
  @Builder
  private GetCurrentPreviewSceneRequest() {
    super(RequestType.GetCurrentPreviewScene, null);
  }
}

package io.obswebsocket.community.client.message.request.sceneitems;

import io.obswebsocket.community.client.message.request.Request;
import io.obswebsocket.community.client.message.request.RequestType;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * This class is generated, do not edit!
 */
@Getter
@ToString(
    callSuper = true
)
public class GetSceneItemTransformRequest extends Request<GetSceneItemTransformRequest.SpecificData> {
  @Builder
  private GetSceneItemTransformRequest(String sceneName, Number sceneItemId) {
    super(RequestType.GetSceneItemTransform, SpecificData.builder().sceneName(sceneName).sceneItemId(sceneItemId).build());
  }

  @Getter
  @ToString
  @Builder
  static class SpecificData {
    @NonNull
    private String sceneName;

    @NonNull
    private Number sceneItemId;
  }
}

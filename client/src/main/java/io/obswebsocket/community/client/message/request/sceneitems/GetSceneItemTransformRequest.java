package io.obswebsocket.community.client.message.request.sceneitems;

import io.obswebsocket.community.client.message.request.Request.Data.Type;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class GetSceneItemTransformRequest extends SceneItemRequest<SceneItemRequest.DataWithId> {

  @Builder
  private GetSceneItemTransformRequest(@NonNull String sceneName, @NonNull Integer sceneItemId) {
    super(Type.GetSceneItemTransform, DataWithId.builder().sceneName(sceneName).sceneItemId(sceneItemId).build());
  }
}
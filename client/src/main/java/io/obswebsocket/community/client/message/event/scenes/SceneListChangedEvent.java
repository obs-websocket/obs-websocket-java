package io.obswebsocket.community.client.message.event.scenes;

import com.google.gson.annotations.SerializedName;
import io.obswebsocket.community.client.message.event.Event;
import io.obswebsocket.community.client.model.Scene;
import java.util.List;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@ToString(callSuper = true)
public class SceneListChangedEvent extends Event {

  @SerializedName("d")
  private Data messageData;

  protected SceneListChangedEvent() {
    super(Type.SceneListChanged, Intent.Scenes);
  }

  @Getter
  @ToString
  public static class SpecificData {

    private List<Scene> scenes;
  }

  @Getter
  @ToString(callSuper = true)
  @SuperBuilder
  public static class Data extends Event.Data {

    protected SpecificData eventData;
  }

}

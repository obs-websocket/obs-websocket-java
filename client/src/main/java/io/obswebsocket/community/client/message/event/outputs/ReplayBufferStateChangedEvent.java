package io.obswebsocket.community.client.message.event.outputs;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@ToString(callSuper = true)
public class ReplayBufferStateChangedEvent extends OutputStateChangedEvent {

  @SerializedName("d")
  private Data messageData;

  protected ReplayBufferStateChangedEvent() {
    super(Type.ReplayBufferStateChanged, Intent.Outputs);
  }

  @Getter
  @ToString(callSuper = true)
  @SuperBuilder
  public static class Data extends OutputStateChangedEvent.Data {

    protected OutputStateChangedEvent.SpecificData eventData;
  }
}

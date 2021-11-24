package io.obswebsocket.community.client.message.event.filters;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@ToString(callSuper = true)
public class FilterCreatedEvent extends FilterEvent {

  @SerializedName("d")
  private Data messageData;

  protected FilterCreatedEvent() {
    super(Type.FilterCreated, Intent.Filters);
  }

  @Getter
  @ToString(callSuper = true)
  @SuperBuilder
  public static class Data extends FilterEvent.Data {

    protected FilterEvent.SpecificData eventData;
  }
}

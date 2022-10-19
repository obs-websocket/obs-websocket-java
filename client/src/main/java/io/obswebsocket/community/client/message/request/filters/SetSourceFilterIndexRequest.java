package io.obswebsocket.community.client.message.request.filters;

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
public class SetSourceFilterIndexRequest extends Request<SetSourceFilterIndexRequest.SpecificData> {

  @Builder
  private SetSourceFilterIndexRequest(String sourceName, String filterName, Number filterIndex) {
    super(RequestType.SetSourceFilterIndex,
        SpecificData.builder().sourceName(sourceName).filterName(filterName)
            .filterIndex(filterIndex).build());
  }

  @Getter
  @ToString
  @Builder
  static class SpecificData {

    @NonNull
    private String sourceName;

    @NonNull
    private String filterName;

    @NonNull
    private Number filterIndex;
  }
}

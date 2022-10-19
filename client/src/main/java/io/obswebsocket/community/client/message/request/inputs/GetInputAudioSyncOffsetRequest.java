package io.obswebsocket.community.client.message.request.inputs;

import io.obswebsocket.community.client.message.request.RequestType;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class GetInputAudioSyncOffsetRequest extends InputRequest<InputRequest.Data> {
  @Builder
  private GetInputAudioSyncOffsetRequest(String inputName) {
    super(RequestType.GetInputAudioSyncOffset, Data.builder().inputName(inputName).build());
  }
}

package io.obswebsocket.community.client.message.request.inputs;

import io.obswebsocket.community.client.message.request.Request;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class GetSpecialInputsRequest extends Request<Void> {

  @Builder
  private GetSpecialInputsRequest() {
    super(Data.Type.GetSpecialInputs, null);
  }
}
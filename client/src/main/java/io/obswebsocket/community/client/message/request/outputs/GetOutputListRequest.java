package io.obswebsocket.community.client.message.request.outputs;

import io.obswebsocket.community.client.message.request.Request;
import io.obswebsocket.community.client.message.request.RequestType;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class GetOutputListRequest extends Request<Void> {

  @Builder
  private GetOutputListRequest() {
    super(RequestType.GetOutputList, null);
  }
}

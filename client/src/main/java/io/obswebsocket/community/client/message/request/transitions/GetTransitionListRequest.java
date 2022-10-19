package io.obswebsocket.community.client.message.request.transitions;

import io.obswebsocket.community.client.message.request.Request;
import io.obswebsocket.community.client.message.request.RequestType;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class GetTransitionListRequest extends Request<Void> {

  @Builder
  private GetTransitionListRequest() {
    super(RequestType.GetTransitionList, null);
  }
}

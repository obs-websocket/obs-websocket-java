package io.obswebsocket.community.client.message.request.transitions;

import io.obswebsocket.community.client.message.request.Request;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class GetTransitionListRequest extends Request<Void> {

  @Builder
  private GetTransitionListRequest() {
    super(Data.Type.GetTransitionList, null);
  }
}

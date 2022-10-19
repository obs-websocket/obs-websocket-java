package io.obswebsocket.community.client.message.request.outputs;

import io.obswebsocket.community.client.message.request.Request;
import io.obswebsocket.community.client.message.request.RequestType;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * This class is generated, do not edit!
 */
@Getter
@ToString(
    callSuper = true
)
public class GetVirtualCamStatusRequest extends Request<Void> {
  @Builder
  private GetVirtualCamStatusRequest() {
    super(RequestType.GetVirtualCamStatus, null);
  }
}

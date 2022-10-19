package io.obswebsocket.community.client.message.request.config;

import io.obswebsocket.community.client.message.request.RequestType;
import io.obswebsocket.community.client.message.request.config.PersistentDataRequest.SpecificData.Realm;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class GetPersistentDataRequest extends PersistentDataRequest {
  @Builder
  private GetPersistentDataRequest(Realm realm, String slotName) {
    super(RequestType.GetPersistentData,
        SpecificData.builder().realm(realm).slotName(slotName).build());
  }
}

package io.obswebsocket.community.message.response.filters;

import lombok.Getter;
import lombok.ToString;
import io.obswebsocket.community.message.request.Request;
import io.obswebsocket.community.message.response.RequestResponse;

@Getter
@ToString(callSuper = true)
public class SetSourceFilterSettingsResponse extends RequestResponse {
    public SetSourceFilterSettingsResponse() {
        super(Request.Type.SetSourceFilterSettings);
    }
}
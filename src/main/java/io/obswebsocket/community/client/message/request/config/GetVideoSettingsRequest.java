package io.obswebsocket.community.client.message.request.config;

import io.obswebsocket.community.client.message.request.Request;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class GetVideoSettingsRequest extends Request {
    @Builder
    private GetVideoSettingsRequest() {
        super(Type.GetVideoSettings);
    }
}

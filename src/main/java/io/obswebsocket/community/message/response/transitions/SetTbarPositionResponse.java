package io.obswebsocket.community.message.response.transitions;

import lombok.Getter;
import lombok.ToString;
import io.obswebsocket.community.message.request.Request;
import io.obswebsocket.community.message.response.RequestResponse;

@Getter
@ToString(callSuper = true)
public class SetTbarPositionResponse extends RequestResponse {
    public SetTbarPositionResponse() {
        super(Request.Type.SetTbarPosition);
    }
}
package net.twasi.obsremotejava.message.request.scenes;

import lombok.Getter;
import lombok.ToString;
import net.twasi.obsremotejava.message.request.Request;

@Getter
@ToString(callSuper = true)
public class GetCurrentPreviewSceneRequest extends Request {
    public GetCurrentPreviewSceneRequest() {
        super(Type.GetCurrentPreviewScene);
    }
}
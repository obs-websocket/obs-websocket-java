package io.obswebsocket.community.client.message.response.scenes;

import io.obswebsocket.community.client.message.AbstractSerializationTest;
import org.junit.jupiter.api.Test;

public class ScenesResponseSerializationTest extends AbstractSerializationTest {

  @Test
  void getSceneListResponse() {

    String json = this.readResourceFile("responses/scenes/GetSceneListResponse.json");

    GetSceneListResponse response = deserialize(json, GetSceneListResponse.class);

    assertSerializationAndDeserialization(json, response);
  }

  @Test
  void getGroupListResponse() {

    String json = this.readResourceFile("responses/scenes/GetGroupListResponse.json");

    GetGroupListResponse response = deserialize(json, GetGroupListResponse.class);

    assertSerializationAndDeserialization(json, response);
  }

}
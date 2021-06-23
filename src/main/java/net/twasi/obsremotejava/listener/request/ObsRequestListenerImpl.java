package net.twasi.obsremotejava.listener.request;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import net.twasi.obsremotejava.listener.lifecycle.ReasonThrowable;
import net.twasi.obsremotejava.message.request.Request;
import net.twasi.obsremotejava.message.request.RequestBatch;
import net.twasi.obsremotejava.message.response.RequestBatchResponse;
import net.twasi.obsremotejava.message.response.RequestResponse;

public class ObsRequestListenerImpl implements ObsRequestListener {

  private final ConcurrentHashMap<String, Consumer> requestListeners = new ConcurrentHashMap<>();

  @Override
  public void registerRequest(Request request, Consumer callback) {
    this.requestListeners.put(request.getRequestId(), callback);
  }

  @Override
  public void onRequestResponse(RequestResponse requestResponse) {
    try {
      if (this.requestListeners.containsKey(requestResponse.getRequestId())) {
        this.requestListeners.get(requestResponse.getRequestId()).accept(requestResponse);
      }
    } finally {
      this.requestListeners.remove(requestResponse.getRequestId());
    }
  }

  @Override
  public void registerRequestBatch(RequestBatch requestBatch, Consumer callback) {
    this.requestListeners.put(requestBatch.getRequestId(), callback);
  }

  @Override
  public void onRequestBatchResponse(RequestBatchResponse requestBatchResponse) {
    try {
      if (this.requestListeners.containsKey(requestBatchResponse.getRequestId())) {
        this.requestListeners.get(requestBatchResponse.getRequestId()).accept(requestBatchResponse);
      }
    } finally {
      this.requestListeners.remove(requestBatchResponse.getRequestId());
    }
  }

}

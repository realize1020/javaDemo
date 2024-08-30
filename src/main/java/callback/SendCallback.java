package callback;

public interface SendCallback {
    void onSuccess(final String sendResult);

    void onException(final Throwable e);
}

package callback;

public class test {
    public static void main(String[] args) {
        DefaultMQProducer producer = new DefaultMQProducer();
        producer.send("hello,world", new SendCallback() {
            @Override
            public void onSuccess(String sendResult) {
                System.out.println("hello world成功！");
            }

            @Override
            public void onException(Throwable e) {
                System.out.println("hello world失败！");
            }
        });
    }
}

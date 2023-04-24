package gson;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

public class GsonTest {

    public static void main(String[] args) {
        String responseResult = "{\"status\":200,\"message\":\"OK\",\"data\":{\"join_url\":\"https://umeet.com.cn/j/1894216583\",\"option_jbh\":true,\"updateDate\":null,\"meeting_id\":\"1894216583\",\"enable_webinar\":false,\"start_url\":\"https://umeet.com.cn/s/1894216583?zak=eyJ6bV9za20iOiJ6bV9vMm0iLCJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJjbGllbnQiLCJ1aWQiOiJCUkpsQzJjNVJQbUs5ekdMZ3JJSTRBIiwiaXNzIjoid2ViIiwic3R5IjoxMDAsIndjZCI6InN5IiwiY2x0IjowLCJzdGsiOiI1MkI3UjcyLUVzcUkzS1R6LWNKVWZGcWV5N3lZRGQtWHhqNXdJT0xsc2dRLkJnWXNRVVZJVFVWTmVWZENjbTFzVkZWSU9HdEdNWGMxY0M5dWFuRlFVV3BQVVdSVWNrTnFZMmQyWm1WblZUMUFZall6TkRZeE9XSTNOREkxTkdFeU1EWmhNell4TVRCbE16WXhOak0yTWpnNVl6STROamxoTnpVd1lURmxZekk0T1RGbFltVm1Oamd4WVRReVpqUTBOd0FnVm0xclptWllkVTkzUldaVVNEbHhOMEozZWxkelJ6aE1SMU5MV2poTWJISUFBbk41QUFBQmhray0wWDhBRW5VQUFBQSIsImV4cCI6MTY3NjI3MzI5OSwiaWF0IjoxNjc2MjY2MDk5LCJhaWQiOiJFajhiS196QVRJeXJBYTRUamVTTTlnIiwiY2lkIjoiIn0.OVU6NBQtSXkgROoksK9ZKYO1x70-FwuUtgU9qOxSMt8\",\"user_key\":null,\"ready_time\":30,\"meeting_capacity\":100,\"uuid\":\"cKDH+nwqR4u4tozhL+jGdA==\",\"host_id\":\"BRJlC2c5RPmK9zGLgrII4A\",\"token\":\"zaGvJuFJHP5Iid1I2En6KFGQkwjqtKDlJWjmGo0FZ-A.BgY4T0lNRmQyVmF6cExGaVpBWk5QeE1KZVAya3FjcWdUYmRiUHBYQjgva0xtbHF5a1pmK05qeUp3PT1AYjYzNDYxOWI3NDI1NGEyMDZhMzYxMTBlMzYxNjM2Mjg5YzI4NjlhNzUwYTFlYzI4OTFlYmVmNjgxYTQyZjQ0NwAMM0NCQXVvaVlTM3M9AAAAAAGD8z2DpgASdQAAAA\",\"duration\":1200,\"start_time\":\"2023-02-13 13:02:18\",\"host_key\":\"212769\",\"play_addr\":null,\"live_id\":null,\"watch_url\":null,\"mute_upon_entry\":false,\"host_email\":\"feiji@70872715EVERBRIGHT.com\",\"createDate\":null}}";
        Gson gson=new Gson();
        //MeetResult meetResult=gson.fromJson(responseResult, new TypeToken<MeetResult>(){}.getType());
        MeetResult meetResult=gson.fromJson(responseResult,gson.MeetResult.class);
        JsonObject jsonObject =JsonParser.parseString(responseResult).getAsJsonObject();
        JsonObject dataJsonObject = jsonObject.get("data").getAsJsonObject();
        Data data = gson.fromJson(dataJsonObject,Data.class);
        System.out.println(meetResult.toString());
        System.out.println(data.toString());


        System.out.println();
    }
}

package ma.ensa.sendmsg.service;


import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class SmsService {


    public int sendSms(String phoneNumber, String lable,String message) throws IOException {
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"sender\":\"pfs\",\"recipient\":\"" + phoneNumber + "\",\"content\":\"" + message + "\",\"type\":\"marketing\",\"tag\":\"\\\"tag1\\\" OR [\\\"tag1\\\", \\\"tag2\\\"]\",\"webUrl\":\"http://requestb.in/173lyyx1\",\"unicodeEnabled\":true,\"organisationPrefix\":\""+lable+"\"}");
        Request request = new Request.Builder()
                .url("https://api.brevo.com/v3/transactionalSMS/send")
                .post(body)
                .addHeader("accept", "application/json")
                .addHeader("content-type", "application/json")
                .addHeader("api-key", "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")
                .build();

        return client.newCall(request).execute().code();
    }
}



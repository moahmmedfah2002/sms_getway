package ma.ensa.sendmsg.service;


import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class SmsService {


    public int sendSms(String phoneNumber, String message, String label) throws IOException {
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"sender\":\"" + label + "\",\"recipient\":\"" + phoneNumber + "\",\"content\":\"" + message + "\",\"type\":\"marketing\",\"tag\":\"\\\"tag1\\\" OR [\\\"tag1\\\", \\\"tag2\\\"]\",\"webUrl\":\"http://requestb.in/173lyyx1\",\"unicodeEnabled\":true,\"organisationPrefix\":\"MyCompany\"}");
        Request request = new Request.Builder()
                .url("https://api.brevo.com/v3/transactionalSMS/send")
                .post(body)
                .addHeader("accept", "application/json")
                .addHeader("content-type", "application/json")
                .build();

        return client.newCall(request).execute().code();
    }
}



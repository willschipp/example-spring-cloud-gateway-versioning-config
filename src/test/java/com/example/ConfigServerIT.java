package com.example;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayOutputStream;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles({"test","native"})
public class ConfigServerIT {

    @LocalServerPort
    private int port;

    @Test
    public void testResponse() throws Exception {
        //hit the server and see if a set of properties comes back
        HttpUriRequest request = new HttpGet("http://localhost:" + port + "/testapp/dev/");
        HttpResponse response = HttpClientBuilder.create().build().execute(request);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        response.getEntity().writeTo(bos);
        String payload = new String(bos.toByteArray());
        assertTrue(payload.contains("classpath:/config-repo/testapp.yml"));
    }
}

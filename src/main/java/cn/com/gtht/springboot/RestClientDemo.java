package cn.com.gtht.springboot;


import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

import java.net.http.HttpClient;
import java.time.Duration;
import java.util.List;

/**
 * Author: zhongzhu.wang
 * Date:2026/1/8 15:31
 */
public class RestClientDemo {

    public static void main(String[] args) {
        HttpClient httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))   // 建连超时
                .followRedirects(HttpClient.Redirect.NORMAL)
                .version(HttpClient.Version.HTTP_2)
                .build();

        RestClient restClient = RestClient.builder()
                .baseUrl("https://671d110e-33d7-449f-b7f7-7387077781df.mock.pstmn.io")
                .requestFactory(new JdkClientHttpRequestFactory(httpClient))
                .build();

        RestClient.RequestBodySpec requestBodySpec = restClient.post()
                .uri("https://671d110e-33d7-449f-b7f7-7387077781df.mock.pstmn.io/am5/rest/OATH/create-assign-and-encrypt")
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-api-key", "PMAK-xxx")
                .header("x-mock-response-name", "aaa")
                .body("{}");

        ResponseEntity<ApiResponse> result = requestBodySpec.retrieve()
                .toEntity(ApiResponse.class);

        ApiResponse apiResponse = requestBodySpec.retrieve()
                .body(ApiResponse.class);

        System.out.println("Response status: " + result.getStatusCode());
        System.out.println("Response headers: " + result.getHeaders());
        System.out.println("Contents: " + apiResponse);
    }
}

class ApiResponse {
    public Result result;
    public long amProcessingTimeMillis;
}


class Result {
    public TokenInfoBean tokenInfoBean;
    public EncryptedSeeds encryptedSeeds;
    public Specs specs;
    public String activationPassword;
    public UserDevice userDevice;
    public int version;
}


class TokenInfoBean {
    public String serialNum;
    public String vendor;
    public String model;
    public List<String> assignedUserUUIDs;
    public List<String> assignedUserIds;
    public String UUID;
    public String name;
    public int version;
}

class EncryptedSeeds {
    public String TOTP;
    public String OCRA;
}


class Specs {
    public TotpSpec TOTP;
    public OcraSpec OCRA;
}


class TotpSpec {
    public String authMode;
    public String algorithm;
    public String suite;
    public int responseFormatLength;
    public int timeInterval;
    public int time;
    public int timeDrift;
    public int counter;
    public int version;
}

class OcraSpec {
    public String authMode;
    public String algorithm;
    public String suite;
    public int responseFormatLength;
    public int timeInterval;
    public int time;
    public int timeDrift;
    public int counter;
    public int version;
}


class UserDevice {
    public String UUID;
    public String status;
    public String type;
    public String id;
    public String agent;
    public String desc;
    public long createdTime;
    public String adminModifier;
    public long adminModifiedTime;
    public long lastUsedTime;
}
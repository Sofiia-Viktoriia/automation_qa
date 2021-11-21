package reqres.apachehttp;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import reqres.logic.dto.AuthDto;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static reqres.logic.Endpoints.LOGIN;
import static reqres.logic.Endpoints.REGISTER;
import static reqres.utils.providers.RequestProvider.*;

public class AuthenticationTests extends BaseTest {

    @Test
    public void registerWithCorrectData() throws IOException {
        AuthDto requestBody = getDefaultCorrectAuthDtoForRegister();
        HttpPost httpPost = new HttpPost(REGISTER);
        HttpEntity entity = new StringEntity(requestBody.toString(), ContentType.APPLICATION_JSON);
        httpPost.setEntity(entity);
        CloseableHttpResponse response = httpClient.execute(httpPost);
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
        HttpEntity responseEntity = response.getEntity();
        JSONObject jsonResponse = new JSONObject(IOUtils.toString(responseEntity.getContent(), StandardCharsets.UTF_8));
        Assert.assertEquals(jsonResponse.getInt("id"), 4);
        Assert.assertEquals(jsonResponse.getString("token"), "QpwL5tke4Pnpja7X4");
    }

    @Test
    public void registerWithIncorrectData() throws IOException {
        AuthDto requestBody = getDefaultIncorrectAuthDto();
        HttpPost httpPost = new HttpPost(REGISTER);
        HttpEntity entity = new StringEntity(requestBody.toStringNotFull(), ContentType.APPLICATION_JSON);
        httpPost.setEntity(entity);
        CloseableHttpResponse response = httpClient.execute(httpPost);
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 400);
        HttpEntity responseEntity = response.getEntity();
        JSONObject jsonResponse = new JSONObject(IOUtils.toString(responseEntity.getContent(), StandardCharsets.UTF_8));
        Assert.assertEquals(jsonResponse.getString("error"), "Missing password");
    }

    @Test
    public void loginWithCorrectData() throws IOException {
        AuthDto requestBody = getDefaultCorrectAuthDtoForLogin();
        HttpPost httpPost = new HttpPost(LOGIN);
        HttpEntity entity = new StringEntity(requestBody.toString(), ContentType.APPLICATION_JSON);
        httpPost.setEntity(entity);
        CloseableHttpResponse response = httpClient.execute(httpPost);
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
        HttpEntity responseEntity = response.getEntity();
        JSONObject jsonResponse = new JSONObject(IOUtils.toString(responseEntity.getContent(), StandardCharsets.UTF_8));
        Assert.assertEquals(jsonResponse.getString("token"), "QpwL5tke4Pnpja7X4");
    }

    @Test
    public void loginWithIncorrectData() throws IOException {
        AuthDto requestBody = getDefaultIncorrectAuthDto();
        HttpPost httpPost = new HttpPost(LOGIN);
        HttpEntity entity = new StringEntity(requestBody.toStringNotFull(), ContentType.APPLICATION_JSON);
        httpPost.setEntity(entity);
        CloseableHttpResponse response = httpClient.execute(httpPost);
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 400);
        HttpEntity responseEntity = response.getEntity();
        JSONObject jsonResponse = new JSONObject(IOUtils.toString(responseEntity.getContent(), StandardCharsets.UTF_8));
        Assert.assertEquals(jsonResponse.getString("error"), "Missing password");
    }
}

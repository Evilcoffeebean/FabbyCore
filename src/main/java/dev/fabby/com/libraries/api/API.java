package dev.fabby.com.libraries.api;

import com.google.common.net.HttpHeaders;
import dev.fabby.com.libraries.player.Rank;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.json.JSONObject;

import java.net.InetAddress;
import java.util.Arrays;
import java.util.UUID;

public class API {


    private static final String API_HOST = "http://localhost:8080/";



    private String get(String url) {
        try(CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet get = new HttpGet(API_HOST + url);
            get.addHeader(HttpHeaders.AUTHORIZATION, "Bearer 2Z7c9rpgSWkxH7hB");
            var response = client.execute(get);
            var entity = response.getEntity();

            if(entity != null) {
                return EntityUtils.toString(entity);
            } else {
                return "Error reaching API.";
            }
        } catch (Exception e) {
            System.out.println("Couldn't fetch data from API. Contacted endpoint: " + API_HOST + url);
            e.printStackTrace();
        }
        return "Couldn't fetch data from API.";
    }

    private void post(String url, JSONObject object) {
        try(CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(API_HOST + url);
            post.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
            post.addHeader(HttpHeaders.AUTHORIZATION, "Bearer 2Z7c9rpgSWkxH7hB");
            post.setEntity(new StringEntity(object.toString()));
            client.execute(post);
        } catch (Exception e) {
            System.out.println("Couldn't fetch data from API. Contacted endpoint: " + API_HOST + url);
            e.printStackTrace();
        }
    }

    private String postWithResponse(String url, JSONObject object) {
        try(CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(API_HOST + url);
            post.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
            post.addHeader(HttpHeaders.AUTHORIZATION, "Bearer 2Z7c9rpgSWkxH7hB");
            post.setEntity(new StringEntity(object.toString()));
            var response = client.execute(post);
            var entity = response.getEntity();

            if(entity != null) {
                return EntityUtils.toString(entity);
            } else {
                return "Error reaching API.";
            }
        } catch (Exception e) {
            System.out.println("Couldn't fetch data from API. Contacted endpoint: " + API_HOST + url);
            e.printStackTrace();
        }
        return "Couldn't fetch data from API.";
    }

    public JSONObject getPlayerInfo(UUID uuid) {
        var response = get("players/" + uuid.toString());
        try {
            return new JSONObject(response);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void createPlayer(UUID uuid, InetAddress address, String name) {
        JSONObject obj = new JSONObject();
        obj.put("uuid", uuid.toString());
        obj.put("name", name);
        obj.put("rank", Rank.MEMBER.name());
        obj.put(
                "ip_address",
                Arrays.toString(address.getAddress()).substring(1).split(":")[0].replaceAll("/", "")
        );

        post("players", obj);
    }

    public boolean playerExists(UUID uuid) {
        try {
            return !get("players/" + uuid.toString() + "/exists").equals("null");
        } catch (Exception e) {
            System.out.println("Couldn't fetch data from API. Contacted endpoint: " + API_HOST + "players/" + uuid.toString() + "/exists");
            return false;
        }
    }


}

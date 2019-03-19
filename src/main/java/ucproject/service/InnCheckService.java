package ucproject.service;

import com.google.gson.Gson;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import ucproject.domain.INNOrganization;
import ucproject.domain.Suggestions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class InnCheckService {
    static String PROXY_USERNAME = "aamartynyuk";
    static String PROXY_PASSWORD = "Ueyuybh_1945";
    static final String PROXY_HOSTNAME = "gren-s-tmg01a.gk.rosatom.local";
    static final String PROXY_PORT = "8080";
    private final String USER_AGENT = "Mozilla/5.0";
    private final String URL = "https://suggestions.dadata.ru/suggestions/api/4_1/rs/suggest/party";
    private String postResultString;


    public String sendingPost(String inn) {


     //   HttpClient client = new DefaultHttpClient();
/////
        String[] creds = credRead();
        PROXY_USERNAME = creds[0].replaceAll("\n", "").replaceAll("\r", "");
        PROXY_PASSWORD = creds[1].replaceAll("\n", "").replaceAll("\r", "");

        DefaultHttpClient client = new DefaultHttpClient();

        client.getCredentialsProvider().setCredentials(
                new AuthScope(PROXY_HOSTNAME, 8080),
                new UsernamePasswordCredentials(PROXY_USERNAME, PROXY_PASSWORD));

        //HttpHost targetHost = new HttpHost("TARGET HOST", 443, "https");
        HttpHost proxy = new HttpHost(PROXY_HOSTNAME, 8080);

        client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
//////

        HttpPost post = new HttpPost(URL);

        post.setHeader("User-Agent", USER_AGENT);
        post.setHeader("Content-Type", "application/json");
        post.setHeader("Accept", "application/json");
        post.setHeader("Authorization", "Token 3312ffbd375be3421d41e78a7a8e414f2bce1d6c");
        HttpResponse response = null;
        try {
            //StringEntity requestEntity = new StringEntity("{ \"query\": \"сбербанк\" }", ContentType.APPLICATION_JSON);post.setEntity(requestEntity); // По имени организации
            post.setEntity(new StringEntity("{ \"query\": \"" + inn + "\" }", ContentType.create("application/json")));
            response = client.execute(post);
            System.out.println("\nSending 'POST' request to URL : " + URL);
            System.out.println("Post parameters : " + post.getEntity());
            System.out.println("Response Code : " +
                    response.getStatusLine().getStatusCode());

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent(), "UTF8"));

            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            postResultString = result.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }


        return postResultString;
    }

    private static String[] credRead()
    {
        String[] creds = new String[0];

        try(FileReader reader = new FileReader("C:\\Creds\\creds.txt"))
        {
            char[] buf = new char[256];
            int c;
            while((c = reader.read(buf))>0){

                if(c < 256){
                    buf = Arrays.copyOf(buf, c);
                }
                creds = String.valueOf(buf).trim().replaceAll(" ", "").split("\n");
            }
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }


        return creds;
    }


    public Suggestions jsonParsing(String postResultString) {
        Gson g = new Gson();
        Suggestions suggestions = g.fromJson(postResultString.toString(), Suggestions.class);

        for (INNOrganization innOrganization : suggestions.getSuggestions())
        {
            innOrganization.setValue(innOrganization.getValue() != null ? innOrganization.getValue() : "Не указано");
            innOrganization.setUnrestricted_value(innOrganization.getUnrestricted_value() != null ? innOrganization.getUnrestricted_value() : "Не указано");
            //organization.getData().setCapital(organization.getData().getCapital() != null ? toUTF(organization.getData().getCapital()) : "Не указано");
            // organization.getData().setFounders(organization.getData().getFounders() != null ? toUTF(organization.getData().getFounders()) : "Не указано");
            innOrganization.getData().setInn(innOrganization.getData().getInn() != null ? innOrganization.getData().getInn() : "Не указано");
            innOrganization.getData().setKpp(innOrganization.getData().getKpp() != null ? innOrganization.getData().getKpp() : "Не указано");
            innOrganization.getData().setOgrn(innOrganization.getData().getOgrn() != null ? innOrganization.getData().getOgrn() : "Не указано");
            innOrganization.getData().setOkpo(innOrganization.getData().getOkpo() != null ? innOrganization.getData().getOkpo() : "Не указано");
            innOrganization.getData().setOkved(innOrganization.getData().getOkved() != null ? innOrganization.getData().getOkved() : "Не указано");
            // organization.getData().setManagers(innOrganization.getData().getManagers() != null ? toUTF(organization.getData().getManagers()) : "Не указано");
            //organization.getData().setPhones(organization.getData().getPhones() != null ? toUTF(organization.getData().getPhones()) : "Не указано");
            //organization.getData().setEmails(organization.getData().getEmails() != null ? toUTF(organization.getData().getEmails()) : "Не указано");
            //organization.getData().getManagement().setName(organization.getData().getManagement().getName() != null ? toUTF(organization.getData().getManagement().getName()) : "Не указано");
            // organization.getData().getManagement().setPost(organization.getData().getManagement().getPost() != null ? toUTF(organization.getData().getManagement().getPost()) : "Не указано");
           innOrganization.getData().getOpf().setType(innOrganization.getData().getOpf().getType() != null ? innOrganization.getData().getOpf().getType() : "Не указано");
           innOrganization.getData().getOpf().setFull(innOrganization.getData().getOpf().getFull() != null ? innOrganization.getData().getOpf().getFull() : "Не указано");
           innOrganization.getData().getName().setFull(innOrganization.getData().getName().getFull() != null ? innOrganization.getData().getName().getFull() : "Не указано");
           innOrganization.getData().getName().setFull_with_opf(innOrganization.getData().getName().getFull_with_opf() != null ? innOrganization.getData().getName().getFull_with_opf() : "Не указано");
           innOrganization.getData().getName().setShort_with_opf(innOrganization.getData().getName().getShort_with_opf() != null ? innOrganization.getData().getName().getShort_with_opf() : "Не указано");
           innOrganization.getData().getAddress().setValue(innOrganization.getData().getAddress().getValue() != null ? innOrganization.getData().getAddress().getValue() : "Не указано");

        }

        return suggestions;
    }


    public String gettingUpDate() {

        Document html = null;


        try {
            html = Jsoup.connect("https://dadata.ru/clean/").get();
        } catch (IOException e) {
            e.printStackTrace();
        }


        String[] servNames = html.body().getElementsByClass("col-xs-7").text().split(" ");
        String[] servUpdates = html.body().getElementsByClass("col-xs-5").text().split(" ");
        Map<String, String> dictionares = new HashMap<>();

        System.out.println(servUpdates[3]);
        return servUpdates[3];
    }


}

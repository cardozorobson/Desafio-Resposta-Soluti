/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;


/**
 *
 * @author robsoncardozo
 */
public class GetPostDesafio {
    
    public static String text = "";
    
    public static String respostaJson;
    
    public static String recebeJson;
    
    public static String recebeDesafio;
    
    static URL url;

    static URLConnection con;

    public static String GetDesafio() throws Exception {
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            @Override
            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        }
        };

        // Install the all-trusting trust manager
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

        // Create all-trusting host name verifier
        HostnameVerifier allHostsValid = new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };

        // Install the all-trusting host verifier
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        int ch;
        
        
        url = new URL("https://api-prova.lab.ca.inf.br:9445/desafio");
        con = url.openConnection();
        Reader reader = new InputStreamReader(con.getInputStream());
        while (true) {
            ch = reader.read();
            if (ch == -1) {
                break;
            }
            
            text = text + (char) ch;
            
        }
            
      
        JSONObject parseJson = (JSONObject)new JSONParser().parse(text);
        
        
       recebeJson =  (String) parseJson.get("desafio");
       
       
        
        
        
           
        
        
        recebeDesafio = text;
       
        
        
        return text;
        
        
    }

    public static void postDesafio() throws IOException {
       
        String url = "https://api-prova.lab.ca.inf.br:9445/desafio";
        URL myurl = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) myurl.openConnection();
        con.setRequestMethod("POST");
        String query = respostaJson;
        con.setRequestProperty("Content-Type", "validation_messages");
        con.setRequestProperty("Content-Type", "resposta");
        
        con.setDoOutput(true);
        con.setDoInput(true);

        try (DataOutputStream output = new DataOutputStream(con.getOutputStream())) {
            output.writeBytes(query);
        }
        System.out.println("Resp Code:" + con.getResponseCode());
        System.out.println("Resp Message:" + con.getResponseMessage());
        System.out.println(con.getHeaderFields());
        //PrintWriter out = response.getWriter();
        
        
        
   

    }
    
     public static byte [] convertStringToByteArray()
    {

        String stringToConvert = recebeJson;

        byte[] theByteArray = stringToConvert.getBytes();

        return theByteArray;

    }
    
    
}

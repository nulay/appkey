package by.imix.taskexecutor.temp;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.auth.DigestScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by miha on 15.09.2015.
 */
public class NewTestEnterWithAuth {
    private static final Logger _log= LoggerFactory.getLogger(NewTestEnterWithAuth.class);
    public static void main(final String[] args) throws Exception {
        final HttpHost targetHost = new HttpHost("mysql", 8080, "http");

        final DefaultHttpClient httpclient = new DefaultHttpClient();
        try {
            httpclient.getCredentialsProvider().setCredentials(new AuthScope(targetHost.getHostName(), targetHost.getPort()),
                    new UsernamePasswordCredentials("user1", "user1Pass"));

            // Create AuthCache instance
            final AuthCache authCache = new BasicAuthCache();
            // Generate DIGEST scheme object, initialize it and add it to the local auth cache
            final DigestScheme digestAuth = new DigestScheme();
            // Suppose we already know the realm name
            digestAuth.overrideParamter("realm", "Custom Realm Name");

            // digestAuth.overrideParamter("nonce", "whatever");
            authCache.put(targetHost, digestAuth);

            // Add AuthCache to the execution context
            final BasicHttpContext localcontext = new BasicHttpContext();
            localcontext.setAttribute(ClientContext.AUTH_CACHE, authCache);

            final HttpGet httpget = new HttpGet("http://mysql:8080/spring-security-rest-digest-auth/api/foos/1");

            _log.debug("executing request: " + httpget.getRequestLine());
            _log.debug("to target: " + targetHost);

            for (int i = 0; i < 3; i++) {
                final HttpResponse response = httpclient.execute(targetHost, httpget, localcontext);
                final HttpEntity entity = response.getEntity();

                _log.debug("----------------------------------------");
                _log.debug(response.getStatusLine().toString());
                if (entity != null) {
                    _log.debug("Response content length: " + entity.getContentLength());
                }
                EntityUtils.consume(entity);
            }
        } finally {
            // When HttpClient instance is no longer needed, shut down the connection manager to ensure immediate deallocation of all system resources
            httpclient.getConnectionManager().shutdown();
        }
    }
}

package com.tpo.strava.oauth.api;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.Token;
import com.github.scribejava.core.model.Verifier;
import com.github.scribejava.core.oauth.OAuthService;

import java.util.Scanner;

/**
 * @author Tiberiu
 * @since 30/11/15.
 */
public class StravaOAuth2ApiTest {
    private static final Token EMPTY_TOKEN = null;

    public static void main(String[] args) {

        // Replace these with your client id and secret
        final String clientId = "your client id";
        final String clientSecret = "your client secret";

        final OAuthService service = new ServiceBuilder()
                .provider(StravaOAuth2Api.class)
                .apiKey(clientId)
                .apiSecret(clientSecret)
                .callback("http://localhost:8080/oauth/callback")
                .build();

        final Scanner in = new Scanner(System.in, "UTF-8");

        // Obtain the Authorization URL
        final String authorizationUrl = service.getAuthorizationUrl(EMPTY_TOKEN);

        System.out.println("Go to authorization URL:");
        System.out.println(authorizationUrl);
        System.out.println("Copy/paste the authorization code here");
        System.out.print(">>");
        final Verifier verifier = new Verifier(in.nextLine());
        System.out.println();

        System.out.println("Trading the Request Token for an Access Token...");
        final Token accessToken = service.getAccessToken(EMPTY_TOKEN, verifier);
        System.out.println("Got the Access Token!");
        System.out.println("(if your curious it looks like this: " + accessToken + " )");
    }
}
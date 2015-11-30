package com.tpo.strava.oauth.api;

import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.extractors.AccessTokenExtractor;
import com.github.scribejava.core.extractors.JsonTokenExtractor;
import com.github.scribejava.core.model.OAuthConfig;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.utils.OAuthEncoder;

/**
 * @author Tiberiu
 * @since 30/11/15.
 */
public class StravaOAuth2Api extends DefaultApi20 {

    public static final String STRAVA_AUTH_URL = "https://www.strava.com/oauth/authorize?client_id=%s&response_type=code&redirect_uri=%s";

    @Override
    public Verb getAccessTokenVerb() {
        return Verb.POST;
    }

    @Override
    public AccessTokenExtractor getAccessTokenExtractor() {
        return new JsonTokenExtractor();
    }

    @Override
    public String getAccessTokenEndpoint() {
        return "https://www.strava.com/oauth/token";
    }

    @Override
    public String getAuthorizationUrl(OAuthConfig oAuthConfig) {
        return String.format(STRAVA_AUTH_URL, oAuthConfig.getApiKey(), OAuthEncoder.encode(oAuthConfig.getCallback()));
    }
}

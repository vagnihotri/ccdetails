package in.flexmoney.assignment.data.net.interceptor;

import in.flexmoney.assignment.data.net.RestApi;

import java.io.IOException;
import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

@Singleton
public class HttpInterceptor implements Interceptor {

    @Inject
    public HttpInterceptor() {}

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request().newBuilder()
                .addHeader("Content-Type", RestApi.CONTENT_TYPE)
                //.addHeader("Origin", RestApi.ORIGIN)
                .build();
        return chain.proceed(request);
    }

}

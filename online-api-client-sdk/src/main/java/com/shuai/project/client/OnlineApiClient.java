package com.shuai.project.client;


import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSON;
import com.shuai.project.HeartApiClientConfig;
import com.shuai.project.model.BaiduParams;
import com.shuai.project.model.WeatherParams;
import com.shuai.project.model.AvatarParams;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static com.shuai.project.utils.SignUtils.getSign;

/**
 * 调用第三方的客户端
 *
 * @ClassName HeartApiClient
 * @Description TODO
 * @Author OTTO
 * @Date 2022/11/30 20:52
 */
@Component
public class OnlineApiClient {

    @Resource
    private HeartApiClientConfig heartApiClientConfig;


    private Map<String, String> getHeaderMap(String body) {
        String encode=null;
        encode = URLEncoder.encode(body, StandardCharsets.UTF_8);
        Map<String, String> hasMap = new HashMap<>();
        String nonce = RandomUtil.randomNumbers(4);
        String currentTime = String.valueOf(System.currentTimeMillis() / 1000);
        hasMap.put("accessKey", heartApiClientConfig.getAccessKey());
        hasMap.put("nonce", nonce);
        hasMap.put("body", encode);
        hasMap.put("timestamp", currentTime);
        hasMap.put("sign", getSign(encode, heartApiClientConfig.getSecretKey(),nonce,currentTime));
        return hasMap;
    }


    public String getWeatherInfo(WeatherParams weatherParams){
        String parameters = JSON.toJSONString(weatherParams);
        return onlineInvoke(parameters, "/api/weather/weatherInfo");
    }

    public String getAvatarUrl(AvatarParams avatarParams){
        String parameters = JSON.toJSONString(avatarParams);
        return onlineInvoke(parameters, "/api/avatar/avatarUrl");
    }

    public String getBaiduInfo(BaiduParams baiduParams){
        String parameters = JSON.toJSONString(baiduParams);
        return onlineInvoke(parameters, "/api/baidu/baiduInfo");
    }

    public String doChat(String text){
        String parameters = JSON.toJSONString(text);
        return onlineInvoke(parameters, "/api/gpt/chat");
    }

    public String onlineInvoke(String parameters,String url) {
        HttpResponse httpResponse = HttpRequest.post(heartApiClientConfig.getGatewayHost() + url)
                .addHeaders(getHeaderMap(parameters))
                .body(parameters)
                .execute();
        return httpResponse.body();
    }
}

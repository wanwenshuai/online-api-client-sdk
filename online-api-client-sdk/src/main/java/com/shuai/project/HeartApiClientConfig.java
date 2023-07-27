package com.shuai.project;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName HeartApiClientConfig
 * @Description TODO
 * @Author OTTO
 * @Date 2022/12/1 23:26
 */

@Configuration
@Data
@ComponentScan
@ConfigurationProperties("online.client")
public class HeartApiClientConfig {

    private String accessKey;

    private String secretKey;

    private String gatewayHost;

}

# online-api-client-sdk
## maven地址
```
<dependency>
        <groupId>io.github.wanwenshuai</groupId>
        <artifactId>online-api-client-sdk</artifactId>
        <version>0.0.1</version>
</dependency>
```
> 添加配置

![image](https://github.com/wanwenshuai/online-api-client-sdk/assets/92237291/def2c6e8-5a09-44ba-9e53-7aa247b6f7db)

> 注入资源
@Resource
private OnlineApiClient apiClient;

> 调用
apiClient.onlineInvoke(userRequestParams,url)


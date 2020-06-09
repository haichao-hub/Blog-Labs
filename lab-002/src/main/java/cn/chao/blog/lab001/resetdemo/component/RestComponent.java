package cn.chao.blog.lab001.resetdemo.component;

import java.util.Map;

import cn.chao.blog.lab001.resetdemo.base.EntityResponse;
import cn.chao.blog.lab001.resetdemo.base.ListResponse;
import cn.chao.blog.lab001.resetdemo.base.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 描述
 *
 * @author
 * @date 2019-03-09
 */
@Component
public class RestComponent {
    public static final ObjectMapper objectMapper = new ObjectMapper();
    @Value("${base.url:127.0.0.1}")
    public String url;

    @Autowired
    private RestTemplate restTemplate;

    private HttpHeaders headers = new HttpHeaders();

    {
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    public <T> EntityResponse<T> postForEntity(String interfaceUrl, Object req, Class<T> clazz) {
        return restTemplate.exchange(toUrl(interfaceUrl), HttpMethod.POST
                , new HttpEntity(req, headers), getEntityReference(clazz)).getBody();
    }

    /**
     * @param interfaceUrl       接口名称
     * @param req          请求参数
     * @param clazz        返回的类型
     * @param uriVariables url参数，使用如下：
     *                     String interfaceUrl = "org/node/getOrgInfo/id/{org_id}";
     *                     Map<String, String> uriVariables = new HashMap();
     *                     uriVariables.put("org_id", 2);
     * @return
     */
    public <T> EntityResponse<T> getForEntity(String interfaceUrl, Object req, Class<T> clazz, Object... uriVariables) {
        return restTemplate.exchange(toUrl(interfaceUrl), HttpMethod.GET
                , new HttpEntity(req, headers), getEntityReference(clazz), uriVariables).getBody();
    }

    public <T> ListResponse<T> getForList(String interfaceUrl, Object req, Class<T> clazz, Map<String, ?> uriVariables) {
        return restTemplate.exchange(toUrl(interfaceUrl), HttpMethod.GET
                , new HttpEntity(req, headers), getListReference(clazz), uriVariables).getBody();
    }

    private <T> ParameterizedTypeReference<EntityResponse<T>> getEntityReference(Class<T> clazz) {
        //objectMapper已经缓存Type，不需要额外缓存
        JavaType javaType = objectMapper.getTypeFactory().constructParametrizedType(EntityResponse.class, EntityResponse.class, clazz);
        return ParameterizedTypeReference.forType(javaType);
    }

    public <T> ListResponse<T> postForList(String interfaceUrl, Object req, Class<T> clazz) {
        return restTemplate.exchange(toUrl(interfaceUrl), HttpMethod.POST
                , new HttpEntity(req, headers), getListReference(clazz)).getBody();
    }

    private <T> ParameterizedTypeReference<ListResponse<T>> getListReference(Class<T> clazz) {
        //objectMapper已经缓存Type，不需要额外缓存
        JavaType javaType = objectMapper.getTypeFactory().constructParametrizedType(ListResponse.class, ListResponse.class, clazz);
        return ParameterizedTypeReference.forType(javaType);
    }

    private <T> ParameterizedTypeReference<PageResponse<T>> getPageReference(Class baseClass, Class<T> genericClass) {
        //objectMapper已经缓存Type，不需要额外缓存
        JavaType javaType = objectMapper.getTypeFactory().constructParametrizedType(baseClass, baseClass, genericClass);
        return ParameterizedTypeReference.forType(javaType);
    }

    public <T> PageResponse<T> postForPage(String interfaceUrl, Object req, Class<T> clazz) {
        PageResponse<T> response = restTemplate.exchange(toUrl(interfaceUrl), HttpMethod.POST
                , new HttpEntity(req, headers), getPageReference(PageResponse.class, clazz)).getBody();
        return response;
    }


    private String toUrl(String interfaceUrl) {
        return url + interfaceUrl;
    }
}
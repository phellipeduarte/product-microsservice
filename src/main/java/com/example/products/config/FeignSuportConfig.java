package com.example.products.config;

import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.spi.ObjectFactory;
import java.beans.Encoder;
import java.util.Hashtable;


/*public class FeignSuportConfig {
    @Bean
    public Encoder multipartFormEncoder() {
        return new SpringFormEncoder(new SpringEncoder(new ObjectFactory() {
            @Override
            public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment) throws Exception {
                return this;
            }

            public HttpMessageConverters getObject() throws BeansException {
                return new HttpMessageConverters(new RestTemplate().getMessageConverters());
            }
        }));
    }
}
*/
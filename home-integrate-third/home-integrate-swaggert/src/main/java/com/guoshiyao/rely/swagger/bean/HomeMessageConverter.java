

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.swagger.bean;

import cn.hutool.core.util.ClassUtil;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import com.guoshiyao.rely.outgoing.OutputParamAb;
import org.springframework.core.GenericTypeResolver;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractGenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJacksonInputMessage;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;

public class HomeMessageConverter extends AbstractGenericHttpMessageConverter<Object> {

    private final Charset charset = StandardCharsets.UTF_8;

    protected ObjectMapper objectMapper;

    private final PrettyPrinter prettyPrinter = new DefaultPrettyPrinter();
    private static final Map<String, JsonEncoding> ENCODINGS;

    static {
        ENCODINGS = CollectionUtils.newHashMap(JsonEncoding.values().length);
        for (JsonEncoding encoding : JsonEncoding.values()) {
            ENCODINGS.put(encoding.getJavaName(), encoding);
        }
        ENCODINGS.put("US-ASCII", JsonEncoding.UTF8);
    }

    public HomeMessageConverter() {
        this.objectMapper = Jackson2ObjectMapperBuilder.json().build();
        this.setSupportedMediaTypes(Arrays.asList(MediaType.ALL, MediaType.APPLICATION_JSON, MediaType
                .APPLICATION_JSON_UTF8, MediaType.APPLICATION_FORM_URLENCODED));

    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return supportInternalAndHandle(clazz);
    }

    private boolean supportInternalAndHandle(Class<?> clazz) {
        boolean securityBody = ClassUtil.isAssignable(OutputParamAb.class, clazz);
        return securityBody;
    }
//
//    @Override
//    public boolean canRead(@Nullable MediaType mediaType) {
//        if (mediaType == null) {
//            return true;
//        }
//        for (MediaType supportedMediaType : getSupportedMediaTypes()) {
//            if (supportedMediaType.includes(mediaType)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    @Override
//    public boolean canWrite(Class<?> clazz, @Nullable MediaType mediaType) {
//        return supports(clazz) && canWrite(mediaType);
//    }

    @Override
    protected void writeInternal(Object outputParamRe, Type type, HttpOutputMessage outputMessage) throws
            IOException, HttpMessageNotWritableException {
        MediaType contentType = outputMessage.getHeaders().getContentType();
        JsonEncoding jsonEncoding = getJsonEncoding(contentType);
        JsonGenerator generator = objectMapper.getFactory().createGenerator(outputMessage.getBody(), jsonEncoding);

        ObjectWriter writer = objectMapper.writer();
        writer.with(prettyPrinter);
        writer.writeValue(generator, outputParamRe);

        generator.flush();
    }


    @Override
    public Object read(Type type, Class<?> contextClass, HttpInputMessage inputMessage) throws
            IOException, HttpMessageNotReadableException {
        JavaType javaType = getJavaType(type, contextClass);
        return readJavaType(javaType, inputMessage);
    }

    @Override
    protected Object readInternal(Class<? extends Object> clazz, HttpInputMessage inputMessage) throws IOException,
            HttpMessageNotReadableException {
        JavaType javaType = getJavaType(clazz, null);
        return readJavaType(javaType, inputMessage);
    }

    private JsonEncoding getJsonEncoding(MediaType contentType) {
        if (null != contentType && contentType.getCharset() != null) {
            Charset charset = contentType.getCharset();
            for (JsonEncoding jsonEncoding : JsonEncoding.values()) {
                if (charset.name().equals(jsonEncoding.name())) {
                    return jsonEncoding;
                }
            }
        }
        return JsonEncoding.UTF8;
    }


    private Object readJavaType(JavaType javaType, HttpInputMessage inputMessage) throws IOException {
        MediaType contentType = inputMessage.getHeaders().getContentType();
        Charset charset = getCharset(contentType);

        ObjectMapper objectMapper = this.objectMapper;
        Assert.state(objectMapper != null, "No ObjectMapper for " + javaType);

        boolean isUnicode = ENCODINGS.containsKey(charset.name()) ||
                "UTF-16".equals(charset.name()) ||
                "UTF-32".equals(charset.name());
        try {
            if (inputMessage instanceof MappingJacksonInputMessage) {
                Class<?> deserializationView = ((MappingJacksonInputMessage) inputMessage).getDeserializationView();
                if (deserializationView != null) {
                    ObjectReader objectReader = objectMapper.readerWithView(deserializationView).forType(javaType);
                    if (isUnicode) {
                        return objectReader.readValue(inputMessage.getBody());
                    } else {
                        Reader reader = new InputStreamReader(inputMessage.getBody(), charset);
                        return objectReader.readValue(reader);
                    }
                }
            }
            if (isUnicode) {
                return objectMapper.readValue(inputMessage.getBody(), javaType);
            } else {
                Reader reader = new InputStreamReader(inputMessage.getBody(), charset);
                return objectMapper.readValue(reader, javaType);
            }
        } catch (InvalidDefinitionException ex) {
            throw new HttpMessageConversionException("Type definition error: " + ex.getType(), ex);
        } catch (JsonProcessingException ex) {
            throw new HttpMessageNotReadableException("JSON parse error: " + ex.getOriginalMessage(), ex, inputMessage);
        }
    }

    protected Charset getCharset(@Nullable MediaType contentType) {
        if (contentType != null && contentType.getCharset() != null) {
            return contentType.getCharset();
        } else {
            return StandardCharsets.UTF_8;
        }
    }

    protected JavaType getJavaType(Type type, @Nullable Class<?> contextClass) {
        return this.objectMapper.constructType(GenericTypeResolver.resolveType(type, contextClass));
    }


}
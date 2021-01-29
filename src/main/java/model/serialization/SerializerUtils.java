package model.serialization;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import model.serialization.exception.SerializeException;
import model.serialization.pojo.ZSetBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SerializerUtils {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public SerializerUtils() {
    }

    public static void main(String[] args) {
        List<ZSetBean> list = new ArrayList();
        list.add((new ZSetBean()).setKey("a").setScore(1L));
        list.add((new ZSetBean()).setKey("b").setScore(2L));
        list.add((new ZSetBean()).setKey("c").setScore(3L));
        String json = serializeAsString(list);
        System.out.println(json);
        JavaType type = getTypeFactory().constructCollectionType(List.class, ZSetBean.class);
        List<ZSetBean> list2 = (List)deserialize((String)json, (JavaType)type);
        System.out.println(list2);
    }

    public static TypeFactory getTypeFactory() {
        return MAPPER.getTypeFactory();
    }

    public static <T> T deserialize(String json, JavaType type) {
        if (null == json) {
            return null;
        } else {
            try {
                return MAPPER.readValue(json, type);
            } catch (IOException var3) {
                return null;
            }
        }
    }

    public static <T> T deserialize(String json, TypeReference<T> valueTypeRef) {
        if (null == json) {
            return null;
        } else {
            try {
                return MAPPER.readValue(json, valueTypeRef);
            } catch (IOException var3) {
                return null;
            }
        }
    }

    public static <T> T deserialize(String json, Class<T> clazz) {
        if (null == json) {
            return null;
        } else {
            try {
                return MAPPER.readValue(json, clazz);
            } catch (IOException var3) {
                return null;
            }
        }
    }

    public static Object deserializeObject(String json, Class<?> clazz) {
        if (null == json) {
            return null;
        } else if (clazz.equals(String.class)) {
            return json;
        } else if (clazz.equals(Integer.class)) {
            return Integer.valueOf(json);
        } else if (clazz.equals(Long.class)) {
            return Long.valueOf(json);
        } else {
            try {
                return MAPPER.readValue(json, clazz);
            } catch (IOException var3) {
                return null;
            }
        }
    }

    public static String serializeAsString(Object t) {
        try {
            return MAPPER.writeValueAsString(t);
        } catch (JsonProcessingException var2) {
            var2.printStackTrace();
            throw new SerializeException("serializeAsString", var2);
        }
    }

    public static byte[] serialize(Object t) {
        try {
            return MAPPER.writeValueAsBytes(t);
        } catch (JsonProcessingException var2) {
            var2.printStackTrace();
            throw new SerializeException("serialize", var2);
        }
    }

    public static <T> T deserialize(byte[] bytes, Class<T> clazz) {
        if (null == bytes) {
            return null;
        } else {
            try {
                return MAPPER.readValue(bytes, clazz);
            } catch (IOException var3) {
                throw new SerializeException("deserialize", var3);
            }
        }
    }

    static {
        MAPPER.setSerializationInclusion(Include.NON_NULL);
        MAPPER.registerModule((new AfterburnerModule()).setUseValueClassLoader(false));
        MAPPER.getSerializerProvider().setNullKeySerializer(new JsonSerializer<Object>() {
            @Override
            public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
                gen.writeFieldName("null");
            }
        });
    }
}

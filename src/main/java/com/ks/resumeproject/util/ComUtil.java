package com.ks.resumeproject.util;

import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Component
public class ComUtil {

    public String addRandomVal(int targetStringLength) {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();

        return random.ints(leftLimit,rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    /**
     * VO 객체인 DTO 내부 값을 추출 후 Map 객체에 담는다.
     * @param output
     * @param map
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public Object putAll(Object output, Map<Object, Object> map) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Map<Object, Object> inputValue = new HashMap<>();

        Field[] fields = output.getClass().getDeclaredFields();

        for(Field field : fields){
            String fieldName = field.getName();
            String methodName = fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);

            Object method = output.getClass().getDeclaredMethod("get" + methodName).invoke(output);

            inputValue.put(fieldName, method);
        }

        inputValue.putAll(map);

        return inputValue;
    }

    /**
     *
     * @param output
     * @param map
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public Object putAll(Object output, Object map) throws Exception {

        Map<Object, Object> inputValue = new HashMap<>();

        for(int i = 0; i < 2; i++){
            Object obj = i == 0 ? output : map;

            Field[] fields = obj.getClass().getDeclaredFields();

            for(Field field : fields){
                String fieldName = field.getName();
                String methodName = fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);

                Object method = obj.getClass().getDeclaredMethod("get" + methodName).invoke(obj);

                inputValue.put(fieldName, method);
            }
        }

        return inputValue;
    }
}

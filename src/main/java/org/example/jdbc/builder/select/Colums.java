package org.example.jdbc.builder.select;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Colums {

    List<String> colums = new ArrayList<>();

    public void extractFields(Object obj, String... fieldNames) {

        List<String> extractedValues = new ArrayList<>();

        try {
            Class<?> clazz = obj.getClass();

            for (String fieldName : fieldNames) {
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                Object value = field.get(obj);
                extractedValues.add(value != null ? value.toString() : null);
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        this.colums =  extractedValues;

    };

    public void getQuery(){
        for( String colum : colums){
            System.out.println(colum);
        }
    }

}

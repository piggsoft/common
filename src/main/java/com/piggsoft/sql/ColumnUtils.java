package com.piggsoft.sql;

import com.piggsoft.mybaits.generator.Plugin;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.ReflectionUtils;

import java.beans.Transient;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;


/**
 * <br>Created by fire pigg on 2016/4/19.
 *
 * @author piggsoft@163.com
 */
public class ColumnUtils {

    private static final String DEFAULT_SEPARATOR = ", ";

    public static String getColumns(Class<?> clazz) {
        List<Field> fields = getAllFields(clazz);
        System.out.println(toString(fields));
        fields = getAllFields(clazz, new FieldsFilter() {
            @Override
            public boolean isExclude(Field field) {
                Transient aTransient = field.getAnnotation(Transient.class);
                return aTransient != null;
            }
        });
       return toString(fields);
    }
    public static List<Field> getAllFields(Class<?> clazz) {
        return getAllFields(clazz, null);
    }

    public static List<Field> getAllFields(Class<?> clazz, final FieldsFilter filter) {
        final List<Field> fields = new ArrayList<>(32);
        ReflectionUtils.doWithFields(clazz, new ReflectionUtils.FieldCallback() {
            @Override
            public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                if (filter != null) {
                    if (filter.isExclude(field) != true) {
                        fields.add(field);
                    }
                } else {
                    fields.add(field);
                }
            }
        });
        return fields;
    }

    public static String toString(List<? extends Member> members) {
        return toString(members, null);
    }

    public static String toString(List<? extends Member> members, String separator) {
        StringBuilder sb = new StringBuilder();
        if (StringUtils.isEmpty(separator)) {
            separator = DEFAULT_SEPARATOR;
        }
        for (int i=0; i<members.size(); i++) {
            if (i > 0) {
                sb.append(separator);
            }
            sb.append(members.get(i).getName());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        getColumns(Plugin.class);
    }

    interface FieldsFilter {
        boolean isExclude(Field field);
    }

}

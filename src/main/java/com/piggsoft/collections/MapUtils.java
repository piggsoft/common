package com.piggsoft.collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


/**
 * <br>Created by fire pigg on 2016/3/31.
 *
 * @author piggsoft@163.com
 */
public class MapUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(MapUtils.class);

    /**
     * 使用 Map按key进行排序
     * @param map
     * @return
     */
    public static <K, V> Map<K, V> sortMapByKey(Map<K, V> map, Comparator<? super K> comparator) {
        if (org.apache.commons.collections.MapUtils.isEmpty(map)) {
            LOGGER.warn("the map is empty");
            return null;
        }
        Map<K, V> sortMap = new TreeMap<>(comparator);
        sortMap.putAll(map);
        return sortMap;
    }

    /**
     * 使用 Map按value进行排序
     * @param map
     * @return
     */
    public static <K, V> Map<K, V> sortMapByValue(Map<K, V> map, Comparator<? super Map.Entry<K, V>> comparator) {
        if (org.apache.commons.collections.MapUtils.isEmpty(map)) {
            LOGGER.warn("the map is empty");
            return null;
        }
        Map<K, V> sortedMap = new LinkedHashMap<>();
        List<Map.Entry<K, V>> entryList = new ArrayList<>(map.entrySet());
        Collections.sort(entryList, comparator);
        Iterator<Map.Entry<K, V>> iter = entryList.iterator();
        Map.Entry<K, V> tmpEntry;
        while (iter.hasNext()) {
            tmpEntry = iter.next();
            sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
        }
        return sortedMap;
    }


}

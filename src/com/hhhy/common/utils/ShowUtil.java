package com.hhhy.common.utils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * Created by lenovo on 8/19/2014.
 */
public class ShowUtil {
    private static final Logger logger = Logger.getLogger(ShowUtil.class);

    /*
     * dimAna_dataSource & mediaSource
     */
    public static String dimAna_dataSourceRoundData(
            Map<String, Integer> sourceStatis) {
        if (sourceStatis == null || sourceStatis.size() == 0)
            return "";
        // String roundData =
        // "['Firefox',   45.0],['IE',       26.8],['Chrome',   12.8],['Safari',    8.5],['Opera',     6.2],['Others',   0.7]";
        StringBuilder sb = new StringBuilder();
        for (String key : sourceStatis.keySet()) {
            sb.append("['" + key + "'," + sourceStatis.get(key) + "],");
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }

    public static String dimAna_dataSourceZhuSource(
            Map<String, Integer> sourceStatis) {
        // String zhuSource =
        // "'Africa', 'America', 'Asia', 'Europe', 'Oceania'";
        if (sourceStatis == null || sourceStatis.size() == 0)
            return "";
        StringBuilder sb = new StringBuilder();
        for (String key : sourceStatis.keySet()) {
            sb.append("'" + key + "',");
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }

    public static String dimAna_dataSourceZhuData(
            Map<String, Integer> sourceStatis) {
        // String zhuData="107, 31, 635, 203, 2";
        if (sourceStatis == null || sourceStatis.size() == 0)
            return "";
        StringBuilder sb = new StringBuilder();
        for (String key : sourceStatis.keySet()) {
            sb.append(sourceStatis.get(key) + ",");
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }

    /*
     * dimAna_emotionTrend & dimAna_sentiTrend
     */
    public static String dimAna_trendDates(List<String> dates) {
        /*
         * String dates =
         * "'2014-8-1','2014-8-2','2014-8-3','2014-8-4','2014-8-5'";
         */
        if (dates == null || dates.size() == 0)
            return "";
        StringBuilder sb = new StringBuilder();
        for (String date : dates) {
            sb.append("'" + date + "',");
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }

    public static String dimAna_trendPostTrend(List<Integer> dates) {
        /* String postEmotionTrend = "1,4,3,2,5"; */
        if (dates == null || dates.size() == 0)
            return "";
        StringBuilder sb = new StringBuilder();
        for (Integer date : dates) {
            sb.append(date + ",");
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }
    public static String dimAna_trendPostTrend2(List<Double> dates) {
        /* String postEmotionTrend = "1,4,3,2,5"; */
        if (dates == null || dates.size() == 0)
            return "";
        StringBuilder sb = new StringBuilder();
        for (Double date : dates) {
            sb.append(date + ",");
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }
    public static String dimAna_emotionTrend(List<Integer> post,List<Integer> neg) {
    	if(post == null || neg == null) return "";
    	if(post.size() != neg.size()){
    		return "";
    	}else if(post.size() == 0) {
    		return "";
    	}
    	List<Double> emotion = new LinkedList<Double>();
    	for(int i = 0 ;i < post.size();i++){
    		emotion.add(5*(post.get(i) - neg.get(i) - 0.0)/(post.get(i) + neg.get(i)));
    	}
    	return dimAna_trendPostTrend2(emotion);
    }

    public static void main(String[] args) {
        // String a = "aaa";
        // System.out.println(a.substring(0,a.length()-1));
        Map<String, Integer> test = new HashMap<String, Integer>();
        test.put("aaa", 12);
        test.put("bbb", 14);
        test.put("ccc", 17);
        test.put("ddd", 19);
        logger.info(dimAna_dataSourceRoundData(test));
        logger.info(dimAna_dataSourceZhuSource(test));
        logger.info(dimAna_dataSourceZhuData(test));
        List<String> test1 = new LinkedList<String>();
        test1.add("111");
        test1.add("222");
        test1.add("e33");
        logger.info(dimAna_trendDates(test1));

        List<Integer> test2 = new LinkedList<Integer>();
        test2.add(111);
        test2.add(222);
        logger.info(dimAna_trendPostTrend(null));

    }
}
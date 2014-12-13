package com.hhhy.common.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import org.apache.log4j.Logger;
import org.nlpcn.commons.lang.util.IOUtil;

public class StopWordsUtils {
    private static Logger logger = Logger.getLogger(StopWordsUtils.class);

    private static Set<String> stopWords;

    static {
        init();
    }

    private static void init() {
        logger.info("loading stop words...");
        try {
            stopWords = new HashSet<String>();
            BufferedReader reader2 = IOUtil.getReader(ResourceBundle.getBundle(
                    "library").getString("stopLibrary"), "UTF-8");
            String word = null;
            while ((word = reader2.readLine()) != null) {
                stopWords.add(word);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("stop words loaded...");
    }
    
    public static Set<String> getStopWords(){
        return stopWords;
    }

}

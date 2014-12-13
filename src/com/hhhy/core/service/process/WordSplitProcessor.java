package com.hhhy.core.service.process;

import java.util.List;

import com.hhhy.common.utils.WordSplitUtils;

public class WordSplitProcessor {
    public static List<String> split(String sentence){
        return WordSplitUtils.splitWords(sentence);
    }
}

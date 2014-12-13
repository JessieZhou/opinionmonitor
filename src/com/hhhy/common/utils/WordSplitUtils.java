package com.hhhy.common.utils;

import java.util.ArrayList;
import java.util.List;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;

public class WordSplitUtils {

    public static List<String> splitWords(String sentence) {
        List<Term> terms = null;
        terms = ToAnalysis.parse(sentence);
        List<String> words = new ArrayList<String>();
        for (Term term : terms) {
            words.add(term.getName());
        }
        return words;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out
                .println(splitWords("这两个jar的区别，标准版本。比简介版本。大的多的多。标准版一般是40m左右。简介版大约是4m左右。"));
    }

}

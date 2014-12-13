package com.hhhy.common.utils;

import java.util.Set;

import com.hhhy.db.beans.EmotionWord;

public class EmotionUtils {
    private static Set<EmotionWord> emotionwords= null;
    static{
        loadEmotionWords();
    }
    
    private static void loadEmotionWords(){
        
    }
}

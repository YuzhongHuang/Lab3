package com.example.yhuang.scavengerhunt.Database;

import java.util.Map;

/**
 * Created by siyer on 10/17/2015.
 */
public interface CallbackInterface {
    void resultsCallback(Map<Integer, ClueRow.Row> urlMap);
}
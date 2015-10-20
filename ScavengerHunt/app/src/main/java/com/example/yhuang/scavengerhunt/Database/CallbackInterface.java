package com.example.yhuang.scavengerhunt.Database;

import java.util.Map;

public interface CallbackInterface {
    void resultsCallback(Map<Integer, ClueRow.Row> urlMap);
}
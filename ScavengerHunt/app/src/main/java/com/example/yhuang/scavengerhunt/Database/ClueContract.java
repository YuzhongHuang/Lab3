package com.example.yhuang.scavengerhunt.Database;

import android.provider.BaseColumns;

/**
 * Schema for the database
 */
public class ClueContract {
    public ClueContract(){}

    public abstract class ClueTable implements BaseColumns {
        public static final String TABLE_NAME = "Cool_Rabbit_Photos";

        public static final String VIDEO_COLUMN = "Video_UUID";
        public static final String PHOTO_COLUMN = "Photo";
        public static final String PLACE_ID = "_id";

        public static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + ClueTable.TABLE_NAME +
                " (" + ClueTable.PLACE_ID + " INTEGER PRIMARY KEY," + ClueTable.VIDEO_COLUMN + " TEXT, "+ ClueTable.PHOTO_COLUMN + " TEXT )";

        public static final String SQL_DELETE_ENTRIES = "DELETE TABLE IF EXISTS " + ClueTable.TABLE_NAME;
    }
}

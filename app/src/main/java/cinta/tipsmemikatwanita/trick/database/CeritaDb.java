package cinta.tipsmemikatwanita.trick.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import cinta.tipsmemikatwanita.trick.model.Cerita;
import cinta.tipsmemikatwanita.trick.model.CeritaWrapper;
import cinta.tipsmemikatwanita.trick.util.Debug;

/**
 * Created by asywalulfikri on 12/15/16.
 */

public class CeritaDb extends Database {


    public CeritaDb(SQLiteDatabase sqlite) {
        super(sqlite);
    }

    public boolean exist(String id) {
        boolean result = false;

        if (mSqLite == null || !mSqLite.isOpen()) {
            return false;
        }

        String sql 	= "SELECT * FROM articles WHERE id ='"+id+"'";

        Cursor c	= mSqLite.rawQuery(sql, null);

        if (c != null) {
            result = (c.getCount() == 0) ? false : true;

            c.close();
        }

        return result;
    }

    public void delete(Cerita cerita) {
        if (mSqLite == null || !mSqLite.isOpen()) {
            return;
        }

        Debug.i("Updating cerita ");

        ContentValues values = new ContentValues();

        values.put("id", cerita.id);

        if (exist(cerita.id)) {
            mSqLite.delete("articles", "id = '" + cerita.id + "'", null);
        } else {
            mSqLite.delete("articles","id = '" + cerita.id + "'", null);
        }
    }

    public void update(Cerita cerita, String bookmark) {
        if (mSqLite == null || !mSqLite.isOpen()) {
            return;
        }

        Debug.i("Updating cerita ");

        ContentValues values = new ContentValues();

        values.put("cid",       bookmark);
        values.put("id",    	cerita.id);
        values.put("title",	    cerita.title);
        values.put("content",   cerita.content);
        values.put("type",		cerita.type);
        values.put("createdAt",	cerita.createdAt);
        values.put("image",     cerita.image);
        values.put("sumber",	cerita.sumber);
        values.put("category",  cerita.category);
        values.put("person",    cerita.person);

        if (exist(cerita.id)) {
            mSqLite.update("articles", values, "id = '" + cerita.id + "'", null);
        } else {
            mSqLite.insert("articles", null, values);
        }
    }


    public CeritaWrapper getList(String type) {
        CeritaWrapper wrapper = null;

        if (mSqLite == null || !mSqLite.isOpen()) {
            return wrapper;
        }

        String sql	= "SELECT * FROM articles WHERE cid = '" + type +"'";

        Cursor c 	= mSqLite.rawQuery(sql, null);

        Debug.i(sql);

        if (c != null) {

            if (c.moveToFirst()) {
                wrapper 	 = new CeritaWrapper();

                wrapper.list 			= new ArrayList<Cerita>();


                while (c.isAfterLast()  == false) {
                    Cerita cerita	= new Cerita();


                    cerita.id 			= c.getString(c.getColumnIndex("id"));
                    cerita.title 		= c.getString(c.getColumnIndex("title"));
                    cerita.content 	    = c.getString(c.getColumnIndex("content"));
                    cerita.type 		= c.getString(c.getColumnIndex("type"));
                    cerita.createdAt 	= c.getString(c.getColumnIndex("createdAt"));
                    cerita.image 		= c.getString(c.getColumnIndex("image"));
                    cerita.sumber 		= c.getString(c.getColumnIndex("sumber"));
                    cerita.category     = c.getString(c.getColumnIndex("category"));
                    cerita.person       = c.getString(c.getColumnIndex("person"));

                    //Debug.i(conv.type);

                    wrapper.list.add(cerita);

                    c.moveToNext();
                }
            }

            c.close();
        }

        return wrapper;
    }

    public CeritaWrapper getListBookmark() {
        CeritaWrapper wrapper = null;

        if (mSqLite == null || !mSqLite.isOpen()) {
            return wrapper;
        }

        String sql	= "SELECT * FROM articles order by createdAt DESC";

        Cursor c 	= mSqLite.rawQuery(sql, null);

        Debug.i(sql);

        if (c != null) {

            if (c.moveToFirst()) {
                wrapper 	 = new CeritaWrapper();

                wrapper.list 			= new ArrayList<Cerita>();


                while (c.isAfterLast()  == false) {
                    Cerita cerita	= new Cerita();


                    cerita.id 			= c.getString(c.getColumnIndex("id"));
                    cerita.title 		= c.getString(c.getColumnIndex("title"));
                    cerita.content 	    = c.getString(c.getColumnIndex("content"));
                    cerita.type 		= c.getString(c.getColumnIndex("type"));
                    cerita.createdAt 	= c.getString(c.getColumnIndex("createdAt"));
                    cerita.image 		= c.getString(c.getColumnIndex("image"));
                    cerita.sumber 		= c.getString(c.getColumnIndex("sumber"));
                    cerita.category     = c.getString(c.getColumnIndex("category"));
                    cerita.person       = c.getString(c.getColumnIndex("person"));

                    //Debug.i(conv.type);

                    wrapper.list.add(cerita);

                    c.moveToNext();
                }
            }

            c.close();
        }

        return wrapper;
    }


}

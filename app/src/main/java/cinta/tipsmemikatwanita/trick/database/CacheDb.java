package cinta.tipsmemikatwanita.trick.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import cinta.tipsmemikatwanita.trick.model.Cerita;
import cinta.tipsmemikatwanita.trick.model.CeritaWrapper;
import cinta.tipsmemikatwanita.trick.util.Debug;


public class CacheDb extends Database {

	public CacheDb(SQLiteDatabase sqlite) {
		super(sqlite);
	}

	public void update(ArrayList<Cerita> list,String cid) {
		if (mSqLite == null || !mSqLite.isOpen()) {
			return;
		}

		if (list == null) {
			return;
		}

		Debug.i("Updating cache ");

		mSqLite.delete("cache_articles", "cid = '" + cid + "'", null);

		int size = list.size();

		for (int i = 0; i < size; i++){
			Cerita cerita	= list.get(i);
			ContentValues values 	= new ContentValues();

			values.put("cid",       cid);
			values.put("id",    	cerita.id);
			values.put("title",	    cerita.title);
			values.put("content",   cerita.content);
			values.put("type",		cerita.type);
			values.put("createdAt",	cerita.createdAt);
			values.put("image",    cerita.image);
			values.put("sumber",	cerita.sumber);
			values.put("category",  cerita.category);
			values.put("person", cerita.person);

			mSqLite.insert("cache_articles", null, values);
		}
	}


	public CeritaWrapper getListCerita(String id) {
		CeritaWrapper wrapper = null;
		wrapper 	 = new CeritaWrapper();
		wrapper.list = new ArrayList<Cerita>();

		if (mSqLite == null || !mSqLite.isOpen()) {
			return wrapper;
		}

		String sql	= "SELECT * FROM cache_articles WHERE cid  = '"+id+"'";
		Cursor c 	= mSqLite.rawQuery(sql, null);

		Debug.i(sql);

		if (c != null) {

			if (c.moveToFirst()) {
				wrapper 	 = new CeritaWrapper();

				wrapper.list 			= new ArrayList<Cerita>();

				while (c.isAfterLast()  == false) {
					Cerita cerita = new Cerita();

					cerita.id 			= c.getString(c.getColumnIndex("id"));
					cerita.title 		= c.getString(c.getColumnIndex("title"));
					cerita.content 	    = c.getString(c.getColumnIndex("content"));
					cerita.type 		= c.getString(c.getColumnIndex("type"));
					cerita.createdAt 	= c.getString(c.getColumnIndex("createdAt"));
					cerita.image 		= c.getString(c.getColumnIndex("image"));
					cerita.sumber 		= c.getString(c.getColumnIndex("sumber"));
					cerita.category     = c.getString(c.getColumnIndex("category"));
					cerita.person       = c.getString(c.getColumnIndex("person"));


					Debug.i("From db id  " + cerita.id);

					wrapper.list.add(cerita);

					c.moveToNext();
				}
			}

			c.close();
		}

		return wrapper;
	}
}

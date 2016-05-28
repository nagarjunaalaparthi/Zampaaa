package com.zampaaa.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.zampaaa.Model.Item;

import java.util.ArrayList;

/**
 * Created by Softapt on 28/05/2016.
 */
public class DBHelper extends SQLiteOpenHelper {

    String itemName = "name";
    String itemPrice = "price";
    String category = "category";
    String vegType = "type";

    SQLiteDatabase db = null;

    private String TABLE_NAME = "itemtable";
    private String CREATE_TABLE = "create table " + TABLE_NAME + "( _id  INTEGER PRIMARY KEY," + itemName
            + " TEXT," + itemPrice + " TEXT," + category
            + " TEXT," + vegType + " TEXT" + ")";

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void insertItem(Item item) {
        ContentValues cv = new ContentValues();
        cv.put(itemName, item.getName());
        cv.put(itemPrice, item.getPrice());
        cv.put(category, item.getCategory());
        cv.put(vegType, item.getVegType());
        db = this.getWritableDatabase();
        db.insert(TABLE_NAME, null, cv);
        db.close();
    }

    public int UpdateItem(Item item) {
        ContentValues cv = new ContentValues();
        cv.put(itemName, item.getName());
        cv.put(itemPrice, item.getPrice());
        cv.put(category, item.getCategory());
        cv.put(vegType, item.getVegType());
        db = this.getWritableDatabase();
        int update = db.update(TABLE_NAME, cv, "_id=?", new String[]{item.getId()});
        db.close();
        return update;
    }

    public int deleteItem(String id){
        db = this.getWritableDatabase();
        int delete = db.delete(TABLE_NAME,  "_id=?", new String[]{id});
        db.close();
        return delete;
    }

    public ArrayList<Item> getItems() {
        ArrayList<Item> items = new ArrayList<>();
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            for(int i = 0;i<cursor.getCount();i++ ){
                cursor.moveToPosition(i);
                Item item = new Item();
                item.setCategory(cursor.getString(cursor.getColumnIndex(category)));
                item.setName(cursor.getString(cursor.getColumnIndex(itemName)));
                item.setPrice(cursor.getString(cursor.getColumnIndex(itemPrice)));
                item.setVegType(cursor.getString(cursor.getColumnIndex(vegType)));
                item.setId(cursor.getString(cursor.getColumnIndex("_id")));
                items.add(item);
            }
        }
        return items;
    }

    public Item getItem(String id) {
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " where _id = ?", new String[]{id});
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            Item item = new Item();
            item.setCategory(cursor.getString(cursor.getColumnIndex(category)));
            item.setName(cursor.getString(cursor.getColumnIndex(itemName)));
            item.setPrice(cursor.getString(cursor.getColumnIndex(itemPrice)));
            item.setVegType(cursor.getString(cursor.getColumnIndex(vegType)));
            item.setId(cursor.getString(cursor.getColumnIndex("_id")));
            return item;
        }
        return null;
    }
}

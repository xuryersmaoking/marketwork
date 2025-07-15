package com.example.marketwork;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "marketwork.db";
    private static final int DATABASE_VERSION = 2;

    // Users table
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";

    // Products table
    private static final String TABLE_PRODUCTS = "products";
    private static final String COLUMN_PRODUCT_ID = "_id";
    private static final String COLUMN_PRODUCT_NAME = "name";
    private static final String COLUMN_PRODUCT_DESCRIPTION = "description";
    private static final String COLUMN_PRODUCT_PRICE = "price";
    private static final String COLUMN_PRODUCT_IMAGE_RES_ID = "image_res_id";

    // Addresses table
    private static final String TABLE_ADDRESSES = "addresses";
    private static final String COLUMN_ADDRESS_ID = "_id";
    private static final String COLUMN_ADDRESS_USER_ID = "user_id";
    private static final String COLUMN_ADDRESS_DETAILS = "address_details";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create users table
        String CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_USERS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_USERNAME + " TEXT NOT NULL,"
                + COLUMN_PASSWORD + " TEXT NOT NULL)";
        db.execSQL(CREATE_TABLE_USERS);

        // Create products table
        String CREATE_TABLE_PRODUCTS = "CREATE TABLE " + TABLE_PRODUCTS + "("
                + COLUMN_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_PRODUCT_NAME + " TEXT NOT NULL,"
                + COLUMN_PRODUCT_DESCRIPTION + " TEXT NOT NULL,"
                + COLUMN_PRODUCT_PRICE + " REAL NOT NULL,"
                + COLUMN_PRODUCT_IMAGE_RES_ID + " INTEGER NOT NULL)";
        db.execSQL(CREATE_TABLE_PRODUCTS);

        // Create addresses table
        String CREATE_TABLE_ADDRESSES = "CREATE TABLE " + TABLE_ADDRESSES + "("
                + COLUMN_ADDRESS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_ADDRESS_USER_ID + " INTEGER NOT NULL,"
                + COLUMN_ADDRESS_DETAILS + " TEXT NOT NULL,"
                + "FOREIGN KEY (" + COLUMN_ADDRESS_USER_ID + ") REFERENCES " + TABLE_USERS + "(" + COLUMN_ID + "))";
        db.execSQL(CREATE_TABLE_ADDRESSES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            // Add products and addresses tables
            String CREATE_TABLE_PRODUCTS = "CREATE TABLE " + TABLE_PRODUCTS + "("
                    + COLUMN_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_PRODUCT_NAME + " TEXT NOT NULL,"
                    + COLUMN_PRODUCT_DESCRIPTION + " TEXT NOT NULL,"
                    + COLUMN_PRODUCT_PRICE + " REAL NOT NULL,"
                    + COLUMN_PRODUCT_IMAGE_RES_ID + " INTEGER NOT NULL)";
            db.execSQL(CREATE_TABLE_PRODUCTS);

            String CREATE_TABLE_ADDRESSES = "CREATE TABLE " + TABLE_ADDRESSES + "("
                    + COLUMN_ADDRESS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_ADDRESS_USER_ID + " INTEGER NOT NULL,"
                    + COLUMN_ADDRESS_DETAILS + " TEXT NOT NULL,"
                    + "FOREIGN KEY (" + COLUMN_ADDRESS_USER_ID + ") REFERENCES " + TABLE_USERS + "(" + COLUMN_ID + "))";
            db.execSQL(CREATE_TABLE_ADDRESSES);
        }
    }

    // User methods
    public boolean addUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_PASSWORD, password);
        long result = db.insert(TABLE_USERS, null, values);
        db.close();
        return result != -1;
    }

    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID};
        String selection = COLUMN_USERNAME + "=? AND " + COLUMN_PASSWORD + "=?";
        String[] selectionArgs = {username, password};

        Cursor cursor = db.query(TABLE_USERS, columns, selection, selectionArgs, null, null, null);
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return exists;
    }

    // Product methods
    public boolean addProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCT_NAME, product.getName());
        values.put(COLUMN_PRODUCT_DESCRIPTION, product.getDescription());
        values.put(COLUMN_PRODUCT_PRICE, product.getPrice());
        values.put(COLUMN_PRODUCT_IMAGE_RES_ID, product.getImageResId());
        long result = db.insert(TABLE_PRODUCTS, null, values);
        db.close();
        return result != -1;
    }

    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_PRODUCTS;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_NAME));
                String description = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_DESCRIPTION));
                double price = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_PRICE));
                int imageResId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_IMAGE_RES_ID));

                Product product = new Product(name, description, price, imageResId);
                productList.add(product);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return productList;
    }

    // Address methods
    public boolean addAddress(int userId, String addressDetails) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ADDRESS_USER_ID, userId);
        values.put(COLUMN_ADDRESS_DETAILS, addressDetails);
        long result = db.insert(TABLE_ADDRESSES, null, values);
        db.close();
        return result != -1;
    }

    public List<Address> getAddressesByUserId(int userId) {
        List<Address> addressList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_ADDRESSES + " WHERE " + COLUMN_ADDRESS_USER_ID + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(userId)});

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ADDRESS_ID));
                String details = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ADDRESS_DETAILS));

                Address address = new Address(id, userId, details);
                addressList.add(address);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return addressList;
    }

    public boolean deleteAddressById(int addressId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_ADDRESSES, COLUMN_ADDRESS_ID + " = ?", new String[]{String.valueOf(addressId)});
        db.close();
        return result > 0;
    }
}






















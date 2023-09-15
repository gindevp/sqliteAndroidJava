package com.example.shopbaefood;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ContactDataSource {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public ContactDataSource(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void addContact(Contact contact) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NAME, contact.getName());
        values.put(DatabaseHelper.COLUMN_PHONE, contact.getPhone());

        database.insert(DatabaseHelper.TABLE_CONTACTS, null, values);
    }

    public List<Contact> getAllContacts() {
        List<Contact> contacts = new ArrayList<>();
        Cursor cursor = database.query(
                DatabaseHelper.TABLE_CONTACTS, null, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Contact contact = cursorToContact(cursor);
            contacts.add(contact);
            cursor.moveToNext();
        }
        cursor.close();
        return contacts;
    }

    private Contact cursorToContact(Cursor cursor) {
        int idIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_ID);
        int nameIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME);
        int phoneIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_PHONE);

        int id = cursor.getInt(idIndex);
        String name = cursor.getString(nameIndex);
        String phone = cursor.getString(phoneIndex);

        return new Contact(id, name, phone);
    }
}

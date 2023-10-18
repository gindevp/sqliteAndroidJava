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
    private String[][] columns = {{"name", "TEXT"}, {"phone", "TEXT"}};

    public ContactDataSource(Context context) {
        dbHelper = new DatabaseHelper(context,"contacts.db",1,"contacts",columns);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void addContact(Contact contact) {
        ContentValues values = new ContentValues();
        values.put("name", contact.getName());
        values.put("phone", contact.getPhone());

        database.insert("contacts", null, values);
    }

    public List<Contact> getAllContacts() {
        List<Contact> contacts = new ArrayList<>();
        Cursor cursor = database.query(
                "contacts", null, null, null, null, null, null);

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
        int idIndex = cursor.getColumnIndex("id");
        int nameIndex = cursor.getColumnIndex("name");
        int phoneIndex = cursor.getColumnIndex("phone");

        int id = cursor.getInt(idIndex);
        String name = cursor.getString(nameIndex);
        String phone = cursor.getString(phoneIndex);

        return new Contact(id, name, phone);
    }
}

package com.example.shopbaefood;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Tạo đối tượng Contact
        Contact contact1 = new Contact(0, "Nguyễn Văn A", "0123456789");
        Contact contact2 = new Contact(0, "Trần Thị B", "0987654321");

// Mở cơ sở dữ liệu
        ContactDataSource dataSource = new ContactDataSource(this);
        dataSource.open();

// Thêm liên hệ
        dataSource.addContact(contact1);
        dataSource.addContact(contact2);

// Lấy danh sách liên hệ
        List<Contact> contacts = dataSource.getAllContacts();
        for (Contact a: contacts
             ) {
            Log.d("database", a.toString());
        }

// Đóng cơ sở dữ liệu khi không cần thiết
        dataSource.close();
    }
}
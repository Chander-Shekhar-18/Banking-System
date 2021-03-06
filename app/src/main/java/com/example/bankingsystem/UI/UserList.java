package com.example.bankingsystem.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.bankingsystem.DB.UserContract;
import com.example.bankingsystem.DB.UserHelper;
import com.example.bankingsystem.ListAdapters.UserListAdapter;
import com.example.bankingsystem.R;
import com.example.bankingsystem.data.User;

import java.util.ArrayList;

public class UserList extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter myAdapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<User> userArrayList;
    private UserHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        findViewById(R.id.allUserBackBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        TextView textView = findViewById(R.id.txtViewAllUserTitle);
        textView.setText("All Users");


        // Create ArrayList of Users
        userArrayList = new ArrayList<User>();

        // Create Table in the Database
        dbHelper = new UserHelper(this);

        // Read Data from DataBase
        displayDatabaseInfo();

        // Show list of items
        recyclerView = findViewById(R.id.all_users_list);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        myAdapter = new UserListAdapter(this, userArrayList);
        recyclerView.setAdapter(myAdapter);

    }

    private void displayDatabaseInfo() {
        userArrayList.clear();

        Cursor cursor = new UserHelper(this).readAllData();

        int phoneNoColumnIndex = cursor.getColumnIndex(UserContract.UserEntry.COLUMN_USER_PHONE_NO);
        int emailColumnIndex = cursor.getColumnIndex(UserContract.UserEntry.COLUMN_USER_EMAIL);
        int ifscCodeColumnIndex = cursor.getColumnIndex(UserContract.UserEntry.COLUMN_USER_IFSC_CODE);
        int accountNumberColumnIndex = cursor.getColumnIndex(UserContract.UserEntry.COLUMN_USER_ACCOUNT_NUMBER);
        int nameColumnIndex = cursor.getColumnIndex(UserContract.UserEntry.COLUMN_USER_NAME);
        int accountBalanceColumnIndex = cursor.getColumnIndex(UserContract.UserEntry.COLUMN_USER_ACCOUNT_BALANCE);

        while (cursor.moveToNext()) {
            String currentName = cursor.getString(nameColumnIndex);
            int accountNumber = cursor.getInt(accountNumberColumnIndex);
            String email = cursor.getString(emailColumnIndex);
            String phoneNumber = cursor.getString(phoneNoColumnIndex);
            String ifscCode = cursor.getString(ifscCodeColumnIndex);
            int accountBalance = cursor.getInt(accountBalanceColumnIndex);

            // Display the values from each column of the current row in the cursor in the TextView
            userArrayList.add(new User(currentName, accountNumber, phoneNumber, ifscCode, accountBalance, email));
        }
    }
}
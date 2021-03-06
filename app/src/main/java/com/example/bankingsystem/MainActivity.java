package com.example.bankingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bankingsystem.UI.TransactionHistory;
import com.example.bankingsystem.UI.UserList;

public class MainActivity extends AppCompatActivity {
    ImageView bankLogo;
    TextView tvBankTitle;
    Button btnAllUsers, btnAllTransactions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

//        findViewById(R.id.mainBackBtn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });

        TextView textView = findViewById(R.id.txtViewMainTitle);
        textView.setText("Banking System");
    }

    private void initViews() {
        btnAllUsers = findViewById(R.id.all_users);
        btnAllTransactions = findViewById(R.id.all_transactions);
    }

    public void showAllUsers(View view) {
        Intent intent = new Intent(this, UserList.class);
        startActivity(intent);
    }

    public void showAllTransactions(View view) {
        Intent intent = new Intent(this, TransactionHistory.class);
        startActivity(intent);
    }
}
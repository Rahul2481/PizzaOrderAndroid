package com.example.prjusingmanyobjects;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

import model.ClientOrder;

public class SecondActivity extends AppCompatActivity {

    TextView tvOrder, tvTotal;
    ArrayList<ClientOrder> listOfOrders;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initialize();
    }

    private void initialize() {
        tvOrder = findViewById(R.id.tvOrder);
        tvTotal = findViewById(R.id.tvTotal);
        listOfOrders =(ArrayList<ClientOrder>) getIntent().getExtras().getSerializable("order");

        StringBuilder list = new StringBuilder("");
        float total =0;

        for (ClientOrder oneOrder : listOfOrders){
            list.append(oneOrder+ "\n");
            total +=oneOrder.getAmount();
        }

        tvOrder.setText(list);
        tvTotal.setText(String.valueOf(total));

    }
}
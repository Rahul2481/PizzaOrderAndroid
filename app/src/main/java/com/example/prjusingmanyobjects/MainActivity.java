package com.example.prjusingmanyobjects;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import model.ClientOrder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {

    TextView tvAmount;
    EditText edClNumber, edNbSlices;
    RadioGroup rgPizza;
    Button btnSave, btnQuit, btnShowAll;
    ArrayList<ClientOrder> listOfOrders;
    RadioButton rbCheese, rbVegetarian, rbMexican;
    String pizzaType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize() {
        tvAmount = findViewById(R.id.tvAmount);
        edClNumber = findViewById(R.id.edClNumber);
        edNbSlices = findViewById(R.id.edNbSlices);
        rgPizza = findViewById(R.id.rgPizza);

        btnSave = findViewById(R.id.btnSave);
        btnQuit = findViewById(R.id.btnQuit);
        btnShowAll = findViewById(R.id.btnShowAll);

        btnSave.setOnClickListener(this);
        btnQuit.setOnClickListener(this);
        btnShowAll.setOnClickListener(this);

        listOfOrders = new ArrayList<ClientOrder>();

        rbCheese = findViewById(R.id.rbCheese);
        rbVegetarian = findViewById(R.id.rbVegetarian);
        rbMexican = findViewById(R.id.rbMexican);

        rbCheese.setOnClickListener(this);
        rbVegetarian.setOnClickListener(this);
        rbMexican.setOnClickListener(this);

        edNbSlices.addTextChangedListener(this);

    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.btnSave:
                saveOrder(view);
                ;break;
            case R.id.btnQuit:
                quitApp();
                ;break;
            case  R.id.btnShowAll:
                showAllOrders();
                ;break;
            case R.id.rbCheese:
                pizzaType = rbCheese.getText().toString();
                showAmount();
                ;break;
            case R.id.rbVegetarian:
                pizzaType = rbVegetarian.getText().toString();
                showAmount();
                ;break;
            case R.id.rbMexican:
                pizzaType = rbMexican.getText().toString();
                showAmount();
                ;break;


        }
    }

    private void showAllOrders() {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("order", listOfOrders);
        startActivity(intent);
    }

    private void quitApp() {
        System.exit(0);
    }

    private void showAmount() {
        try {
            float price = getPrice(pizzaType);
            int nbSlices = Integer.valueOf(edNbSlices.getText().toString());
            float amount = price * nbSlices;
            tvAmount.setText(amount + "");
        }
        catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private float getPrice(String pizzaType) throws Exception {
        float price;
        if (pizzaType.equals("Vegetarian"))
            price = 2.5f;
        else
            if (pizzaType.equals("Mexican"))
                price = 2.4f;
            else
                if (pizzaType.equals("Cheese"))
                    price = 2.0f;
                else
                    throw new Exception("Please select a pizza");
        return price;
    }

    private void saveOrder(View view) {
    try {
        int clNumber = Integer.valueOf(edClNumber.getText().toString());
        int nbSlices = Integer.valueOf(edNbSlices.getText().toString());
        ClientOrder oneOrder = new ClientOrder(clNumber, pizzaType, nbSlices);
        listOfOrders.add(oneOrder);
        Snackbar.make(view, "The order of the client Number :" + clNumber +"is saved successfully", Snackbar.LENGTH_LONG).show();

        clearWidgets();
    }
    catch (Exception e){
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
    }
    }

    private void clearWidgets() {
        edClNumber.setText(null);
        edNbSlices.setText(null);
        rgPizza.clearCheck();
        tvAmount.setText(null);
        edClNumber.requestFocus();

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        showAmount();
    }
}
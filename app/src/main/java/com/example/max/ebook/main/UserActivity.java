package com.example.max.ebook.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.max.ebook.R;

import java.util.ArrayList;

/**
 * Created by Max on 3/6/2560.
 */

public class UserActivity extends AppCompatActivity implements UserView {
    ListView resultListView;
    TextView money;
    UserPresenter presenter = null;
    Button purchaseBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        initViewHolders();

        if(presenter == null) {
            presenter = new UserPresenter(this,getIntent());
        }

    }

    private void initViewHolders() {
        resultListView = (ListView) findViewById(R.id.listProfile);
        money = (TextView) findViewById(R.id.money);
        purchaseBtn = (Button) findViewById(R.id.purchaseButton);
    }
    public void onRefill(View view) {
        EditText editText = (EditText)findViewById(R.id.refillText);
        double amount = Double.parseDouble(editText.getText().toString());
        presenter.refill(amount);
    }
    public void onMain(View view) {
        Intent intent = new Intent(UserActivity.this, MainActivity.class);

        ArrayList<String> collection = presenter.user.getCollectionList();

        intent.putExtra("collectionSize", collection.size()+"");
        intent.putExtra("wallet", presenter.getMoney()+"");
        for (int i = 0;i<collection.size();i++) {
            intent.putExtra("collection"+i,collection.get(i));
        }
        startActivity(intent);
        finish();
    }

    @Override
    public void updateMoney(double amount) {
        money.setText("Money: "+ amount + " $");
    }

    @Override
    public void updateAll(ArrayList<String> books) {
        String[] list = new String[books.size()];

        for (int i = 0;i<books.size();i++) {
            list[i] = books.get(i);
        }
        ArrayAdapter<String> adapter =  new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        resultListView.setAdapter(adapter);

    }

    public void onOrder(View view) {
        presenter.showCart();
        purchaseBtn.setVisibility(View.VISIBLE);
    }

    public void onCollection(View view) {
        presenter.showCollection();
        purchaseBtn.setVisibility(View.INVISIBLE);
    }

    public void onPurchase(View view) {
        presenter.purchase();
    }
}

package com.example.max.ebook.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.max.ebook.R;
import com.example.max.ebook.data.Book;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements  BookView{

    BookPresenter presenter = null;
    ListView resultListView;
    Spinner spin;
    String state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViewHolders();

        if(presenter == null) {
            presenter = new BookPresenter(this);
        }

        presenter.listAllBook();

        spin = (Spinner)findViewById(R.id.spin);

        ArrayList<String> choices = new ArrayList<String>();
        choices.add("by Title");
        choices.add("by Published year");

        ArrayAdapter spinAdapter =  new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, choices);
        spin.setAdapter(spinAdapter);

        createUser();

    }
    private void createUser(){
        if(getIntent().getExtras() != null) {
            Intent intent = getIntent();
            int collectionSize = Integer.parseInt(intent.getStringExtra("collectionSize"));
            double wallet = Double.parseDouble(intent.getStringExtra("wallet"));

            for (int i = 0; i < collectionSize; i++) {
                presenter.user.addCollection(intent.getStringExtra("collection" + i));
            }

            presenter.setMoney(wallet);
        }


    }

    private void initViewHolders() {
        resultListView = (ListView) findViewById(R.id.list);
    }

    @Override
    public void setBookResult(List<Book> books) {
        String[] list = new String[books.size()];

        for (int i = 0;i<books.size();i++) {
            list[i] = books.get(i).getTitle()+"\n"+ books.get(i).getPub_year()+ "\nPrice: " + books.get(i).getPrice() + " $";
        }
        ArrayAdapter<String> adapter =  new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        resultListView.setAdapter(adapter);

        resultListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, final int position, long arg3)
            {
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Do you want this item to add to the cart");
                builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getApplicationContext(),
                                "Book added", Toast.LENGTH_SHORT).show();
                        presenter.addToCart(position);
                    }
                });
                builder.setNegativeButton("Don't add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //dialog.dismiss();
                    }
                });
                builder.show();
            }
        });
    }

    public void onSearch(View view) {
        EditText editText = (EditText)findViewById(R.id.text);
        String text = editText.getText().toString();
        state = String.valueOf(spin.getSelectedItem());
        presenter.search(text,state);
    }

    public void onUser(View view) {
        Intent intent = new Intent(MainActivity.this, UserActivity.class);
        ArrayList<Book> cartBooks = presenter.user.order.getBooks();
        ArrayList<String> collection = presenter.user.getCollectionList();

        intent.putExtra("cartSize", cartBooks.size()+"");
        intent.putExtra("collectionSize", collection.size()+"");
        intent.putExtra("wallet", presenter.user.money+"");
        intent.putExtra("sumPrice", presenter.user.order.getTotalPrice()+"");
        for (int i = 0;i<collection.size();i++) {
            intent.putExtra("collection"+i,collection.get(i));
        }
        for (int i = 0;i<cartBooks.size();i++) {
            intent.putExtra("cartBook"+i,cartBooks.get(i).getTitle()+"\n"+ cartBooks.get(i).getPub_year()+ "\nPrice: " + cartBooks.get(i).getPrice() + " USD");
        }
        startActivity(intent);
        finish();
    }
}

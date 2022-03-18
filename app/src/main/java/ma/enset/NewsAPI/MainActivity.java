package ma.enset.NewsAPI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import ma.enset.NewsAPI.Model.Articles;
import ma.enset.NewsAPI.Model.Everything;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final String apiKey = "2dbb871928d94ad689d71f57bbcf66f7";
    RecyclerView recyclerView;
    Adapter adapter;
    List<Articles> articles = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText nameText = findViewById(R.id.nameField);
        EditText dateText = findViewById(R.id.dateField);
        Button cherche = findViewById(R.id.Chercher);
        cherche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView = findViewById(R.id.recyclerView);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                String name = nameText.getText().toString();
                String date = dateText.getText().toString();
                retrieveJson(name,date,apiKey);
            }
        });

    }

    public void retrieveJson(String country,String date, String apiKey ){
        Call<Everything> call = ApiClient.getInstance().getApi().getEverything(country,date,apiKey);
        call.enqueue(new Callback<Everything>() {
            @Override
            public void onResponse(Call<Everything> call, Response<Everything> response) {
                if (response.isSuccessful() && response.body().getArticles() !=null){
                    articles.clear();
                    articles = response.body().getArticles();
                    adapter = new Adapter(MainActivity.this,articles);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<Everything> call, Throwable t) {

            }
        });
    }

}
package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.adapters.StateAdapter;
import com.example.myapplication.models.State;
import com.squareup.picasso.Picasso;

import com.example.myapplication.listeners.MyBtnOnclickListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private  static final int REQUEST_INTERNET_PERMISSION=1;

    //Countries for drpdown list
   // String[] countries={"Brazil", "Columbia", "USA", "Mexico", "Chili"};

    private GridLayout gridLayout;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //buildListView();
        //buildSpinner();
        buildListViewCustomAdapter();

        //setContentView(R.layout.main_grid_layout);
        //checkPermissionInternet();

        //gridLayout=findViewById(R.id.gridLayout);

//        int numRows=3;
//        int numCols=3;
//        //createGridView(numRows, numCols);

//        getImageFromUrlByPicasso();

    }

    void buildListViewCustomAdapter(){
        // получаем элемент ListView
        // Обольше похож на ul / ol элементы html
        ListView countriesList = findViewById(R.id.countriesList);

        List<State> states= new ArrayList<>();
        states.add(new State("England", "London", R.drawable.en));
        states.add(new State("Ukraine", "Kyiv", R.drawable.ua));

        StateAdapter adapter = new StateAdapter(this, states);

        // устанавливаем для списка адаптер
        countriesList.setAdapter(adapter);

        // adapter.loadDataFromServer();

    }

    void buildSpinner(){
        Spinner spinner = findViewById(R.id.spinner);

        // получаем ресурс
        String[] countries = getResources().getStringArray(R.array.countries);

        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, countries);
        // Определяем разметку для использования при выборе элемента
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String selectedItem = countries[position];
                //toast("Spinner: " + selectedItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //toast("Spinner: Nothing Selected" );
            }
        });

        // Применяем адаптер к элементу spinner
        spinner.setAdapter(adapter);

    }
    void buildListView() {
        // получаем элемент ListView
        ListView countriesList = findViewById(R.id.countriesList);
        String[] countries = getResources().getStringArray(R.array.countries);
        // создаем адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, countries);
        countriesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String selectedItem = countries[position];
                view.setBackgroundColor(R.color.black);
                //toast("ListView: " + selectedItem);
            }
        });
        // устанавливаем для списка адаптер
        countriesList.setAdapter(adapter);
    }

//    void buildListViewCustomAdapter(){
//        // получаем элемент ListView
//        // Обольше похож на ul / ol элементы html
//        ListView countriesList = findViewById(R.id.countriesList);
//
//        List<State> states= new ArrayList<>();
//        states.add(new State("England", "London", R.drawable.en));
//        states.add(new State("Ukraine", "Kyiv", R.drawable.ua));
//
//        StateAdapter adapter = new StateAdapter(this, states);
//
//        // устанавливаем для списка адаптер
//        countriesList.setAdapter(adapter);
//
//        // adapter.loadDataFromServer();
//
//    }
    /**
     * Перед каждой операцией, связанной с запросом приложения в сеть, нужно вызывать этот метод
     */
    public void checkPermissionInternet(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED) {
            // Разрешение на доступ к интернету уже предоставлено. Вы можете выполнять операции, требующие доступ к интернету, здесь.
        } else {
            // Разрешение на доступ к интернету не предоставлено. Запросите его у пользователя.
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.INTERNET }, REQUEST_INTERNET_PERMISSION);
        }
    }

    protected void getImageFromUrlByGlide(){
        ConstraintLayout constraintLayout = new ConstraintLayout(this);

        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams
                (ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT);

        String imageUrl = "https://www.iconsdb.com/icons/preview/orange/cocktail-3-xxl.png";

        ImageView imageView = new ImageView(this);

//        Glide.with(this)
//                .load(imageUrl)
//                .into(imageView);

        imageView.setLayoutParams(layoutParams);
        constraintLayout.addView(imageView);
        setContentView(constraintLayout);
    }

    //Я должна импортировать Пикассо в файле Gradle Scripts/build.gradle.kts(Module:app)!!!!!
    protected void getImageFromUrlByPicasso(){
        ConstraintLayout constraintLayout = new ConstraintLayout(this);

        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams
                (ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT);

        String imageUrl = "https://www.iconsdb.com/icons/preview/orange/cocktail-3-xxl.png";

        ImageView imageView = new ImageView(this);

        Picasso.get()
                .load(imageUrl)
                .into(imageView);

        imageView.setLayoutParams(layoutParams);
        constraintLayout.addView(imageView);
        setContentView(constraintLayout);
    }





    private void createGridView(int numRows, int numCols) {
        gridLayout.removeAllViews();

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                String imageName = "image_" + (row * numCols + col);

                // Получение идентификатора ресурса для изображения
                int imageResource=getResources().getIdentifier(imageName, "drawable",  getPackageName());
                ImageView imageView = new ImageView(this);
                imageView.setImageResource(imageResource);


                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.rowSpec = GridLayout.spec(row);
                params.columnSpec = GridLayout.spec(col);
                params.setMargins(0, 0, 10, 10);
                imageView.setLayoutParams(params);

                gridLayout.addView(imageView);
            }
        }
    }



    protected void createMemoryGamePadOneIcon() {
        ConstraintLayout constraintLayout = new ConstraintLayout(this);

        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.image_0);

        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams
                (ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT);

        imageView.setLayoutParams(layoutParams);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toast("Click");
            }
        });

        constraintLayout.addView(imageView);

        setContentView(constraintLayout);
    }

    protected void createElements(){
        ConstraintLayout constraintLayout=new ConstraintLayout(this);

        Button btn=new Button(this);
        btn.setText("Hello, guys!");

        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams
                (ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT);

        btn.setLayoutParams(layoutParams);
        constraintLayout.addView(btn);
        setContentView(constraintLayout);
    }

    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.calculator_layout);
////        MyRegisterFormValidator validator=new MyRegisterFormValidator(this);
////        ((EditText)findViewById(R.id.inpEmail)).addTextChangedListener(validator);
////        ((EditText)findViewById(R.id.inpPassword)).addTextChangedListener(validator);
////        ((EditText)findViewById(R.id.inpPasswordConfirm)).addTextChangedListener(validator);
//    }
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        EditText inp=(EditText) findViewById(R.id.inpText);
//        //Second variant
//        inp.addTextChangedListener(new MyTextWatcher(this));
        //First variant
//        inp.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                //log(Double.parseDouble("before"+charSequence));
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                //log(Double.parseDouble("on"+charSequence));
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//               // log(Double.parseDouble("after"+editable.toString()));
//                Log.d("after",editable.toString());
//            }

        //});
    //}

    //@Override
//    protected void onCreateBtn(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        Button btn=(Button) findViewById(R.id.btnClickMe);
//        btn.setText("My new btn text");
//
//        //First variant
////        btn.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                Log.d("btn","Click on");
////            }
////        });
//        //Second variant-when I describe listener separately
//        btn.setOnClickListener(new MyBtnOnclickListener(this));
//    }

    public void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void log(String msg) {
        Log.d("MainActivity", msg);
    }
}
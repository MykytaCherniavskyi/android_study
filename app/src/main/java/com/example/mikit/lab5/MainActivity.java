package com.example.mikit.lab5;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private static final String FILE = "dbResult";
    private Button updateBtn;
    private Button deleteBtn;
    private Button addBtn;
    private Button readBtn;
    private Button clearBtn;
    private Button sortBtn;

    private EditText idInput;
    private EditText firstInput;
    private EditText secondInput;
    private EditText emailInput;
    private EditText addressInput;

    private RadioGroup sortGroup;

    private String orderBy;

    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //action buttons group
        updateBtn = findViewById(R.id.update);
        deleteBtn = findViewById(R.id.delete);
        addBtn = findViewById(R.id.addBtn);
        readBtn = findViewById(R.id.readBtn);
        clearBtn = findViewById(R.id.clearBtn);
        sortBtn = findViewById(R.id.sortBtn);

        //handlers for buttons
        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
        addBtn.setOnClickListener(this);
        readBtn.setOnClickListener(this);
        clearBtn.setOnClickListener(this);
        sortBtn.setOnClickListener(this);

        //editText group
        idInput = findViewById(R.id.idInput);
        firstInput = findViewById(R.id.firstNameInput);
        secondInput = findViewById(R.id.secondNameInput);
        emailInput = findViewById(R.id.emailInput);
        addressInput = findViewById(R.id.addressInput);

        //sort group
        sortGroup = findViewById(R.id.sortGroup);
        sortGroup.setOnCheckedChangeListener(this);
        orderBy = "not order";

        dbHelper = new DBHelper(this);
    }

    public void readData(SQLiteDatabase database, String orderBy) {
        // реализовать orderBy
        /*
         * query return Cursor - набор строк с данными
         * */
        try {
            Cursor cursor = database.query(DBHelper.TABLE_CONTACTS, null, null, null, null, null, orderBy);

            if (cursor.moveToFirst()) {
                int idIndex = cursor.getColumnIndex(DBHelper.KEY_PERSON_ID);
                int nameIndex = cursor.getColumnIndex(DBHelper.KEY_FIRST_NAME);
                int secondNameIndex = cursor.getColumnIndex(DBHelper.KEY_SECOND_NAME);
                int emailIndex = cursor.getColumnIndex(DBHelper.KEY_EMAIL);
                int addressIndex = cursor.getColumnIndex(DBHelper.KEY_HOME_ADDRESS);

                //Запись в файл
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(openFileOutput(FILE,MODE_PRIVATE)));

                do {
                    bw.write("ID = " + cursor.getInt(idIndex) +
                            ", name - " + cursor.getString(nameIndex) +
                            ", surname - " + cursor.getString(secondNameIndex) +
                            ", email - " + cursor.getString(emailIndex) +
                            ", address - " + cursor.getString(addressIndex) +
                            '\n');
                } while (cursor.moveToNext());

                bw.close();
            } else {
                Log.i("MyDatabase", "0 rows");
            }
            cursor.close();

            //Считывание из файла
            BufferedReader br = new BufferedReader( new InputStreamReader(openFileInput(FILE)));

            String str = "";

            while ((str = br.readLine()) != null) {
                Log.i("MyDatabase",str);
            }

            br.close();
        } catch (Exception e) {
                Log.i("My database",e.getMessage());
        }

    }


    @Override
    public void onClick(View v) {
        String id = idInput.getText().toString();
        String name = firstInput.getText().toString();
        String surname = secondInput.getText().toString();
        String email = emailInput.getText().toString();
        String address = addressInput.getText().toString();

        if (id.length() == 0) id = null;

        /*
        * Класс преднозначеный для работы с БД
        * Есть такие методы для работы как как:
        * query()
        * insert()
        * delete()
        * update()
        *
        * execSQL() - выполняет любой код на языке SQL
        * dbHelper.getWritableDatabase() - открыть и вернуть экземплят БД для работы (если бд нету, то onCreate и так же при изменение)
        * */
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        /*
        * Вспомогательный класс для добавления данных в таблицу
        * каждый экземпляр обьекта отвечает за одну строку
        * Active Record ???
        * Выглядит как массив Ключ - Значение
        * */
        ContentValues contentValues = new ContentValues();

        switch (v.getId()) {

            case R.id.update:
                if (id.equalsIgnoreCase("")) break;

                contentValues.put(DBHelper.KEY_FIRST_NAME, name);
                contentValues.put(DBHelper.KEY_SECOND_NAME, surname);
                contentValues.put(DBHelper.KEY_EMAIL, email);
                contentValues.put(DBHelper.KEY_HOME_ADDRESS, address);
                // = ? из массива аргументов подставиться нужный ид
                //в данном слечае из массива String, а так если будет несколько аргументов, то подставит по порядку
                Log.i("MyDatabase", "update row with id - " + id);
                database.update(DBHelper.TABLE_CONTACTS,contentValues,DBHelper.KEY_PERSON_ID + "= ?", new String[]{id});
                break;
            case R.id.delete:
                if (id.equalsIgnoreCase("")) break;

                Log.i("MyDatabase", "delete row with id - " + id);
                database.delete(DBHelper.TABLE_CONTACTS,DBHelper.KEY_PERSON_ID + "= " + id, null);
                break;
            case R.id.addBtn:

                contentValues.put(DBHelper.KEY_FIRST_NAME, name);
                contentValues.put(DBHelper.KEY_SECOND_NAME, surname);
                contentValues.put(DBHelper.KEY_EMAIL, email);
                contentValues.put(DBHelper.KEY_HOME_ADDRESS, address);

                /*
                * 1 - обьект таблицы
                * 2 - пустая строка
                * 3 - контент для вставки
                * */
                database.insert(DBHelper.TABLE_CONTACTS, null, contentValues);

                break;
            case R.id.readBtn:
                Log.i("MyDatabase", "Read all with orderBy" + orderBy);
                readData(database,null);
                break;
            case R.id.clearBtn:
                Log.i("MyDatabase", "delete all rows");
                database.delete(DBHelper.TABLE_CONTACTS, null, null);
                break;
            case R.id.sortBtn:
                if (orderBy.equalsIgnoreCase("not order")) break;
                readData(database,orderBy);
                break;

        }

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.sortNameRadio:
                orderBy = "_first_name";
                break;
            case R.id.surnameNameRadio:
                orderBy = "_second_name";
                break;
            case R.id.emailRadio:
                orderBy = "_email";
                break;
        }
    }
}

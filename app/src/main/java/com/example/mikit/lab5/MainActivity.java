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

public class MainActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

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

    private RadioButton sort;
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

        int checkedRadioButtonId = sortGroup.getCheckedRadioButtonId();

        // Найдём переключатель по его id
        RadioButton sort = findViewById(checkedRadioButtonId);
        orderBy = sort.getText().toString().toLowerCase();

        dbHelper = new DBHelper(this);
    }

    public void readData(SQLiteDatabase database, String orderBy) {
        // реализовать orderBy
        /*
         * query return Cursor - набор строк с данными
         * */
        Cursor cursor = database.query(DBHelper.TABLE_CONTACTS, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(DBHelper.KEY_PERSON_ID);
            int nameIndex = cursor.getColumnIndex(DBHelper.KEY_FIRST_NAME);
            int secondNameIndex = cursor.getColumnIndex(DBHelper.KEY_SECOND_NAME);
            int emailIndex = cursor.getColumnIndex(DBHelper.KEY_EMAIL);
            int addressIndex = cursor.getColumnIndex(DBHelper.KEY_HOME_ADDRESS);

            do {
                Log.i("MyDatabase", "ID = " + cursor.getInt(idIndex) +
                        ", name - " + cursor.getString(nameIndex) +
                        ", surname - " + cursor.getString(secondNameIndex) +
                        ", email - " + cursor.getString(emailIndex) +
                        ", address - " + cursor.getString(addressIndex));
            } while (cursor.moveToNext());
        } else {
            Log.i("MyDatabase", "0 rows");
        }
        cursor.close();
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

                break;
            case R.id.delete:
                Log.i("MyDatabase", "delete row with id - " + id);
                database.delete(DBHelper.TABLE_CONTACTS, null, new String[]{id});
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
                readData(database,orderBy);
                break;
            case R.id.clearBtn:
                Log.i("MyDatabase", "delete all database");
                database.delete(DBHelper.TABLE_CONTACTS, null, null);

                break;
            case R.id.sortBtn:

                break;

        }

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.sortNameRadio:
                orderBy = "name";
                break;
            case R.id.surnameNameRadio:
                orderBy = "surname";
                break;
            case R.id.emailRadio:
                orderBy = "email";
                break;
        }
    }
}

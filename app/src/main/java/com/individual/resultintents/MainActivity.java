package com.individual.resultintents;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //khai bao
    public static final int NEW_EMPLOYEE = 113;
    public static final int EDIT_EMPLOYEE = 114;
    public static final int SAVE_NEW_EMPLOYEE = 115;
    public static final int SAVE_EDIT_EMPLOYEE = 116;
    ListView lv;
    int posselect ;
    ArrayList<Person> list = new ArrayList<>();
    ArrayAdapter<Person> adapter=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Khai bao cac doi tuong person
        Person person1 = new Person(1, "Nguyen Van A");
        Person person2 = new Person(2, "Ha Thien An");
        Person person3 = new Person(3, "Phan Hieu Nghia");
        Person person4 = new Person(4, "Phan Thanh Kieu");
        Person person5 = new Person(5, "Đam Thu Ngan");
        Person person6 = new Person(6, "Kieu Dong Son");
        Person person7 = new Person(7, "Luc Tien Hiep");
        Person person8 = new Person(8, "Nguyen Thao Nhi");
        //Them person vao ArrayList
        list.add(person1);
        list.add(person2);
        list.add(person3);
        list.add(person4);
        list.add(person5);
        list.add(person6);
        list.add(person7);
        list.add(person8);
        //hien danh sach vao ListEmployee
        lv = findViewById(R.id.ListEmployee);
        adapter = new ArrayAdapter<Person>
                (this, android.R.layout.simple_list_item_1, list);
        lv.setAdapter((ListAdapter) adapter);
        lv.setOnItemClickListener(
                (AdapterView.OnItemClickListener) this::onItemClick);
        registerForContextMenu(lv);

    }
    private void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        posselect = arg2;
    }



    public void onCreateContextMenu (ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo) ;
        getMenuInflater().inflate(R.menu.mycontextmenu, menu);
    }


    public boolean onContextItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.menunew:
                doStartNew();
                break;
            case R.id.menuedit:
                doStartEdit();
                break;
            case R.id.menudelete:
                doStartDelete();
                break;
        }

        return super.onContextItemSelected(item);
    }

    @SuppressWarnings("deprecation")
    public void doStartNew() {
        Intent intentNEW = new Intent(this, NewActivity.class);
        startActivityForResult(intentNEW, MainActivity.NEW_EMPLOYEE);
    }
    @SuppressWarnings("deprecation")
    public void doStartEdit() {
        Intent intentEdit = new Intent(this, EditActivity.class);
        Person p =  list.get(posselect);
        Bundle bundle = new Bundle();
        bundle.putSerializable("per", p);
        intentEdit.putExtra("DATA", bundle);
        startActivityForResult(intentEdit, MainActivity.EDIT_EMPLOYEE);
    }
    public void doStartDelete() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Xoá");
        builder.setMessage("Xoá lựa chọn này?");
        builder.setPositiveButton("Có", (arg0, arg1) -> {
            list.remove(posselect);
            adapter.notifyDataSetChanged();
        }).setNegativeButton("Không", (dialog, which) -> dialog.dismiss());
        builder.create().show();
    }

    protected void  onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case MainActivity.NEW_EMPLOYEE:
                if (resultCode == MainActivity.SAVE_NEW_EMPLOYEE)
                {
                    Bundle bundle = data.getBundleExtra("DATA");
                    Person p = (Person) bundle.getSerializable("per");
                    list.add(p);
                    adapter.notifyDataSetChanged();
                }
                break;
            case MainActivity.EDIT_EMPLOYEE:
                if (resultCode == MainActivity.SAVE_EDIT_EMPLOYEE)
                {
                    Bundle bundle = data.getBundleExtra("DATA");
                    Person p = (Person) bundle.getSerializable("per");
                    list.set(posselect, p);
                    adapter.notifyDataSetChanged();
                }
                break;
        }
    }

    public boolean onCreateOptionsMenu (Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mycontextmenu, menu);
        return false;
    }
}
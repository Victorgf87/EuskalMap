package victorgf87.euskalmap;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import victorgf87.euskalmap.classes.Fetcher;
import victorgf87.euskalmap.classes.Group;
import victorgf87.euskalmap.classes.Groups;
import victorgf87.euskalmap.classes.Place;
import victorgf87.euskalmap.classes.Places;
import victorgf87.euskalmap.classes.User;
import victorgf87.euskalmap.classes.Users;
import victorgf87.euskalmap.placesadapters.PlacesAdapter;

public class MainActivity extends AppCompatActivity
{
    @InjectView(R.id.activity_main_rootLayout)RelativeLayout rootLayout;
    @InjectView(R.id.activity_main_recyclerPlaces)RecyclerView recyclerPlaces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        HandlerThread ht=new HandlerThread("threaddd");

        ht.start();
        Handler handler=new Handler(ht.getLooper());
        handler.post(new Runnable()
        {
            @Override
            public void run()
            {
                try {
                    new Fetcher().fetchFromUrl();
                    Place pla=Places.getInstance().getPlaces().get(0);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            populateRecyclerPlaces(Places.getInstance().getPlaces());


                        }
                    });


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    @MainThread
    private void populateRecyclerPlaces(List<Place>places)
    {
        PlacesAdapter adapt=new PlacesAdapter(places);
        recyclerPlaces.setAdapter(adapt);

        recyclerPlaces.setLayoutManager(new GridLayoutManager(this,10));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch(id)
        {
            case R.id.menu_main_show_graphs:
                Intent inte=new Intent();
                inte.setClass(this,GraphsActivity.class);
                startActivity(inte);
                return true;
            case R.id.menu_main_search_users:
                menuSearchUser();
                return true;
            case R.id.menu_main_search_place:
                menuSearchPlace();
                return true;
            case R.id.menu_main_search_group:
                menuSearchGroup();
                return true;

        }





        return super.onOptionsItemSelected(item);
    }


    private void menuSearchGroup()
    {
        final Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.ask_one_data_layout);
        dialog.setTitle("Introduzca nombre del grupo");

        dialog.show();

        btnOkInDialog= (Button) dialog.findViewById(R.id.ask_one_data_okButton);
        txtTitleInDialog=(TextView)dialog.findViewById(R.id.ask_one_data_txtTitle);
        txtDataInDialog=(EditText)dialog.findViewById(R.id.ask_one_data_txtData);
        txtTitleInDialog.setText("Introduzca nombre del grupo");


        btnOkInDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtDataInDialog.getText().length()>0)
                {
                    Group group= Groups.getInstance().findGroupByName(txtDataInDialog.getText().toString());
                    List<String>results=new ArrayList<String>();
                    if(group!=null)
                    {
                        List<User> users= group.getUsers();

                        Collections.sort(users);
                        for(User user: users)
                        {
                            results.add(user.resultText());
                        }
                    }
                    else
                    {
                        results.add("No se ha encontrado ningun grupo con ese nombre");
                    }

                    showResultsDialog(results);
                }
                dialog.dismiss();
            }
        });
    }

    private void menuSearchPlace()
    {
        final Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.search_place_spinners_layout);
        dialog.setTitle("Elija una plaza");

        dialog.show();

        final Spinner spinnerRow=(Spinner)dialog.findViewById(R.id.search_place_row_spinner);
        final Spinner spinnerColumn=(Spinner)dialog.findViewById(R.id.search_place_column_spinner);


        //Populate spinners
        final List<String> rowSpinnerArray =  new ArrayList<String>();

        for(char letra1='A'; letra1<='B'; letra1++)
        {
            for(char letra2='A'; letra2<='P'; letra2++)
            {
                rowSpinnerArray.add("" + letra1 + letra2);
            }
        }

        List<Integer>columnSpinnerArray=new ArrayList<Integer>();
        for(int i=1; i<=128; i++)
        {
            columnSpinnerArray.add(i);
        }

        ArrayAdapter<String> rowAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, rowSpinnerArray);

        ArrayAdapter<Integer>columnAdapter=new ArrayAdapter<Integer>(
                this,android.R.layout.simple_spinner_item, columnSpinnerArray);


        rowAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        columnAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinnerRow.setAdapter(rowAdapter);
        spinnerColumn.setAdapter(columnAdapter);




        btnOkInDialog= (Button) dialog.findViewById(R.id.ask_one_data_okButton);



        btnOkInDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String selectedRow = spinnerRow.getSelectedItem().toString();
                String selectedColumn = spinnerColumn.getSelectedItem().toString();
                /*List<User> users= Users.getInstance().usersByName(txtDataInDialog.getText().toString());
                List<String>results=new ArrayList<String>();
                Collections.sort(users);
                for(User user: users)
                {
                    results.add(user.resultText());
                }*/
                Place place= Places.getInstance().searchPlace(selectedRow,selectedColumn);
                List<String>results=new ArrayList<String>();
                results.add(place.getUser().resultText());
                showResultsDialog(results);
                dialog.dismiss();
            }
        });
    }

    TextView txtTitleInDialog;

    EditText txtDataInDialog;

    Button btnOkInDialog;

    private void menuSearchUser()
    {
        final Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.ask_one_data_layout);

        dialog.show();

        btnOkInDialog= (Button) dialog.findViewById(R.id.ask_one_data_okButton);
        txtTitleInDialog=(TextView)dialog.findViewById(R.id.ask_one_data_txtTitle);
        txtDataInDialog=(EditText)dialog.findViewById(R.id.ask_one_data_txtData);
        txtTitleInDialog.setText("Introduzca nombre o parte de el");


        btnOkInDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtDataInDialog.getText().length()>0)
                {
                    List<User> users= Users.getInstance().usersByName(txtDataInDialog.getText().toString());
                    List<String>results=new ArrayList<String>();
                    Collections.sort(users);
                    for(User user: users)
                    {
                        results.add(user.resultText());
                    }
                    showResultsDialog(results);
                }
                dialog.dismiss();
            }
        });
    }


    private void showResultsDialog(List<String>texts)
    {
        Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.show_results_layout);
        ListView lv= (ListView) dialog.findViewById(R.id.show_results_layout_list);
        ResultsAdapter adapt=new ResultsAdapter(texts);
        adapt.notifyDataSetChanged();
        lv.setAdapter(adapt);
        dialog.show();


    }


    public class ResultsAdapter extends BaseAdapter
    {


        private List<String>texts;

        public ResultsAdapter(List<String> texts) {
            this.texts = texts;
        }

        @Override
        public int getCount() {
            return texts.size();
        }

        @Override
        public Object getItem(int position) {
            return texts.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            TextView tv=new TextView(MainActivity.this);
            tv.setText(texts.get(position));
            return tv;
        }
    }
}

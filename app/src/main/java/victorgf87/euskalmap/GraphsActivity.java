package victorgf87.euskalmap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.dacer.androidcharts.BarView;
import com.dacer.androidcharts.PieHelper;
import com.dacer.androidcharts.PieView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.InjectViews;
import victorgf87.euskalmap.classes.Place;
import victorgf87.euskalmap.classes.Places;

public class GraphsActivity extends AppCompatActivity
{
    @InjectView(R.id.activity_graph_pie)PieView pieView;
    @InjectView(R.id.activity_graphs_amount_bars)BarView amountBars;
    @InjectViews({R.id.activity_graphs_txtunassigned,R.id.activity_graphs_txtreserved,R.id.activity_graphs_txtconfirmed,R.id.activity_graphs_txtgranted})TextView[] textViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphs);
        ButterKnife.inject(this);

        Set<String> keyset=Place.cssClassToColor.keySet();
        Object[] keySetString=  keyset.toArray();

        for(int i=0; i<textViews.length; i++)
        {
            textViews[i].setTextColor(Place.cssClassToColor.get(keySetString[i]));
        }


        fillPie();
        fillAmountBars();


    }

    private void fillAmountBars()
    {
        Places places=Places.getInstance();
        ArrayList<String>strList=new ArrayList<String>();
        ArrayList<Integer>dataList=new ArrayList<Integer>();


        dataList.add(places.getHowManyUnassigned());
        dataList.add(places.getHowManyReserved());
        dataList.add(places.getHowManyConfirmed());
        dataList.add(places.getHowManyGranted());


        Collection<String> names= Place.cssClassToName.values();
        for(String name:names)
        {
            strList.add(name);
        }


        amountBars.setBottomTextList(strList);
        amountBars.setDataList(dataList,Places.getInstance().getPlaces().size());
    }

    private void fillPie()
    {
        Places places= Places.getInstance();
        ArrayList<PieHelper> pieHelperArrayList = new ArrayList<PieHelper>();

        pieHelperArrayList.add(new PieHelper((int)places.getPercentageofUnassigned(), Place.cssClassToColor.get("g")));
        pieHelperArrayList.add(new PieHelper((int)places.getPercentageOfReserved(), Place.cssClassToColor.get("o")));
        pieHelperArrayList.add(new PieHelper((int)places.getPercentageOfConfirmed(), Place.cssClassToColor.get("r")));
        pieHelperArrayList.add(new PieHelper((int)places.getPercentageOfGranted(), Place.cssClassToColor.get("b")));

        pieView.setDate(pieHelperArrayList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_graphs, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

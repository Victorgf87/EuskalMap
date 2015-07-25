package victorgf87.euskalmap.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import victorgf87.euskalmap.R;
import victorgf87.euskalmap.classes.Place;

/**
 * Created by Nabe on 25/07/15.
 */
public class PlaceView extends RelativeLayout implements View.OnClickListener
{
    private Place place;
    @InjectView(R.id.place_view_layout_txtDescr)TextView txtPlaceName;
    @InjectView(R.id.place_view_layout_rootLayout)RelativeLayout rootLayout;
    public PlaceView(Context context, Place place) {
        super(context);
        this.place=place;
        inflate(context, R.layout.place_view_layout, (ViewGroup) this);
        ButterKnife.inject(this);
        txtPlaceName.setText(place.onlyPlaceUbication());
        setBackgroundColor(Place.cssClassToColor.get(place.getCssClass()));


    }


    /**
     * Managign clicks
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_UP){
            Toast.makeText(this.getContext(),place.getDataInfo(),Toast.LENGTH_LONG).show();
            return performClick();
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this.getContext(),"Hola",Toast.LENGTH_LONG).show();
    }
}

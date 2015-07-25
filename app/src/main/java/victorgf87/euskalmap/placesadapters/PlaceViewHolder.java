package victorgf87.euskalmap.placesadapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import victorgf87.euskalmap.R;
import victorgf87.euskalmap.classes.Place;


/**
 * Created by Nabe on 25/07/15.
 */
public class PlaceViewHolder extends RecyclerView.ViewHolder
{
    private RelativeLayout rootLayout;
    private TextView txtAddress;
    public PlaceViewHolder(View itemView)
    {
        super(itemView);
        rootLayout=(RelativeLayout)itemView.findViewById(R.id.place_view_layout_rootLayout);
        txtAddress= (TextView) itemView.findViewById(R.id.place_view_layout_txtDescr);
    }
    public void bindPlace(Place place)
    {
        rootLayout.setBackgroundColor(Place.cssClassToColor.get(place.getCssClass()));
        txtAddress.setText(place.getDataInfo());
    }

}

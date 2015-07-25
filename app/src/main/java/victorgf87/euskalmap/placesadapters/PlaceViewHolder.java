package victorgf87.euskalmap.placesadapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import victorgf87.euskalmap.R;
import victorgf87.euskalmap.classes.Place;


/**
 * Created by Nabe on 25/07/15.
 */
public class PlaceViewHolder extends RecyclerView.ViewHolder
{
    private RelativeLayout rootLayout;
    private TextView txtAddress;
    private View itemView;
    public PlaceViewHolder(View itemView)
    {
        super(itemView);
        this.itemView=itemView;


        rootLayout=(RelativeLayout)itemView.findViewById(R.id.place_view_layout_rootLayout);
        txtAddress= (TextView) itemView.findViewById(R.id.place_view_layout_txtDescr);
    }
    public void bindPlace(final Place place)
    {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(itemView.getContext(), place.getDataInfo(), Toast.LENGTH_LONG).show();
            }
        });
        rootLayout.setBackgroundColor(Place.cssClassToColor.get(place.getCssClass()));
        txtAddress.setText(place.onlyPlaceUbication());

    }

}

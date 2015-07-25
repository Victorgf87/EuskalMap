package victorgf87.euskalmap.placesadapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import victorgf87.euskalmap.R;
import victorgf87.euskalmap.classes.Place;

/**
 * Created by Nabe on 25/07/15.
 */
public class PlacesAdapter extends RecyclerView.Adapter<PlaceViewHolder>
{
    private List<Place>places;

    public PlacesAdapter(List<Place> places)
    {
        this.places=places;
    }

    @Override
    public PlaceViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.place_view_layout, viewGroup, false);

        PlaceViewHolder tvh = new PlaceViewHolder(itemView);

        return tvh;
    }

    @Override
    public void onBindViewHolder(PlaceViewHolder viewHolder, int pos) {
        Place item = places.get(pos);
        viewHolder.bindPlace(item);

    }
    @Override
    public int getItemCount() {
        return places.size();
    }
}

package scoreapp.testtask.com.presentation.view.adapter.spinnerAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import scoreapp.testtask.com.R;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import scoreapp.testtask.com.domain.model.Team;

public class SpinnerAdapter extends ArrayAdapter<Team>{

    private final LayoutInflater inflater;
    private List<Team> items;
    private int disabledItemPosition;

    public SpinnerAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource, -1);

        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView,
                                @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    @Override
    public int getCount(){
        return this.items.size();
    }

    @Override
    public @NonNull View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    private View createItemView(int position, View convertView, ViewGroup parent){
        final View view = this.inflater.inflate(R.layout.spinner_item, parent, false);

        TextView teamName = view.findViewById(R.id.textView_spiner_item_value);
        teamName.setText(this.items.get(position).getTeamName());

        return view;
    }

    @Override
    public boolean isEnabled(int position) {
        return this.disabledItemPosition != position;
    }

    public void setSpinnerData(List<Team> teams) {
        this.items = teams;
        notifyDataSetChanged();
    }

    public void setDisabledItem(int disabledItemPosition) {
        this.disabledItemPosition = disabledItemPosition;
        notifyDataSetChanged();
    }
}


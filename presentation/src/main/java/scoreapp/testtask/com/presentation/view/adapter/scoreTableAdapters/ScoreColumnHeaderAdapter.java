package scoreapp.testtask.com.presentation.view.adapter.scoreTableAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import scoreapp.testtask.com.R;
import scoreapp.testtask.com.domain.model.Team;


public class ScoreColumnHeaderAdapter extends RecyclerView.Adapter<ScoreColumnHeaderAdapter.ViewHolder> {

    private List<Team> list;
    private Context context;

    public ScoreColumnHeaderAdapter(Context context, List<Team> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cell_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(list.get(position).getTeamName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView_greed);
        }
    }
}
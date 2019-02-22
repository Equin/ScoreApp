package scoreapp.testtask.com.presentation.view.adapter.scoreTableAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import scoreapp.testtask.com.domain.model.OppositeTeam;
import scoreapp.testtask.com.domain.model.Team;

import scoreapp.testtask.com.R;

public class ScoreRowAdapter extends RecyclerView.Adapter<ScoreRowAdapter.ViewHolder> {

    private List<Team> list;
    private Context context;
    private int rowPosition;

    public ScoreRowAdapter(Context context, List<Team> list, int rowPosition) {
        this.context = context;
        this.list = list;
        this.rowPosition = rowPosition;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cell_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if(position == this.rowPosition) {
            holder.textView.setText(String.valueOf(position+1));
            holder.textView.setBackgroundColor(this.context.getResources().getColor(R.color.colorBlack));
            holder.textView.setTextColor(this.context.getResources().getColor(R.color.colorWhite));
        } else {
            holder.textView.setText("");
            holder.textView.setBackgroundColor(this.context.getResources().getColor(R.color.colorWhite));
            holder.textView.setTextColor(this.context.getResources().getColor(R.color.colorBlack));

            List<OppositeTeam> OppositeTeams = this.list.get(position).getOppositeTeamList();

            for(int i = 0; i<OppositeTeams.size(); i++) {
                if(this.list.get(this.rowPosition).getTeamName().equals(OppositeTeams.get(i).getTeamName())) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(OppositeTeams.get(i).getConcededGoals());
                    stringBuilder.append(":");
                    stringBuilder.append(OppositeTeams.get(i).getScoredGoals());

                    holder.textView.setText(stringBuilder);
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        ViewHolder(View itemView) {
            super(itemView);
            this.textView = itemView.findViewById(R.id.textView_greed);
        }
    }
}
package scoreapp.testtask.com.presentation.view.adapter.scoreTableAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import scoreapp.testtask.com.R;
import scoreapp.testtask.com.domain.model.Team;

public class ScoreTableAdapter extends RecyclerView.Adapter<ScoreTableAdapter.ViewHolder> {

    private static final int COUNT_TABLE_RECYCLER_VIEWS = 3;

    private List<Team> list;
    private Context context;
    private RecyclerView.RecycledViewPool viewPool;
    private RecyclerView.OnScrollListener scrollListener;
    private RecyclerView.OnItemTouchListener touchListener;

    public ScoreTableAdapter(Context context, List<Team> teams) {
        this.context = context;
        this.list = teams;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_item, parent, false);

        this.viewPool = new RecyclerView.RecycledViewPool();

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.rowRecycleView.setLayoutManager(holder.layoutManager);
        holder.rowRecycleView.addOnScrollListener(this.scrollListener);
        holder.rowRecycleView.addOnItemTouchListener(this.touchListener);
        holder.rowRecycleView.setHasFixedSize(true);
        holder.rowRecycleView.setTag(COUNT_TABLE_RECYCLER_VIEWS + position);

        ScoreRowAdapter scoreRowAdapter = new ScoreRowAdapter(context, list, position);
        holder.rowRecycleView.setAdapter(scoreRowAdapter);
        holder.rowRecycleView.setRecycledViewPool(this.viewPool);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private RecyclerView rowRecycleView;
        private RecyclerView.LayoutManager layoutManager;

        ViewHolder(View itemView) {
            super(itemView);

            this.rowRecycleView = itemView.findViewById(R.id.row_item_recycler_view);
            this.layoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        };
    }

    public void setListeners(RecyclerView.OnScrollListener scrollListener, RecyclerView.OnItemTouchListener touchListener) {
        this.touchListener = touchListener;
        this.scrollListener = scrollListener;
    }
}
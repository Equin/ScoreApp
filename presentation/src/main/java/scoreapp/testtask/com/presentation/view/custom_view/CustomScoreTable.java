package scoreapp.testtask.com.presentation.view.custom_view;

import android.app.Activity;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import java.util.List;

import javax.inject.Inject;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import scoreapp.testtask.com.domain.model.Team;
import scoreapp.testtask.com.presentation.view.adapter.scoreTableAdapters.ScoreColumnHeaderAdapter;
import scoreapp.testtask.com.presentation.view.adapter.scoreTableAdapters.ScoreRowHeaderAdapter;
import scoreapp.testtask.com.presentation.view.adapter.scoreTableAdapters.ScoreTableAdapter;

import scoreapp.testtask.com.R;

public class CustomScoreTable {

    private static final int TABLE_RECYCLER_VIEW_TAG = 0;
    private static final int COLUMN_HEADER_RECYCLER_VIEW_TAG = 1;
    private static final int ROW_HEADER_RECYCLER_VIEW_TAG = 2;
    private static final int RECYCLER_VIEWS_COUNT = 3;

    private Context context;
    private View view;

    private int touchedRecyclerViewTag;
    private int itemsCount;

    @Inject
    public CustomScoreTable(Context context) {
        this.context = context;
    }

    public void setView(View view) {
        this.view = view;
    }

    private void setupTableRecycleViews(List<Team> teams) {
        createColumnHeaderRecyclerView(teams);
        createRowHeaderRecyclerView(teams);
        createContentRecyclerView(teams);
    }

    private void createContentRecyclerView(List<Team> teams) {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(
                this.context,RecyclerView.HORIZONTAL, false);

        RecyclerView recyclerViewTableContent = this.view.findViewById(R.id.recyclerView_table_content);
        recyclerViewTableContent.setHasFixedSize(true);
        recyclerViewTableContent.setLayoutManager(manager);
        recyclerViewTableContent.setTag(TABLE_RECYCLER_VIEW_TAG);
        recyclerViewTableContent.addOnScrollListener(this.recyclerViewScrollListener);
        recyclerViewTableContent.addOnItemTouchListener(this.recyclerViewTouchListener);

        ScoreTableAdapter adapter = new ScoreTableAdapter(this.context, teams);
        adapter.setListeners(this.recyclerViewScrollListener, this.recyclerViewTouchListener);
        recyclerViewTableContent.setAdapter(adapter);
    }

    private void createRowHeaderRecyclerView(List<Team> teams) {
        RecyclerView.LayoutManager rowLayoutManager = new LinearLayoutManager(
                this.context, LinearLayout.HORIZONTAL, false);

        RecyclerView rowHeaderRecycleView = this.view.findViewById(R.id.recyclerView_row_header);
        rowHeaderRecycleView.setHasFixedSize(true);
        rowHeaderRecycleView.setLayoutManager(rowLayoutManager);
        rowHeaderRecycleView.setTag(ROW_HEADER_RECYCLER_VIEW_TAG);
        rowHeaderRecycleView.addOnScrollListener(this.recyclerViewScrollListener);
        rowHeaderRecycleView.addOnItemTouchListener(this.recyclerViewTouchListener);

        ScoreRowHeaderAdapter scoreRowHeaderAdapter = new ScoreRowHeaderAdapter(this.context, teams);
        rowHeaderRecycleView.setAdapter(scoreRowHeaderAdapter);
    }

    private void createColumnHeaderRecyclerView(List<Team> teams) {
        RecyclerView.LayoutManager columnHeaderLayoutManager = new LinearLayoutManager(this.context);

        RecyclerView columnHeaderRecycleView = this.view.findViewById(R.id.recyclerView_column_header);
        columnHeaderRecycleView.setHasFixedSize(true);
        columnHeaderRecycleView.setLayoutManager(columnHeaderLayoutManager);
        columnHeaderRecycleView.setTag(COLUMN_HEADER_RECYCLER_VIEW_TAG);
        columnHeaderRecycleView.addOnScrollListener(this.recyclerViewScrollListener);
        columnHeaderRecycleView.addOnItemTouchListener(this.recyclerViewTouchListener);

        ScoreColumnHeaderAdapter scoreColumnHeaderAdapter = new ScoreColumnHeaderAdapter(this.context, teams);
        columnHeaderRecycleView.setAdapter(scoreColumnHeaderAdapter);
    }

    public void renderScoreTable(List<Team> teams) {
        this.itemsCount = teams.size();
        setupTableRecycleViews(teams);
    };

    public RecyclerView.OnScrollListener recyclerViewScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if ((int) recyclerView.getTag() == CustomScoreTable.this.touchedRecyclerViewTag) {
                for (int recyclerViewTag = 0; recyclerViewTag < itemsCount + RECYCLER_VIEWS_COUNT; recyclerViewTag++) {
                    if (recyclerViewTag != (int) recyclerView.getTag()) {
                        RecyclerView tempRecyclerView = CustomScoreTable.this.view.findViewWithTag(recyclerViewTag);
                        if(tempRecyclerView != null)
                            tempRecyclerView.scrollBy(dx, dy);
                    }
                }
            }
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }
    };

    private RecyclerView.OnItemTouchListener recyclerViewTouchListener = new RecyclerView.OnItemTouchListener() {
        @Override
        public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent event) {
            CustomScoreTable.this.touchedRecyclerViewTag = (int) recyclerView.getTag();
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView recyclerView, MotionEvent event) {
            //intentionally left empty
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
            //intentionally left empty
        }
    };
}

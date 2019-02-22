package scoreapp.testtask.com.presentation.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.evrencoskun.tableview.TableView;
import com.evrencoskun.tableview.adapter.AbstractTableAdapter;

import scoreapp.testtask.com.domain.model.Team;
import scoreapp.testtask.com.presentation.constant.HeaderTitle;
import scoreapp.testtask.com.presentation.internal.di.components.CoreComponent;
import scoreapp.testtask.com.presentation.model.Cell;
import scoreapp.testtask.com.presentation.model.ColumnHeader;
import scoreapp.testtask.com.presentation.model.RowHeader;
import scoreapp.testtask.com.presentation.presenter.ScoreTablePresenter;
import scoreapp.testtask.com.presentation.view.adapter.spinnerAdapters.SortSpinnerAdapter;
import scoreapp.testtask.com.presentation.view.adapter.statisticsTableAdapter.StatisticsTableViewAdapter;
import scoreapp.testtask.com.presentation.view.listener.OnSortItemSelectedListener;
import scoreapp.testtask.com.presentation.model.TableViewModel;
import scoreapp.testtask.com.presentation.view.contract.ScoreTableView;
import scoreapp.testtask.com.presentation.view.custom_view.CustomScoreTable;
import scoreapp.testtask.com.R;

public class ScoreTableFragment extends BaseFragment implements ScoreTableView {

    public static ScoreTableFragment newInstance() {
        return new ScoreTableFragment();
    }

    @Inject
    ScoreTablePresenter scoreTablePresenter;

    @Inject
    CustomScoreTable customScoreTable;

    @BindView(R.id.tableView_statistic_table)
    TableView tableView;

    @BindView(R.id.spinner_sort)
    Spinner sortSpinner;

    private AbstractTableAdapter<ColumnHeader, RowHeader, Cell> statisticsTable;
    private TableViewModel statisticsTableViewModel;

    public ScoreTableFragment() {
        setRetainInstance(true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(CoreComponent.class).inject(this);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_score_table, container, false);

        ButterKnife.bind(this, rootView);

        initializeTableView();
        initializeSortSpinner();

        return rootView;
    }

    private void initializeSortSpinner() {
        SortSpinnerAdapter sortSpinnerAdapter = new SortSpinnerAdapter(getContext(), R.layout.spinner_item);
        sortSpinnerAdapter.setSpinnerData(Arrays.asList(HeaderTitle.headerNames));
        this.sortSpinner.setOnItemSelectedListener(new OnSortItemSelectedListener(this.tableView));
        this.sortSpinner.setAdapter(sortSpinnerAdapter);
    }

    private void initializeTableView() {
        this.statisticsTable = new StatisticsTableViewAdapter(getContext());
        this.tableView.setAdapter(this.statisticsTable);
        this.statisticsTableViewModel = new TableViewModel();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.scoreTablePresenter.setView(this);
        this.scoreTablePresenter.initialize();
        this.customScoreTable.setView(view);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.scoreTablePresenter.destroy();
        this.hideLoading();
    }

    @Override
    public void renderTables(List<Team> teams) {
        this.customScoreTable.renderScoreTable(teams);

        this.statisticsTableViewModel.setTableData(teams);
        this.statisticsTable.setAllItems(
                statisticsTableViewModel.getColumnHeaderList(),
                statisticsTableViewModel.getRowHeaderList(),
                statisticsTableViewModel.getCellList());
    }
}
package scoreapp.testtask.com.presentation.view.listener;

import android.view.View;
import android.widget.AdapterView;

import com.evrencoskun.tableview.TableView;
import com.evrencoskun.tableview.sort.SortState;

public class OnSortItemSelectedListener implements AdapterView.OnItemSelectedListener {

    private TableView tableView;

    public OnSortItemSelectedListener(TableView tableView) {
        this.tableView = tableView;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        this.tableView.sortColumn(i, SortState.ASCENDING);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        // intentionally left empty
    }
}
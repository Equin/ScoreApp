package scoreapp.testtask.com.presentation.view.adapter.statisticsTableAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.evrencoskun.tableview.adapter.AbstractTableAdapter;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;
import com.evrencoskun.tableview.sort.SortState;

import scoreapp.testtask.com.presentation.model.Cell;
import scoreapp.testtask.com.presentation.model.ColumnHeader;
import scoreapp.testtask.com.presentation.model.RowHeader;
import scoreapp.testtask.com.presentation.view.adapter.statisticsTableAdapter.holder.CellViewHolder;
import scoreapp.testtask.com.presentation.view.adapter.statisticsTableAdapter.holder.ColumnHeaderViewHolder;
import scoreapp.testtask.com.presentation.view.adapter.statisticsTableAdapter.holder.RowHeaderViewHolder;

import scoreapp.testtask.com.R;

public class StatisticsTableViewAdapter extends AbstractTableAdapter<ColumnHeader, RowHeader, Cell> {

    private final LayoutInflater inflater;

    public StatisticsTableViewAdapter(Context context) {
        super(context);
        this.inflater = LayoutInflater.from(mContext);
    }

    @Override
    public AbstractViewHolder onCreateCellViewHolder(ViewGroup parent, int viewType) {
        View layout = this.inflater.inflate(R.layout.table_view_cell_layout, parent, false);
        return new CellViewHolder(layout);
    }

    @Override
    public void onBindCellViewHolder(AbstractViewHolder holder, Object cellItemModel,
                                     int columnPosition, int rowPosition) {
        Cell cell = (Cell) cellItemModel;
        CellViewHolder viewHolder = (CellViewHolder) holder;
        viewHolder.setCell(cell);
    }

    @Override
    public AbstractViewHolder onCreateColumnHeaderViewHolder(ViewGroup parent, int viewType) {
        View layout = this.inflater.inflate(R.layout.table_view_column_header_layout, parent, false);
        return new ColumnHeaderViewHolder(layout);
    }

    @Override
    public void onBindColumnHeaderViewHolder(AbstractViewHolder holder, Object columnHeaderItemModel,
                                             int columnPosition) {
        ColumnHeader columnHeader = (ColumnHeader) columnHeaderItemModel;
        ColumnHeaderViewHolder columnHeaderViewHolder = (ColumnHeaderViewHolder) holder;
        columnHeaderViewHolder.setColumnHeader(columnHeader);
    }

    @Override
    public AbstractViewHolder onCreateRowHeaderViewHolder(ViewGroup parent, int viewType) {
        View layout = this.inflater.inflate(R.layout.table_view_row_header_layout, parent, false);
        return new RowHeaderViewHolder(layout);
    }

    @Override
    public void onBindRowHeaderViewHolder(AbstractViewHolder holder, Object rowHeaderItemModel,
                                          int rowPosition) {
        RowHeader rowHeader = (RowHeader) rowHeaderItemModel;
        RowHeaderViewHolder rowHeaderViewHolder = (RowHeaderViewHolder) holder;
        rowHeaderViewHolder.rowHeaderTextView.setText(String.valueOf(rowHeader.getData()));
    }


    @Override
    public View onCreateCornerView() {
        View corner = inflater.inflate(R.layout.table_view_corner_layout, null);

        corner.setOnClickListener(view -> {
            SortState sortState = StatisticsTableViewAdapter.this.getTableView()
                    .getRowHeaderSortingStatus();
            if (sortState != SortState.ASCENDING) {
                StatisticsTableViewAdapter.this.getTableView().sortRowHeader(SortState.ASCENDING);
            } else {
                StatisticsTableViewAdapter.this.getTableView().sortRowHeader(SortState.DESCENDING);
            }
        });

        return corner;
    }

    @Override
    public int getColumnHeaderItemViewType(int position) {
        return 0;
    }

    @Override
    public int getRowHeaderItemViewType(int position) {
        return 0;
    }

    @Override
    public int getCellItemViewType(int column) {
                return 0;
    }
}

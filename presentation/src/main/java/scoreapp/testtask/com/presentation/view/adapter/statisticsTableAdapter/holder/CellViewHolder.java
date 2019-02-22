package scoreapp.testtask.com.presentation.view.adapter.statisticsTableAdapter.holder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;
import scoreapp.testtask.com.R;
import scoreapp.testtask.com.presentation.model.Cell;

public class CellViewHolder extends AbstractViewHolder {

    private final TextView cellTextView;
    private final LinearLayout cellContainer;
    private Cell cell;

    public CellViewHolder(View itemView) {
        super(itemView);
        this.cellTextView = (TextView) itemView.findViewById(R.id.cell_data);
        this.cellContainer = (LinearLayout) itemView.findViewById(R.id.cell_container);
    }

    public void setCell(Cell cell) {
        this.cell = cell;
        this.cellTextView.setText(String.valueOf(cell.getData()));

        // It is necessary to remeasure itself.
        this.cellContainer.getLayoutParams().width = LinearLayout.LayoutParams.WRAP_CONTENT;
        this.cellTextView.requestLayout();
    }
}
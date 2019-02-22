package scoreapp.testtask.com.presentation.view.adapter.statisticsTableAdapter.holder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractSorterViewHolder;

import scoreapp.testtask.com.R;
import scoreapp.testtask.com.presentation.model.ColumnHeader;

public class ColumnHeaderViewHolder extends AbstractSorterViewHolder {

    private final LinearLayout columnHeaderContainer;
    private final TextView columnHeaderTextView;

    public ColumnHeaderViewHolder(View itemView) {
        super(itemView);
        this.columnHeaderTextView = (TextView) itemView.findViewById(R.id.column_header_textView);
        this.columnHeaderContainer = (LinearLayout) itemView.findViewById(R.id
                .column_header_container);
    }

    public void setColumnHeader(ColumnHeader columnHeader) {
        this.columnHeaderTextView.setText(String.valueOf(columnHeader.getData()));

        // It is necessary to remeasure itself.
        this.columnHeaderContainer.getLayoutParams().width = LinearLayout.LayoutParams.WRAP_CONTENT;
        this.columnHeaderTextView.requestLayout();
    }
}

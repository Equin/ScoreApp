package scoreapp.testtask.com.presentation.view.adapter.statisticsTableAdapter.holder;

import android.view.View;
import android.widget.TextView;

import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;

import scoreapp.testtask.com.R;

public class RowHeaderViewHolder extends AbstractViewHolder {

    public final TextView rowHeaderTextView;

    public RowHeaderViewHolder(View itemView) {
        super(itemView);
        this.rowHeaderTextView = (TextView) itemView.findViewById(R.id.row_header_textview);
    }
}
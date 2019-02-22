package scoreapp.testtask.com.presentation.view.listener;

import android.view.View;
import android.widget.AdapterView;

import scoreapp.testtask.com.presentation.view.adapter.spinnerAdapters.SpinnerAdapter;

public class OnItemSelectedListener implements AdapterView.OnItemSelectedListener {
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        SpinnerAdapter spinnerAdapter = (SpinnerAdapter) adapterView.getAdapter();
        spinnerAdapter.setDisabledItem(i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        // intentionally left empty
    }
}
package scoreapp.testtask.com.presentation.view.listener;

import androidx.appcompat.app.AlertDialog;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

public class EditorActionListener implements TextView.OnEditorActionListener {

    private AlertDialog alertDialog;

    public EditorActionListener(AlertDialog alertDialog) {
        this.alertDialog = alertDialog;
    }

    @Override
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {

        if(actionId == EditorInfo.IME_ACTION_DONE) {
            this.alertDialog.dismiss();
            return true;
        }

        return false;
    }
}
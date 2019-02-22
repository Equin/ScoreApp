package scoreapp.testtask.com.presentation.constant;

import scoreapp.testtask.com.R;

public enum AlertDialogType {
    ADD_TEAM(R.layout.add_team_dialog),
    ADD_SCORE(R.layout.add_score_dialog);

    private final int layoutResource;

    AlertDialogType(int layoutResource) {
            this.layoutResource = layoutResource;
    }

    public int getLayoutResource() {
        return this.layoutResource;
    }
}

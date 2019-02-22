package scoreapp.testtask.com.presentation.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;
import scoreapp.testtask.com.domain.model.Team;
import scoreapp.testtask.com.presentation.constant.AlertDialogType;
import scoreapp.testtask.com.presentation.internal.di.components.CoreComponent;
import scoreapp.testtask.com.presentation.presenter.MainPresenter;
import scoreapp.testtask.com.presentation.view.adapter.spinnerAdapters.SpinnerAdapter;
import scoreapp.testtask.com.presentation.view.contract.MainView;

import scoreapp.testtask.com.R;
import scoreapp.testtask.com.presentation.view.listener.EditorActionListener;
import scoreapp.testtask.com.presentation.view.listener.OnItemSelectedListener;

public class MainFragment extends BaseFragment implements MainView {

    public interface MainFragmentInteractionListener {
           void showStatisticsScreen();
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Inject
    MainPresenter mainPresenter;

    private SpinnerAdapter spinnerAdapter;
    private AlertDialog alertDialog;
    private MainFragmentInteractionListener mainFragmentInteractionListener;

    @Nullable
    @BindView(R.id.editText_team_name)
    EditText editTextTeamName;

    public MainFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.mainPresenter.setView(this);
        this.mainPresenter.initialize();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainFragmentInteractionListener) {
            this.mainFragmentInteractionListener = (MainFragmentInteractionListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.mainFragmentInteractionListener = null;
    }

    @Override
    public void onDestroy() {
       super.onDestroy();
       this.mainPresenter.destroy();
    }

    @Override
    public void showAddTeamDialog() {
        createAddTeamDialog();
    }

    @Override
    public void showAddScoreDialog() {
        createAddScoreDialog();
    }

    @Override
    public void showStatisticsScreen() {
        this.mainFragmentInteractionListener.showStatisticsScreen();
    }

    @Override
    public void setTeamsToSpinnerAdapter(List<Team> teams) {
        this.spinnerAdapter.setSpinnerData(teams);
    }

    @Override
    public void dismissAddScoreDialog() {
        this.alertDialog.dismiss();
    }

    @Optional
    @OnClick(R.id.button_show_statistics)
    public void onShowStatisticsButtonClicked(){
        this.mainPresenter.onShowStatisticsButtonClicked();
    }

    @Optional
    @OnClick(R.id.button_add_team)
    public void onAddTeamButtonClicked(){
        this.mainPresenter.onAddTeamButtonClicked();
    }

    @Optional
    @OnClick(R.id.button_add_score)
    public void onScoreButtonClicked(){
        this.mainPresenter.onAddScoreButtonClicked();
    }

    @Optional
    @OnClick(R.id.button_reset_statistics)
    public void onResetStatisticsButtonClicked(){
        this.mainPresenter.onResetStatisticsButtonClicked();
    }

    private void createAddTeamDialog( ) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View dialogCustomVIew = layoutInflater.inflate(AlertDialogType.ADD_TEAM.getLayoutResource(), null);

        createDialog(dialogCustomVIew);
        initializeAddTeamDialogContent(dialogCustomVIew);
    }

    private void  initializeAddTeamDialogContent(View dialogCustomVIew) {
        Button saveTeamButton = dialogCustomVIew.findViewById(R.id.button_save_new_team);
        EditText teamName = dialogCustomVIew.findViewById(R.id.editText_team_name);
        teamName.setOnEditorActionListener(new EditorActionListener(this.alertDialog));

        saveTeamButton.setOnClickListener((click)->{
            this.mainPresenter.onSaveTeamButtonClicked(teamName.getText().toString());
        });
    }

    private void createAddScoreDialog( ) {
        LayoutInflater li = LayoutInflater.from(getContext());
        View dialogCustomVIew = li.inflate(AlertDialogType.ADD_SCORE.getLayoutResource(), null);

        createDialog(dialogCustomVIew);
        initializeAddScoreDialogContent(dialogCustomVIew);
    }

    private void  initializeAddScoreDialogContent(View dialogCustomVIew) {
        this.spinnerAdapter = new SpinnerAdapter(getContext(), R.layout.spinner_item);

        this.mainPresenter.setTeamsToSpinnerAdapter();

        Button saveScoreButton = dialogCustomVIew.findViewById(R.id.button_save_score);

        EditText scoreFirstTeam = dialogCustomVIew.findViewById(R.id.editText_score_first_team);
        EditText scoreSecondTeam = dialogCustomVIew.findViewById(R.id.editText_score_second_team);

        scoreFirstTeam.setOnEditorActionListener(new EditorActionListener(this.alertDialog));
        scoreSecondTeam.setOnEditorActionListener(new EditorActionListener(this.alertDialog));

        Spinner spinnerFirstTeam = dialogCustomVIew.findViewById(R.id.spinner_first_team);
        Spinner spinnerSecondTeam = dialogCustomVIew.findViewById(R.id.spinner_second_team);

        spinnerFirstTeam.setOnItemSelectedListener(new OnItemSelectedListener());
        spinnerSecondTeam.setOnItemSelectedListener(new OnItemSelectedListener());

        spinnerSecondTeam.setAdapter(this.spinnerAdapter);
        spinnerFirstTeam.setAdapter(this.spinnerAdapter);
        spinnerSecondTeam.setSelection(1);

        saveScoreButton.setOnClickListener((click)->{
            int firstTeamScores = Integer.parseInt(
                    !("").equals(scoreFirstTeam.getText().toString())
                            ? scoreFirstTeam.getText().toString() : "0");
            int secondTeamScores = Integer.parseInt(
                    !("").equals(scoreSecondTeam.getText().toString())
                            ? scoreSecondTeam.getText().toString() : "0");

            TextView firstTeamName = (TextView)  spinnerFirstTeam.getSelectedView()
                    .findViewById(R.id.textView_spiner_item_value);
            TextView secondTeamName = (TextView)  spinnerSecondTeam.getSelectedView()
                    .findViewById(R.id.textView_spiner_item_value);

            this.mainPresenter.onSaveTeamScoreButtonClicked(
                    firstTeamName.getText().toString(),
                    firstTeamScores,
                    secondTeamName.getText().toString(),
                    secondTeamScores);
        });
    }

    private void createDialog(View dialogCustomVIew) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setView(dialogCustomVIew);

        this.alertDialog = alertDialogBuilder.create();
        this.alertDialog.setOnShowListener(dialogInterface -> {
            showKeyboard();

        });

        this.alertDialog.setOnDismissListener(dialogInterface -> {
            hideKeyboard();
        });

        this.alertDialog.show();
    }
}
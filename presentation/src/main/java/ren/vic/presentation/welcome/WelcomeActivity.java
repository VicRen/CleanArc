package ren.vic.presentation.welcome;

import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import ren.vic.presentation.R;
import ren.vic.presentation.R2;
import ren.vic.presentation.common.BaseActionBarActivity;

public class WelcomeActivity extends BaseActionBarActivity implements WelcomeContract.View {

    @BindView(R2.id.tvWelcome)
    TextView mTvWelcome;

    @Inject
    WelcomePresenter mWelcomePresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initData() {
        mWelcomePresenter.setView(this);
        mWelcomePresenter.showMessage("Vic");
    }

    @Override
    public void onShowMessage(String message) {
        mTvWelcome.setText(message);
    }
}

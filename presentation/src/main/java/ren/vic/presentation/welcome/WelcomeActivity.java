package ren.vic.presentation.welcome;

import android.widget.TextView;

import butterknife.BindView;
import ren.vic.presentation.R;
import ren.vic.presentation.R2;
import ren.vic.presentation.common.BaseActionBarActivity;

public class WelcomeActivity extends BaseActionBarActivity {

    @BindView(R2.id.tvWelcome)
    TextView mTvWelcome;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initData() {
        mTvWelcome.setText("Welcome");
    }
}

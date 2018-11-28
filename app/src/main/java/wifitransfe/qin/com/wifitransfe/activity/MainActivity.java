package wifitransfe.qin.com.wifitransfe.activity;

import android.os.Bundle;

import com.qin.mvplibrary.Model;

import wifitransfe.qin.com.wifitransfe.R;
import wifitransfe.qin.com.wifitransfe.presenter.MainPresenter;
import wifitransfe.qin.com.wifitransfe.view.MainActiviDelegatety;

public class MainActivity extends BaseActivity<Model, MainActiviDelegatety, MainPresenter> implements MainActiviDelegatety{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public Model createModel() {
        return null;
    }

    @Override
    public MainActiviDelegatety createView() {
        return this;
    }

    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter();
    }


}

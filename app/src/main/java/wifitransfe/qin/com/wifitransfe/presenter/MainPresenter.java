package wifitransfe.qin.com.wifitransfe.presenter;


import android.util.Log;

import com.qin.mvplibrary.BasePresenter;
import com.qin.mvplibrary.Model;

import wifitransfe.qin.com.wifitransfe.view.MainActiviDelegatety;

public class MainPresenter extends BasePresenter<Model, MainActiviDelegatety> {

    @Override
    protected void onViewDestroy() {//销毁Activity时的操作，可以停止当前的model
    }
}

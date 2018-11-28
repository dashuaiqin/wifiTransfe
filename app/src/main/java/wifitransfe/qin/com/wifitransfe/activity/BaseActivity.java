package wifitransfe.qin.com.wifitransfe.activity;


import com.qin.mvplibrary.BaseMvpActivity;
import com.qin.mvplibrary.BasePresenter;
import com.qin.mvplibrary.Model;
import com.qin.mvplibrary.Delegate;

public abstract class BaseActivity<M extends Model, V extends Delegate, P extends BasePresenter> extends BaseMvpActivity<M, V, P> {

}

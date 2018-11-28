package com.qin.mvplibrary;

public interface BaseMvp<M extends Model, V extends Delegate, P extends BasePresenter> {
    M createModel();

    V createView();

    P createPresenter();
}

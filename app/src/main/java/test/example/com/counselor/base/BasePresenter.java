package test.example.com.counselor.base;

import android.content.Context;

/**
 * Created by Lan on 2016/11/2.
 */

public abstract class BasePresenter<E, T> {

    protected Context mContext;
    protected E mModel;
    protected T mView;

    public void setModelAndView(E model, T view, Context context) {
        this.mModel = model;
        this.mView = view;
        this.mContext = context;
        this.onAttachView();
    }

    public abstract void onAttachView();

    public abstract void onDetachView();
}

package test.example.com.counselor.util;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by Sli.D on 2018/1/23.
 */

public class PDialog {
    ProgressDialog dialog;
    public PDialog(Context context,String msg, boolean close) {
        if(dialog==null)
            this.dialog = new ProgressDialog(context);
        this.dialog.setMessage(msg);
        this.dialog.setCancelable(close);
    }
    public void show(){
        this.dialog.show();
    }
    public void dismiss(){
        this.dialog.dismiss();
    }
}

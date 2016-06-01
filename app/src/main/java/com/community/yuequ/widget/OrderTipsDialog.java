package com.community.yuequ.widget;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;

import com.community.yuequ.R;
import com.community.yuequ.imple.DialogConfListener;
import com.community.yuequ.imple.OrderTipsOneListener;

/**
 * Created by Administrator on 2016/6/1.
 */
public class OrderTipsDialog extends DialogFragment {

    public static OrderTipsDialog newInstance(String tips) {
        OrderTipsDialog dialog = new OrderTipsDialog();
        Bundle bundle = new Bundle();
        bundle.putString("tips",tips);
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.tips)
                .setMessage("您还没有权限观看节目，成为VIP可无限量观看！")
                .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        FragmentActivity activity = getActivity();
                        if(activity instanceof DialogConfListener){
                            ((OrderTipsOneListener)activity).one_confirm();
                        }

                    }
                })
                .setNegativeButton(R.string.alert_dialog_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        FragmentActivity activity = getActivity();
                        if(activity instanceof DialogConfListener){
                            ((OrderTipsOneListener)activity).one_cancel();
                        }
                    }
                })
                .create();
    }

}

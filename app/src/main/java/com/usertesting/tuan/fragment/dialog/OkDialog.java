package com.usertesting.tuan.fragment.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.usertesting.tuan.activity.R;
import com.usertesting.tuan.data.Answer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tuan on 3/19/2016.
 */
public class OkDialog extends DialogFragment {

    public static final String TAG = OkDialog.class.getSimpleName();
    public static final String BUNDLE_DATA = TAG + "BUNDLE_DATA";
    protected Context mContext;
    private TextView mDialogTitle;
    private TextView mDialogBody;
    private Button mPositiveButton;

    public static OkDialog newInstance(ArrayList<String> requirements) {

        OkDialog dialog = new OkDialog();
        if(requirements != null) {
            Bundle args = new Bundle();
            args.putStringArrayList(BUNDLE_DATA, requirements);
            dialog.setArguments(args);
        }
        return dialog;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        mContext = activity;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Dialog ret = new Dialog(mContext);
        ret.requestWindowFeature(Window.FEATURE_NO_TITLE);
        ret.setContentView(R.layout.ok_dialog_layout);

        mDialogTitle = (TextView) ret.findViewById(R.id.dialog_title);
        mDialogTitle.setText(getResources().getString(R.string.test_info));
        mDialogBody = (TextView) ret.findViewById(R.id.dialog_body);
        String bodyText = "";
        Bundle args = getArguments();
        if(args != null && args.getStringArrayList(BUNDLE_DATA) != null) {
            List<String> list = args.getStringArrayList(BUNDLE_DATA);
            bodyText = getResources().getString(R.string.recorder_type) + ": " + list.get(0) + "\n";
            bodyText +=  getResources().getString(R.string.type) + ": " + list.get(1) + "\n";
            bodyText += getResources().getString(R.string.reference_id) + ": " + list.get(2) + "\n";
        }
        mDialogBody.setText(bodyText);
        mPositiveButton = (Button) ret.findViewById(R.id.positive_button);
        mPositiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return ret;
    }
}

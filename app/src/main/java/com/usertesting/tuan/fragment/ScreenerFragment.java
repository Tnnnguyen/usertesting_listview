package com.usertesting.tuan.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.usertesting.tuan.activity.R;
import com.usertesting.tuan.data.Answer;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ScreenerFragment extends Fragment {

    public static final String TAG = ScreenerFragment.class.getSimpleName();
    public static final String BUNDLE_KEY_ANSWERS = TAG +  "BUNDLE_KEY_ANSWERS";
    public static final String BUNDLE_KEY_QUESTION = TAG + "BUNDLE_KEY_QUESTION";
    @Bind(R.id.screener_question) TextView mScreenerTv;
    @Bind(R.id.radio_group) RadioGroup mRadioGroup;
    @Bind(R.id.screener_submit_button) Button mSubmitButton;
    @OnClick(R.id.screener_submit_button)
    void submit() {
        //submit data here, then dismiss self
        getFragmentManager().popBackStack();
    }

    private String mScreenerQuestion;
    private List<Answer> mAnswerList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if(args != null) {
            if(args.getParcelableArrayList(BUNDLE_KEY_ANSWERS) != null) {
                mAnswerList = args.getParcelableArrayList(BUNDLE_KEY_ANSWERS);
            }
            if(args.getString(BUNDLE_KEY_QUESTION) != null) {
                mScreenerQuestion = args.getString(BUNDLE_KEY_QUESTION);
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.screener_layout, container, false);
        ButterKnife.bind(this, rootView);
        mScreenerTv.setText(mScreenerQuestion);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ButterKnife.bind(getActivity());
        for(int i = 0; i < mAnswerList.size(); i++) {
            RadioButton button = new RadioButton(getActivity());
            button.setId(i);
            button.setText(mAnswerList.get(i).getAnswerText());
            mRadioGroup.addView(button);
        }
    }

    public static ScreenerFragment newInstance(List<Answer> list, String question) {
        ScreenerFragment frag = new ScreenerFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(BUNDLE_KEY_ANSWERS, (ArrayList)list);
        bundle.putString(BUNDLE_KEY_QUESTION, question);
        frag.setArguments(bundle);
        return frag;
    }
}

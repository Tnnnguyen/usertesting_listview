package com.usertesting.tuan.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.usertesting.tuan.data.Answer;
import com.usertesting.tuan.fragment.ScreenerFragment;
import com.usertesting.tuan.fragment.TestListFragment;

import java.util.List;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentTransaction ft = getFragmentTransaction();
        ft.add(R.id.main_container, TestListFragment.newInstance(), TestListFragment.TAG).commit();
    }

    @Override
    public void onBackPressed() {
        if(!getFragmentManager().popBackStackImmediate()) {
            super.onBackPressed();
        }
    }

    /**
     * Show ScreenerFragment
     * @param list the list of questions for this screener
     * @param question the question of the screener
     */
    public void showScreenerFragment(List<Answer> list, String question) {
        FragmentTransaction ft = getFragmentTransaction();
        ft.add(R.id.main_container, ScreenerFragment.newInstance(list, question), ScreenerFragment.TAG)
                .addToBackStack(null).commit();
    }

    /**
     * Get a FragmentTransaction instance
     * @return instance of FragmentTraansaction
     */
    public FragmentTransaction getFragmentTransaction() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        return ft;
    }
}

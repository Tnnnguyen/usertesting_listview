package com.usertesting.tuan.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.usertesting.tuan.Utils;
import com.usertesting.tuan.activity.MainActivity;
import com.usertesting.tuan.activity.R;
import com.usertesting.tuan.data.Answer;
import com.usertesting.tuan.data.NextQuestion;
import com.usertesting.tuan.data.Screener;
import com.usertesting.tuan.data.TestItem;
import com.usertesting.tuan.fragment.dialog.OkDialog;
import com.usertesting.tuan.webservice.ApiService;

import org.apache.commons.lang3.text.WordUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;
import retrofit.RestAdapter;
import retrofit.RetrofitError;


/**
 * url to use:
 * https://s3-us-west-1.amazonaws.com/candidate-test/sample_json
 */
public class TestListFragment extends Fragment {

    public static final String TAG = TestListFragment.class.getSimpleName();
    private String url = "https://s3-us-west-1.amazonaws.com/candidate-test";
    private TestListAdapter mAdapter;
    List<TestItem> mTestItemList;
    @Bind(R.id.list_view) ListView mListView;
    @Bind(R.id.loading_wheel) ProgressBar mLoadingWheel;
    @BindString(R.string.this_test_for_you) String testForYou;
    @BindString(R.string.contact_for_assistance) String contactAssitance;
    @BindString(R.string.test) String test;
    @BindString(R.string.introduction) String introduction;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initListView();
        mAdapter = new TestListAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.list_fragment_layout, container, false);
        ButterKnife.bind(this, rootView);
        mListView = (ListView)rootView.findViewById(R.id.list_view);
        return rootView;
    }

    /**
     * Make a web service call and retrieve test data
     */
    private void initListView() {
        new AsyncTask<String, Void, List<TestItem>>(){
            @Override
            protected void onPreExecute() {
            }

            @Override
            protected void onPostExecute(List<TestItem> items) {
                mLoadingWheel.setVisibility(View.GONE);
                if(items != null) {
                    mTestItemList = items;
                    mListView.setAdapter(mAdapter);
                }
            }

            @Override
            protected List<TestItem> doInBackground(String... params) {
                List<TestItem> items = null;
                try {
                    RestAdapter.Builder restBuilder = new RestAdapter.Builder()
                            .setEndpoint(params[0]);
                    RestAdapter adapter = restBuilder.build();
                    ApiService service = adapter.create(ApiService.class);
                    items = service.getTestItems();
                    //Per requirement: only display tests that are for Android OS and state: available
                    //remove unwanted tests from list
                    for(Iterator<TestItem> iter = items.iterator(); iter.hasNext(); ) {
                        TestItem item = iter.next();
                        if (!item.getState().equals(TestItem.STATE_AVAILABLE) || !item.getOperatingSystems().contains(TestItem.OS_ANDROID)) {
                            iter.remove();
                        }
                    }
                }
                catch (RetrofitError e) {
                    e.printStackTrace();
                }

                return items;
            }
        }.execute(url);
    }

    public class TestListAdapter extends BaseAdapter {

        public TestListAdapter() {
        }

        @Override
        public long getItemId(int position) {
            return mTestItemList.indexOf(getItem(position));
        }

        @Override
        public int getCount() {
            return mTestItemList.size();
        }

        @Override
        public Object getItem(int position) {
            return mTestItemList.get(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TestItem item = mTestItemList.get(position);
            TestViewHolder holder = new TestViewHolder();
            if(convertView == null) {
                convertView = LayoutInflater.from(getActivity()).inflate(R.layout.test_item, null);
                convertView.setTag(holder);
            }
            else {
                holder = (TestViewHolder) convertView.getTag();
            }
            holder.position = position;
            holder.header = (TextView) convertView.findViewById(R.id.test_item_heading);
            holder.body = (TextView) convertView.findViewById(R.id.test_item_body);
            holder.declineButton = (TextView) convertView.findViewById(R.id.test_item_decline_button);
            holder.acceptButton = (Button) convertView.findViewById(R.id.test_item_accept_button);
            holder.takeScreenerButton = (Button) convertView.findViewById(R.id.test_item_take_screener_button);
            holder.divider = (ImageView) convertView.findViewById(R.id.divider);

            String state = item.getState();
            if(state.equals(TestItem.STATE_RESERVED)){
                holder.header.setText(WordUtils.capitalizeFully(state));
                holder.header.setBackgroundColor(getResources().getColor(R.color.gray));
                holder.declineButton.setVisibility(View.GONE);
                holder.acceptButton.setVisibility(View.GONE);
                holder.divider.setVisibility(View.GONE);
                String thisTestForYou = testForYou;
                if(item.getReferenceId() != null) {
                    thisTestForYou += " " + item.getReferenceId();
                }
                //trying to match sample screen shot, different text style in same TextView
                int firstMark = thisTestForYou.length();
                String bodyText = thisTestForYou + "\n" + contactAssitance;
                SpannableString spStr = new SpannableString(bodyText);
                spStr.setSpan(new RelativeSizeSpan(.8f), firstMark, (spStr.length()), 0);
                int lightGray = getResources().getColor(R.color.light_gray);
                spStr.setSpan(new ForegroundColorSpan(lightGray), firstMark, (spStr.length()), 0);
                holder.body.setText(spStr);
            }
            //state = available
            else {
                String type = WordUtils.capitalizeFully(item.getType());
                holder.header.setText(type + " " + test);
                holder.header.setBackgroundColor(getResources().getColor(R.color.user_testing_blue));
                holder.divider.setVisibility(View.VISIBLE);
                Screener screener = item.getScreener();
                NextQuestion nextQuestion = null;
                if(screener != null) {
                    nextQuestion = screener.getNextQuestion();
                }
                if(nextQuestion != null) {
                    //show screener button
                    holder.takeScreenerButton.setVisibility(View.VISIBLE);
                    holder.takeScreenerButton.setTag(position);
                    holder.takeScreenerButton.setOnClickListener(takeScreenerOnClickListener);
                }
                else {
                    //show decline & accept buttons
                    TextView tvAccept = holder.acceptButton;
                    tvAccept.setVisibility(View.VISIBLE);
                    tvAccept.setTag(position);
                    tvAccept.setOnClickListener(acceptTestOnClickListener);
                    TextView tvDecline = holder.declineButton;
                    tvDecline.setVisibility(View.VISIBLE);
                    tvDecline.setTag(position);
                    tvDecline.setOnClickListener(declineOnClickListener);
                }
                String bodyText = introduction + ": " + Utils.capFirstLetter(item.getIntroduction());
                bodyText += "\nOS: ";
                List<String> os = item.getOperatingSystems();
                for(int i = 0; i < os.size(); i++) {
                    if(i > 0) {
                        bodyText += ",";
                    }
                    bodyText += " " + WordUtils.capitalize(os.get(i));
                }
                holder.body.setText(bodyText);
            }
            return convertView;
        }
    }

    View.OnClickListener acceptTestOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showAcceptanceRequirement((Integer) v.getTag());
        }
    };

    View.OnClickListener declineOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            removeItemOnDecline((Integer)v.getTag());
         }
    };

    View.OnClickListener takeScreenerOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showScreener((Integer)v.getTag());
        }
    };

    /**
     * Show screener question and answer for this test
     * @param position the position of the test item in the list
     */
    private void showScreener(int position) {
        NextQuestion nq = mTestItemList.get(position).getScreener().getNextQuestion();
        ArrayList<Answer> list = (ArrayList)nq.getAnswersList();
        String question = Utils.capFirstLetter(nq.getQuestion());
        MainActivity activity = (MainActivity)getActivity();
        if(activity != null && !activity.isFinishing()) {
            activity.showScreenerFragment(list, question);
        }
    }

    /**
     * Show additional information on a test
     * @param position the position of the test item in the list
     */
    private void showAcceptanceRequirement(int position) {
        FragmentManager fm = getFragmentManager();
        ArrayList<String> list = new ArrayList<>();
        TestItem item = mTestItemList.get(position);
        list.add(item.getRecorderType());
        list.add(item.getType());
        list.add(item.getReferenceId());
        OkDialog dialog = OkDialog.newInstance(list);
        dialog.show(fm, OkDialog.TAG);
    }

    /**
     * Remove a test when decline button is clicked
     * @param position the position of the test item in the list
     */
    private void removeItemOnDecline(int position) {
        mTestItemList.remove(position);
        mAdapter.notifyDataSetChanged();
    }

    static class TestViewHolder {
        TextView header;
        TextView body;
        Button acceptButton;
        TextView declineButton;
        Button takeScreenerButton;
        ImageView divider;
        int position;
    }

    public static TestListFragment newInstance() {
        return new TestListFragment();
    }
}

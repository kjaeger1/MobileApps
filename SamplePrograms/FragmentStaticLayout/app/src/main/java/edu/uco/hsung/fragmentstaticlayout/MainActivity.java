//
// This example is adopted from Android Programming course
//  www.coursera.com
//

package edu.uco.hsung.fragmentstaticlayout;

import android.app.Activity;
import android.os.Bundle;

import edu.uco.hsung.fragmentstaticlayout.TitlesFragment.ListSelectionListener;

public class MainActivity extends Activity implements
        ListSelectionListener {

    public static String[] mTitleArray;
    public static String[] mQuoteArray;
    private QuotesFragment mDetailsFragment;

    private static final String TAG = "FRAGMENTSTATICLAYOUT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitleArray = getResources().getStringArray(R.array.Titles);
        mQuoteArray = getResources().getStringArray(R.array.Quotes);

        setContentView(R.layout.activity_main);

        mDetailsFragment = (QuotesFragment) getFragmentManager()
                .findFragmentById(R.id.details);
    }

    @Override
    public void onListSelection(int index) {
        if (mDetailsFragment.getShownIndex() != index) {
            mDetailsFragment.showQuoteAtIndex(index);
        }
    }

}
package edu.uco.hsung.fragmentdynamiclayout;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class QuotesFragment extends Fragment {

	private static final String TAG = "QuotesFragment";

	private TextView mQuoteView = null;
	private int mCurrIdx = -1;
	private int mQuoteArrLen;

	int getShownIndex() {
		return mCurrIdx;
	}

	void showIndex(int newIndex) {
		if (newIndex < 0 || newIndex >= mQuoteArrLen)
			return;
		mCurrIdx = newIndex;
		mQuoteView.setText(MainActivity.mQuoteArray[mCurrIdx]);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.i(TAG, getClass().getSimpleName() + ":entered onCreateView()");

		return inflater.inflate(R.layout.quote_fragment,
				container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		Log.i(TAG, getClass().getSimpleName() + ":entered onActivityCreated()");
		super.onActivityCreated(savedInstanceState);

		mQuoteView = (TextView) getActivity().findViewById(R.id.quoteView);
		mQuoteArrLen = MainActivity.mQuoteArray.length;
	}

}

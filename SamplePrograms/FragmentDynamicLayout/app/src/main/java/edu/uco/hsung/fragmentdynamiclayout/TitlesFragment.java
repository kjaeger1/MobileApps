package edu.uco.hsung.fragmentdynamiclayout;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TitlesFragment extends ListFragment {
	private static final String TAG = "TitlesFragment";
	private ListSelectionListener mListener = null;

	public interface ListSelectionListener {
		public void onListSelection(int index);
	}

	@Override
	public void onListItemClick(ListView l, View v, int pos, long id) {
		getListView().setItemChecked(pos, true);
		mListener.onListSelection(pos);
	}

	@Override
	public void onActivityCreated(Bundle savedState) {
		super.onActivityCreated(savedState);
		
		setListAdapter(new ArrayAdapter<String>(getActivity(),
				R.layout.title_item, MainActivity.mTitleArray));
		getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);

		try {
			mListener = (ListSelectionListener) getActivity();
		} catch (ClassCastException e) {
			throw new ClassCastException(getActivity().toString()
					+ " must implement OnArticleSelectedListener");
		}
	}

}
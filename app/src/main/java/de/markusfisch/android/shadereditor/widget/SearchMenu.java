package de.markusfisch.android.shadereditor.widget;

import android.support.annotation.NonNull;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;

import de.markusfisch.android.shadereditor.R;

public class SearchMenu {
	public interface OnSearchListener {
		void filter(String value);
	}

	public static void addSearchMenu(@NonNull Menu menu,
			MenuInflater inflater,
			OnSearchListener onSearchListener) {
		inflater.inflate(R.menu.search, menu);
		SearchView searchView = (SearchView)
				MenuItemCompat.getActionView(menu.findItem(R.id.search));
		searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
			@Override
			public boolean onQueryTextChange(String newText) {
				onSearchListener.filter(newText);
				return false;
			}

			@Override
			public boolean onQueryTextSubmit(String query) {
				onSearchListener.filter(query);
				return true;
			}
		});
		searchView.setOnCloseListener(() -> {
			onSearchListener.filter(null);
			return false;
		});
	}
}

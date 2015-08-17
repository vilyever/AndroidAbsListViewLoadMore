package com.vilyever.abslistviewloadmore;

import android.widget.AbsListView;

/**
 * AbsListView_VDLoadMore
 * AndroidAbsListViewLoadMore <com.vilyever.abslistviewloadmore>
 * Created by vilyever on 2015/8/17.
 * Feature:
 */
public class AbsListView_VDLoadMore implements AbsListView.OnScrollListener {
    private final AbsListView_VDLoadMore self = this;

    private LoadMoreDelegate delegate;
    private AbsListView.OnScrollListener onScrollListener;

    private boolean isScrollToLastItem = false;


    /* #Constructors */
    public AbsListView_VDLoadMore() {
        // Required empty public constructor
    }
    
    
    /* #Overrides */    
    
    /* #Accessors */     
     
    /* #Delegates */
    // AbsListView.OnScrollListener
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (null != self.onScrollListener) {
            self.onScrollListener.onScrollStateChanged(view, scrollState);
        }

        if (null != self.delegate) {
            if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && self.isScrollToLastItem) {
                self.delegate.requireLoadMoreFromAbsListView(view);
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (null != self.onScrollListener) {
            self.onScrollListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
        }

        self.isScrollToLastItem = (totalItemCount > 0) && (firstVisibleItem + visibleItemCount >= totalItemCount - 1);
    }

    /* #Private Methods */    
    
    /* #Public Methods */
    public static void loadMoreDelegate(AbsListView absListView, LoadMoreDelegate delegate) {
        loadMoreDelegate(absListView, delegate, null);
    }

    public static void loadMoreDelegate(AbsListView absListView, LoadMoreDelegate delegate, AbsListView.OnScrollListener originalOnScrollListener) {
        AbsListView_VDLoadMore loadMore = new AbsListView_VDLoadMore();
        loadMore.delegate = delegate;
        loadMore.onScrollListener = originalOnScrollListener;

        absListView.setOnScrollListener(loadMore);
    }

    /* #Classes */

    /* #Interfaces */
    public interface LoadMoreDelegate {
        void requireLoadMoreFromAbsListView(AbsListView absListView);
    }
     
    /* #Annotations @interface */    
    
    /* #Enums */
}
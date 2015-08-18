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

    private Delegate delegate;
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
        if (self.onScrollListener != null) { // 若传入onScrollListener不为空，调用改Listener的相应方法
            self.onScrollListener.onScrollStateChanged(view, scrollState);
        }

        if (self.delegate != null) {
            // 在没有滑动的情况下，且最后一个item可见，触发回调
            if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && self.isScrollToLastItem) {
                self.delegate.requireLoadMoreFromAbsListView(view);
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (self.onScrollListener != null) { // 若传入onScrollListener不为空，调用改Listener的相应方法
            self.onScrollListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
        }

        self.isScrollToLastItem = (totalItemCount > 0) && (firstVisibleItem + visibleItemCount >= totalItemCount - 1);
    }

    /* #Private Methods */    
    
    /* #Public Methods */
    public static void addLoadMoreDelegate(AbsListView absListView, Delegate delegate) {
        addLoadMoreDelegate(absListView, delegate, null);
    }

    /**
     * @param absListView
     * @param delegate LoadMore回调
     * @param originalOnScrollListener 若需要监听onScrollListener
     */
    public static void addLoadMoreDelegate(AbsListView absListView, Delegate delegate, AbsListView.OnScrollListener originalOnScrollListener) {
        AbsListView_VDLoadMore loadMore = new AbsListView_VDLoadMore();
        loadMore.delegate = delegate;
        loadMore.onScrollListener = originalOnScrollListener;

        absListView.setOnScrollListener(loadMore);
    }

    /* #Classes */

    /* #Interfaces */
    public interface Delegate {
        void requireLoadMoreFromAbsListView(AbsListView absListView);
    }
     
    /* #Annotations @interface */    
    
    /* #Enums */
}
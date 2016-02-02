package com.vilyever.abslistviewloadmore;

import android.support.annotation.NonNull;
import android.widget.AbsListView;

/**
 * VDAbsListViewLoadMore
 * AndroidAbsListViewLoadMore <com.vilyever.abslistviewloadmore>
 * Created by vilyever on 2015/8/17.
 * Feature:
 */
public class VDAbsListViewLoadMore implements AbsListView.OnScrollListener {
    private final VDAbsListViewLoadMore self = this;

    /* Public Methods */
    /** @see #addLoadMoreDelegate(AbsListView, LoadMoreDelegate, AbsListView.OnScrollListener)  */
    public static VDAbsListViewLoadMore addLoadMoreDelegate(@NonNull AbsListView absListView, @NonNull LoadMoreDelegate delegate) {
        return addLoadMoreDelegate(absListView, delegate, null);
    }

    /**
     * 设置监听
     * @param absListView 监听的视图
     * @param delegate LoadMore回调
     * @param originalOnScrollListener 若需要监听onScrollListener
     */
    public static VDAbsListViewLoadMore addLoadMoreDelegate(@NonNull AbsListView absListView, @NonNull LoadMoreDelegate delegate, AbsListView.OnScrollListener originalOnScrollListener) {
        VDAbsListViewLoadMore loadMore = new VDAbsListViewLoadMore();
        loadMore.setDelegate(delegate);
        loadMore.setOnScrollListener(originalOnScrollListener);

        absListView.setOnScrollListener(loadMore);

        return loadMore;
    }

    /* Properties */
    public interface LoadMoreDelegate {
        void requireLoadMore(AbsListView absListView);
    }
    public class SimpleLoadMoreDelegate implements LoadMoreDelegate {
        @Override
        public void requireLoadMore(AbsListView absListView) {
        }
    }
    private LoadMoreDelegate delegate;
    public VDAbsListViewLoadMore setDelegate(LoadMoreDelegate delegate) {
        this.delegate = delegate;
        return this;
    }
    public LoadMoreDelegate getDelegate() {
        if (delegate == null) {
            delegate = new SimpleLoadMoreDelegate();
        }
        return delegate;
    }

    private AbsListView.OnScrollListener onScrollListener;
    public VDAbsListViewLoadMore setOnScrollListener(AbsListView.OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
        return this;
    }
    public AbsListView.OnScrollListener getOnScrollListener() {
        return onScrollListener;
    }

    private boolean isScrollToLastItem;
    private VDAbsListViewLoadMore setIsScrollToLastItem(boolean isScrollToLastItem) {
        this.isScrollToLastItem = isScrollToLastItem;
        return this;
    }
    private boolean isScrollToLastItem() {
        return isScrollToLastItem;
    }

    /* Delegates */
    /** {@link android.widget.AbsListView.OnScrollListener} */
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (self.getOnScrollListener() != null) { // 若传入onScrollListener不为空，调用该Listener的相应方法
            self.getOnScrollListener().onScrollStateChanged(view, scrollState);
        }

        // 在没有滑动的情况下，且最后一个item可见，触发回调
        if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && self.isScrollToLastItem()) {
            self.getDelegate().requireLoadMore(view);
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (self.getOnScrollListener() != null) { // 若传入onScrollListener不为空，调用该Listener的相应方法
            self.getOnScrollListener().onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
        }

        self.setIsScrollToLastItem((totalItemCount > 0) && (firstVisibleItem + visibleItemCount >= totalItemCount - 1));
    }
    /** {@link android.widget.AbsListView.OnScrollListener} */

}
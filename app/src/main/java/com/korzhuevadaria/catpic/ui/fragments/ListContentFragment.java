package com.korzhuevadaria.catpic.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.korzhuevadaria.catpic.BuildConfig;
import com.korzhuevadaria.catpic.R;
import com.korzhuevadaria.catpic.adapters.ListAdapter;
import com.korzhuevadaria.catpic.models.PhotoItem;
import com.korzhuevadaria.catpic.models.VkPhotosResponse;
import com.korzhuevadaria.catpic.network.NetworkService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListContentFragment extends Fragment {
    private static List<PhotoItem> mPhotoItems = new ArrayList<PhotoItem>();
    private static List<PhotoItem> mPreviousPhotos = new ArrayList<PhotoItem>();
    private RecyclerView mRecyclerView;
    private ListAdapter mListAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private LinearLayoutManager mLayoutManager;

    private int mLastVisibleItem;
    private int mTotalItemCount;
    private int mVisibleItems = 10;
    private int mLastitem = 10;
    private int mCurrentItemsCount = 0;
    private boolean isLoading = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.recycler_view, container, false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.my_recycler_view);
        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mListAdapter = new ListAdapter(getContext(), new ArrayList<PhotoItem>());
        mRecyclerView.setAdapter(mListAdapter);
        mRecyclerView.setLayoutManager(mLayoutManager);
        sendRequest();

        mSwipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (mPhotoItems.size() == 0) {
                    sendRequest();
                    mSwipeRefreshLayout.setRefreshing(false);
                } else {
                    loadNextItems();
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }
        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mTotalItemCount = mLayoutManager.getItemCount();
                mLastVisibleItem = mLayoutManager.findLastVisibleItemPosition();

                if (mLastVisibleItem == mPhotoItems.size() - 1) {
                    if (isLoading) {
                        isLoading = false;
                        loadPreviousItems();
                    }
                }
            }
        });

        return v;
    }

    private void sendRequest() {
        NetworkService.getInstance()
                .getVkApi()
                .getWallPhotos("-130670107", "wall", 1, 10, BuildConfig.VKApiKey, "5.101")
                .enqueue(new Callback<VkPhotosResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<VkPhotosResponse> call, @NonNull Response<VkPhotosResponse> response) {
                        if (response.isSuccessful()) {
                            VkPhotosResponse photo = response.body();
                            mPhotoItems = photo.response.getItems();
                            mCurrentItemsCount = photo.response.getCount();
                            writeRecycler(mPhotoItems);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<VkPhotosResponse> call, @NonNull Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

    /* Загрузка новых элементов при свайпе вниз */
    private void loadNextItems() {
        NetworkService.getInstance()
                .getVkApi()
                .getFromPositionWallPhotos("-130670107", "wall", 0, 1000, mCurrentItemsCount - 1, BuildConfig.VKApiKey, "5.101")
                .enqueue(new Callback<VkPhotosResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<VkPhotosResponse> call, @NonNull Response<VkPhotosResponse> response) {
                        if (response.isSuccessful()) {
                            VkPhotosResponse photo = response.body();
                            if (photo.response.getCount() != mCurrentItemsCount) {
                                List<PhotoItem> mNextPhotos = photo.response.getItems();

                                Collections.reverse(mNextPhotos);
                                mNextPhotos.addAll(mPhotoItems);
                                mPhotoItems = mNextPhotos;

                                mCurrentItemsCount = photo.response.getCount();
                                mListAdapter.notifyDataSetChanged();

                                mLastitem += 10;
                            }

                            writeRecycler(mPhotoItems);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<VkPhotosResponse> call, @NonNull Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

    private void loadPreviousItems() {
        NetworkService.getInstance()
                .getVkApi()
                .getFromPositionWallPhotos("-130670107", "wall", 1, 10, mLastitem, BuildConfig.VKApiKey, "5.101")
                .enqueue(new Callback<VkPhotosResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<VkPhotosResponse> call, @NonNull Response<VkPhotosResponse> response) {
                        if (response.isSuccessful()) {
                            VkPhotosResponse photo = response.body();
                            mPreviousPhotos = photo.response.getItems();

                            if (photo.response.getCount() > mLastitem) {
                                mPhotoItems.addAll(mPreviousPhotos);

                                mListAdapter.notifyDataSetChanged();

                                mLastitem += 10;

                                isLoading = true;
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<VkPhotosResponse> call, @NonNull Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

    private void writeRecycler(List<PhotoItem> photos) {
        mListAdapter = new ListAdapter(getContext(), photos);
        mRecyclerView.setAdapter(mListAdapter);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mSwipeRefreshLayout.setRefreshing(false);
    }
}

package com.zhixin.richard.s01e03.base;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.zhixin.richard.s01e03.R;
import com.zhixin.richard.s01e03.activity.user.LoginActivity;
import com.zhixin.richard.s01e03.cache.CacheManager;
import com.zhixin.richard.s01e03.constants.user.UserConstants;
import com.zhixin.richard.s01e03.model.Base;
import com.zhixin.richard.s01e03.model.ListEntity;
import com.zhixin.richard.s01e03.model.Result;
import com.zhixin.richard.s01e03.model.tips.TipMoneyList;
import com.zhixin.richard.s01e03.ui.empty.EmptyLayout;
import com.zhixin.richard.s01e03.utils.TDevice;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by Administrator on 2016-03-15.
 */
public abstract class BaseListFragment extends BaseTabFragment implements PullToRefreshBase.OnRefreshListener<ListView>,PullToRefreshBase.OnLastItemVisibleListener,
        AdapterView.OnItemClickListener {
    public static final String BUNDLE_KEY_CATALOG = "BUNDLE_KEY_CATALOG";
    protected PullToRefreshListView mListView;
    protected ListBaseAdapter mAdapter;
    protected EmptyLayout mErrorLayout;
    protected int mStoreEmptyState = -1;

    protected int mCurrentPage = 0;
    protected int mCatalog = TipMoneyList.CATALOG_ALL;

    private AsyncTask<String, Void, ListEntity> mCacheTask;
    private ParserTask mParserTask;

    protected int getLayoutRes() {
        return R.layout.fragment_pull_refresh_listview;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            mCatalog = args.getInt(BUNDLE_KEY_CATALOG);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutRes(), container, false);
        initViews(view);
        return view;
    }


    protected void initViews(View view) {
        mErrorLayout = (EmptyLayout) view.findViewById(R.id.error_layout);
        mErrorLayout.setOnLayoutClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mCurrentPage = 0;
                mState = STATE_REFRESH;
                mErrorLayout.setErrorType(EmptyLayout.NETWORK_LOADING);
                requestData(true);
            }
        });
        mListView = (PullToRefreshListView) view.findViewById(R.id.listview);

        mListView.setOnItemClickListener(this);
        mListView.setOnRefreshListener(this);
        mListView.setOnLastItemVisibleListener(this);

        if (mAdapter != null) {
            mListView.setAdapter(mAdapter);
            mErrorLayout.setErrorType(EmptyLayout.HIDE_LAYOUT);
        } else {
            mAdapter = getListAdapter();
            // mListView.setRefreshing();
            mListView.setAdapter(mAdapter);

            if (requestDataIfViewCreated()) {
                mCurrentPage = 0;
                mState = STATE_REFRESH;
                mErrorLayout.setErrorType(EmptyLayout.NETWORK_LOADING);
                requestData(true);
            } else {
                mErrorLayout.setErrorType(EmptyLayout.HIDE_LAYOUT);
            }
        }
        if (mStoreEmptyState != -1) {
            mErrorLayout.setErrorType(mStoreEmptyState);
        }
    }

    @Override
    public void onDestroyView() {
        mStoreEmptyState = mErrorLayout.getErrorState();
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        cancelReadCacheTask();
        cancelParserTask();
        super.onDestroy();
    }

    protected abstract ListBaseAdapter getListAdapter();

    protected boolean requestDataIfViewCreated() {
        return true;
    }

    protected String getCacheKeyPrefix() {
        return null;
    }

    protected ListEntity parseList(InputStream is) throws Exception {
        return null;
    }

    protected ListEntity parseList(JSONObject jsonObject) throws Exception {
        return null;
    }

    protected ListEntity readList(Serializable seri) {
        return null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
    }

    @Override
    public void onRefresh(PullToRefreshBase<ListView> refreshView) {
        mCurrentPage = 0;
        mState = STATE_REFRESH;
        requestData(true);
    }

    @Override
    public void onLastItemVisible() {
        if (mState == STATE_NONE) {
            if (mAdapter.getState() == ListBaseAdapter.STATE_LOAD_MORE) {
                mCurrentPage++;
                mState = STATE_LOADMORE;
                requestData(false);
            }
        }
    }

    private String getCacheKey() {
        return new StringBuffer(getCacheKeyPrefix()).append(mCatalog)
                .append("_").append(mCurrentPage).append("_")
                .append(TDevice.getPageSize()).toString();
    }

    protected void requestData(boolean refresh) {
        String key = getCacheKey();
        if (TDevice.hasInternet()
                && (!CacheManager.isReadDataCache(getActivity(), key) || refresh)) {
            sendRequestData();
        } else {
            readCacheData(key);
        }
    }

    protected void sendRequestData() {
    }

    private void readCacheData(String cacheKey) {
        cancelReadCacheTask();
        mCacheTask = new CacheTask(getActivity()).execute(cacheKey);
    }
    private void cancelReadCacheTask() {
        if (mCacheTask != null) {
            mCacheTask.cancel(true);
            mCacheTask = null;
        }
    }
    private class CacheTask extends AsyncTask<String, Void, ListEntity> {
        private WeakReference<Context> mContext;

        private CacheTask(Context context) {
            mContext = new WeakReference<Context>(context);
        }

        @Override
        protected ListEntity doInBackground(String... params) {
            Serializable seri = CacheManager.readObject(mContext.get(),
                    params[0]);
            if (seri == null) {
                return null;
            } else {
                return readList(seri);
            }
        }

        @Override
        protected void onPostExecute(ListEntity list) {
            super.onPostExecute(list);
            if (list != null) {
                executeOnLoadDataSuccess(list.getList());
            } else {
                executeOnLoadDataError(null);
            }
            executeOnLoadFinish();
        }
    }

    private class SaveCacheTask extends AsyncTask<Void, Void, Void> {
        private WeakReference<Context> mContext;
        private Serializable seri;
        private String key;

        private SaveCacheTask(Context context, Serializable seri, String key) {
            mContext = new WeakReference<Context>(context);
            this.seri = seri;
            this.key = key;
        }

        @Override
        protected Void doInBackground(Void... params) {
            CacheManager.saveObject(mContext.get(), seri, key);
            return null;
        }
    }
    protected JsonHttpResponseHandler mHandler = new JsonHttpResponseHandler(){
        @Override
        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
            if(isAdded()){
                Gson gson = new Gson();
                try {
                    Result result = gson.fromJson(response.getString("result").toString(), Result.class);
                    if(result.getErrorCode()== UserConstants.login_code_right){
                        if(mState == STATE_REFRESH){
                            onRefreshNetworkSuccess();
                        }

                        //对数据进行护理
                        executeParserTask(response);
                    }else{
                        Intent intent = new Intent(getContext(), LoginActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }


        @Override
        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
            if (isAdded()) {
                readCacheData(getCacheKey());
            }
        }

        @Override
        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
            if (isAdded()) {
                readCacheData(getCacheKey());
            }
        }

        @Override
        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
            if (isAdded()) {
                readCacheData(getCacheKey());
            }
        }
    };

//    protected AsyncHttpResponseHandler mHandler = new AsyncHttpResponseHandler() {
//
//        @Override
//        public void onSuccess(int statusCode, Header[] headers,
//                              byte[] responseBytes) {
//            if (isAdded()) {
//                if (mState == STATE_REFRESH) {
//                    onRefreshNetworkSuccess();
//                }
//                executeParserTask(responseBytes);
//            }
//        }
//
//        @Override
//        public void onFailure(int arg0, Header[] arg1, byte[] arg2,
//                              Throwable arg3) {
//            if (isAdded()) {
//                readCacheData(getCacheKey());
//            }
//        }
//    };

    protected void executeOnLoadDataSuccess(List<?> data) {
        if (mState == STATE_REFRESH)
            mAdapter.clear();
        mAdapter.addData(data);
        mErrorLayout.setErrorType(EmptyLayout.HIDE_LAYOUT);
        if (data.size() == 0 && mState == STATE_REFRESH) {
            //TODO 修改内容
            //mErrorLayout.setErrorType(EmptyLayout.NODATA);
            mErrorLayout.setErrorType(EmptyLayout.NODATA_ENABLE_CLICK);
        } else if (data.size() < TDevice.getPageSize()) {
            if (mState == STATE_REFRESH)
                mAdapter.setState(ListBaseAdapter.STATE_NO_MORE);
            else
                mAdapter.setState(ListBaseAdapter.STATE_NO_MORE);
        } else {
            mAdapter.setState(ListBaseAdapter.STATE_LOAD_MORE);
        }
    }

    protected void onRefreshNetworkSuccess() {

    }

    protected void executeOnLoadDataError(String error) {
        if (mCurrentPage == 0) {
            mErrorLayout.setErrorType(EmptyLayout.NETWORK_ERROR);
        } else {
            mErrorLayout.setErrorType(EmptyLayout.HIDE_LAYOUT);
            mAdapter.setState(ListBaseAdapter.STATE_NETWORK_ERROR);
            mAdapter.notifyDataSetChanged();
        }
    }

    protected void executeOnLoadFinish() {
        mListView.onRefreshComplete();
        mState = STATE_NONE;
    }

//    private void executeParserTask(byte[] data) {
//        cancelParserTask();
//        mParserTask = new ParserTask(data);
//        mParserTask.execute();
//    }
    private void executeParserTask(JSONObject response){
        cancelParserTask();
        mParserTask = new ParserTask(response);
        mParserTask.execute();
    }

    private void cancelParserTask() {
        if (mParserTask != null) {
            mParserTask.cancel(true);
            mParserTask = null;
        }
    }

    class ParserTask extends  AsyncTask<Void,Void,String>{
        private JSONObject jsonObject;
        private boolean parserError;
        private List<?> list;

        public ParserTask(JSONObject jsonObject){
            this.jsonObject = jsonObject;
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                ListEntity data = parseList(jsonObject);
                if (data instanceof Base) {
                    //TODO  Notice notice = ((Base) data).getNotice();
//                    if (notice != null) {
//                        UIHelper.sendBroadCast(getActivity(), notice);
//                    }
                }
                if(data.getList() ==null){
                    list = null;
                }else {
                    new SaveCacheTask(getActivity(), data, getCacheKey()).execute();
                    list = data.getList();
                }
            }catch (Exception e) {
                e.printStackTrace();
                parserError = true;
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (parserError) {
                readCacheData(getCacheKey());
            } else {
                executeOnLoadDataSuccess(list);
                executeOnLoadFinish();
            }
        }
    }


//    class ParserTask extends AsyncTask<Void, Void, String> {
//
//        private byte[] reponseData;
//        private boolean parserError;
//        private List<?> list;
//
//        public ParserTask(byte[] data) {
//            this.reponseData = data;
//        }
//
//        @Override
//        protected String doInBackground(Void... params) {
//            try {
//                ListEntity data = parseList(new ByteArrayInputStream(
//                        reponseData));
//                if (data instanceof Base) {
//                   //TODO  Notice notice = ((Base) data).getNotice();
////                    if (notice != null) {
////                        UIHelper.sendBroadCast(getActivity(), notice);
////                    }
//                }
//                new SaveCacheTask(getActivity(), data, getCacheKey()).execute();
//                list = data.getList();
//            } catch (Exception e) {
//                e.printStackTrace();
//                parserError = true;
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//            super.onPostExecute(result);
//            if (parserError) {
//                readCacheData(getCacheKey());
//            } else {
//                executeOnLoadDataSuccess(list);
//                executeOnLoadFinish();
//            }
//        }
//    }
}



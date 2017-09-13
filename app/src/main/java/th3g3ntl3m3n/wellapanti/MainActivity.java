package th3g3ntl3m3n.wellapanti;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ListView listView;
    private String[] arrayOfTitles = {"Login/Register", "Place Ads", "Browse Ads", "FAQs", "Contact Us", "Terms & Conditions"};
    private RecyclerView mRecyclerView;
    private UserAdapter userAdapter;
    private Toolbar toolbar;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    private ArrayList<Album> allAlbums;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Hello There");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        allAlbums = new ArrayList<>();
        getSupportFragmentManager().beginTransaction().add(R.id.flContent, new Advertisement(), "TNC").commit();
        for (int i = 0; i < 30; i++) {
            allAlbums.add(new Album("Hellloooooolollol" + i, i, i));
        }
//        mRecyclerView = findViewById(R.id.recycleView);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        userAdapter = new UserAdapter();
//        mRecyclerView.setAdapter(userAdapter);
//        userAdapter.setOnLoadListener(new OnLoadListener() {
//            @Override
//            public void onLoadMore() {
//                Log.e("haint", "Load More");
//                allAlbums.add(null);
//                userAdapter.notifyItemInserted(allAlbums.size() - 1);
//                //Load more data for reyclerview
//                new Handler().postDelayed(new Runnable() {
//                    @Override public void run() {
//                        Log.e("haint", "Load More 2");
//                        //Remove loading item
//                        allAlbums.remove(allAlbums.size() - 1);
//                        userAdapter.notifyItemRemoved(allAlbums.size());
//                        //Load data
//                        int index = allAlbums.size();
//                        int end = index + 20;
//                        for (int i = index; i < end; i++) {
//                            allAlbums.add(new Album("hellooooooo " + i, i,i));
//                        }
//                        userAdapter.notifyDataSetChanged();
//                        userAdapter.setLoaded();
//                    }
//                }, 2000);
//            }
//        });
        drawerLayout = findViewById(R.id.drawer_layout);
        listView = findViewById(R.id.left_drawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }
        };
        listView.setOnItemClickListener(new DrawerItemClickListener());
        actionBarDrawerToggle.syncState();
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayOfTitles));
    }

    private void selectPosition(int position) {
        switch (position) {
            case 0:
                getSupportFragmentManager().beginTransaction().replace(R.id.flContent, new Authentication()).commit();
                getSupportActionBar().setTitle(arrayOfTitles[position]);
                break;
            case 1:
                getSupportFragmentManager().beginTransaction().replace(R.id.flContent, new PostAdvertisement()).commit();
                getSupportActionBar().setTitle(arrayOfTitles[position]);
                break;
            case 2:
                getSupportFragmentManager().beginTransaction().replace(R.id.flContent, new Advertisement()).commit();
                getSupportActionBar().setTitle(arrayOfTitles[position]);
                break;
            case 3:
                getSupportFragmentManager().beginTransaction().replace(R.id.flContent, new FAQ()).commit();
                getSupportActionBar().setTitle(arrayOfTitles[position]);
                break;
            case 4:
                getSupportFragmentManager().beginTransaction().replace(R.id.flContent, new ContactUs()).commit();
                getSupportActionBar().setTitle(arrayOfTitles[position]);
                break;
            case 5:
                getSupportFragmentManager().beginTransaction().replace(R.id.flContent, new TermsNConditions()).commit();
                getSupportActionBar().setTitle(arrayOfTitles[position]);
                break;
        }

        drawerLayout.closeDrawers();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

    static class LoadingViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public LoadingViewHolder(View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar1);
        }
    }


//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//        // If the nav drawer is open, hide action items related to the content view
//        boolean drawerOpen = drawerLayout.isDrawerOpen(arrayOfTitles);
//        menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
//        return super.onPrepareOptionsMenu(menu);
//    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;

        public UserViewHolder(View itemView) {
            super(itemView);
            tvName = null;
        }
    }

    private class DrawerItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            selectPosition(i);
        }
    }

    class UserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private final int VIEW_TYPE_ITEM = 0;
        private final int VIEW_TYPE_LOADING = 1;
        private OnLoadListener mOnLoadListener;
        private boolean isLoading;
        private int visibleThreshold = 5;
        private int lastVisibleItem, totalItemCount;

        public UserAdapter() {
            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
            mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    totalItemCount = linearLayoutManager.getItemCount();
                    lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                    if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                        if (mOnLoadListener != null) {
                            mOnLoadListener.onLoadMore();
                        }
                        isLoading = true;
                    }
                }
            });
        }

        public void setOnLoadListener(OnLoadListener mOnLoadMoreListener) {
            this.mOnLoadListener = mOnLoadMoreListener;
        }

        @Override
        public int getItemViewType(int position) {
            return allAlbums.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == VIEW_TYPE_ITEM) {
                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.album_item_layout, parent, false);
                return new UserViewHolder(view);
            } else if (viewType == VIEW_TYPE_LOADING) {
                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.loading_item_layout, parent, false);
                return new LoadingViewHolder(view);
            }
            return null;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof UserViewHolder) {
                Album album = allAlbums.get(position);
                UserViewHolder userViewHolder = (UserViewHolder) holder;
                userViewHolder.tvName.setText(album.getTitle());
            } else if (holder instanceof LoadingViewHolder) {
                LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
                loadingViewHolder.progressBar.setIndeterminate(true);
            }
        }

        @Override
        public int getItemCount() {
            return allAlbums == null ? 0 : allAlbums.size();
        }

        public void setLoaded() {
            isLoading = false;
        }
    }
}

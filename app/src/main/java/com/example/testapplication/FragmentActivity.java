package com.example.testapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.testapplication.Fragment.StoryDetailFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

public class FragmentActivity extends androidx.fragment.app.FragmentActivity {
    private FloatingActionButton button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpagerlayout);

        /*viewPager获取fragment*/
        ViewPager viewPager =findViewById(R.id.viewPagerContainer);
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        /*设置tablayout与view pager之间的联系*/
        TabLayout tabLayout = findViewById(R.id.tabs);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

        /*对应layout文件R.layout.fragmentlayout
        Bundle bundle = new Bundle();
        StoryDetailFragment fragment = new StoryDetailFragment();
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.story_detail_container,fragment).commit();
        */

        button = (FloatingActionButton) findViewById(R.id.floatButton);
        button.setOnClickListener(view -> {
            Snackbar.make(view,"Data deleted",Snackbar.LENGTH_SHORT)
                    .setAction("Undo", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(FragmentActivity.this,"Data restored",Toast.LENGTH_LONG).show();
                        }
                    }).show();
            //Toast.makeText(FragmentActivity.this,"现在是第"+viewPager.getCurrentItem()+"页",Toast.LENGTH_LONG).show();
        });
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter{
        SectionsPagerAdapter(FragmentManager fragmentManager){
            super(fragmentManager);
        }
        @Override
        public Fragment getItem(int position){
            return DummyFragment.newInstance(position+1);
        }
        @Override
        public int getCount(){
            return 3;
        }
    }

    public static class DummyFragment extends Fragment{
        private static final String ARG_SECTION_NUMBER ="section_number";
        public static DummyFragment newInstance(int sectionNumber){
            DummyFragment fragment = new DummyFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(ARG_SECTION_NUMBER,sectionNumber);
            fragment.setArguments(bundle);
            return fragment;
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.listviewitem,container,false);
            TextView textView = rootView.findViewById(R.id.ListViewName);
            textView.setText("fragment页面"+getArguments().getInt(ARG_SECTION_NUMBER,0));
            return rootView;
        }
    }
}

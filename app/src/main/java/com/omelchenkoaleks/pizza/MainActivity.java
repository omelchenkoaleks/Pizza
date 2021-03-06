package com.omelchenkoaleks.pizza;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private ShareActionProvider shareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // получаем ссылку на панель инструментов
        Toolbar toolbar = findViewById(R.id.toolbar);
        // назначаем панель инструментов панелью действий активности
        setSupportActionBar(toolbar);

        // связываем SectionsPagerAdapter с ViewPager
        SectionsPagerAdapter pagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.pager);
        viewPager.setAdapter(pagerAdapter);

        // связываем ViewPager с TabLayout
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // заполняем меню; элементы действий добавляются на панель приложения
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // получаем ссылку на провайдера передачи информации
        MenuItem menuItem = menu.findItem(R.id.action_share);
        // присваиваем ее переменной и вызываем метод
        shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
        setShareActionIntent("Want to join me for pizza?");

        return super.onCreateOptionsMenu(menu);
    }

    // метод, который создает интент и передает его провайдеру
    private void setShareActionIntent(String text) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        shareActionProvider.setShareIntent(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_create_order:
                // код выполнится при выборе элемента Create Order
                Intent intent = new Intent(this, OrderActivity.class);
                startActivity(intent);
                return true;

                default:
                    return super.onOptionsItemSelected(item);
        }
    }

    // добавляем адаптер страничного компонента фрагментов - для того, чтобы ViewPager мог
    // отображать каждый фрагмент постранично
    private class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new TopFragment();
                case 1:
                    return new PizzaFragment();
                case 2:
                    return new  PastaFragment();
                case 3:
                    return new StoresFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 4;
        }

        // метод адаптера, который нужно реализовать, чтобы добвавить текст на каждую вкладку
        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getResources().getText(R.string.home_tab);
                case 1:
                    return getResources().getText(R.string.pizza_tab);
                case 2:
                    return getResources().getText(R.string.pasta_tab);
                case 3:
                    return getResources().getText(R.string.store_tab);
            }
            return null;
        }
    }
}

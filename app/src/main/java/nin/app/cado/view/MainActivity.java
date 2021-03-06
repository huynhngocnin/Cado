package nin.app.cado.view;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.squareup.otto.Produce;

import nin.app.cado.R;
import nin.app.cado.Util.DateTimeUtil;
import nin.app.cado.event.DateFixturesChangedEvent;
import nin.app.cado.event.DateResultChangedEvent;
import nin.app.cado.helper.BusProvider;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager mFragmentManager;
    private Spinner spDate;
    private TextView tvTitle;
    private TextView tvMoney;
    private TextView tvCurrency;
    private ActionBar mActionBar;
    private Toolbar toolbar;
    private String valueResult = DateTimeUtil.getYesterdayDate();
    private String valueFixtures = DateTimeUtil.getTomorrowDate();
    private int pageType = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        LayoutInflater mInflater = LayoutInflater.from(this);
        View mCustomView = mInflater.inflate(R.layout.actionbar_form, null);
        tvTitle = (TextView) mCustomView.findViewById(R.id.actionbar_title);
        spDate = (Spinner) mCustomView.findViewById(R.id.actionbar_date);
        tvMoney = (TextView) mCustomView.findViewById(R.id.actionbar_money);
        tvCurrency = (TextView) mCustomView.findViewById(R.id.actionbar_currency);
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);

        setDatePicker(0);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, null, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
//        toggle.syncState();

        mFragmentManager = getSupportFragmentManager();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Select menu but not highlight menuBar
        onNavigationItemSelected(navigationView.getMenu().getItem(0));
        //Select menu and highlight menuBar
        navigationView.setCheckedItem(R.id.nav_camera);

    }

    @Override
    public void onResume() {
        super.onResume();
        // Register ourselves so that we can provide the initial value.
        BusProvider.getInstance().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
            xfragmentTransaction.replace(R.id.containerView, new TabFragment()).commit();
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setDatePicker(int pageType) {
        this.pageType = pageType;
        if (pageType == 0) {
            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, new String[]{DateTimeUtil.getCurrentDate()}); //selected item will look like a spinner set from XML
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spDate.setAdapter(spinnerArrayAdapter);
            spDate.setEnabled(false);
        } else if (pageType == 1) {
            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, DateTimeUtil.getList7DayResult()); //selected item will look like a spinner set from XML
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spDate.setAdapter(spinnerArrayAdapter);
            spDate.setEnabled(true);
            spDate.setSelection(((ArrayAdapter<String>) spDate.getAdapter()).getPosition(valueResult));
            spDate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    callEventBusResult();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        } else {
            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, DateTimeUtil.getList7DayFixtures()); //selected item will look like a spinner set from XML
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spDate.setAdapter(spinnerArrayAdapter);
            spDate.setEnabled(true);
            spDate.setSelection(((ArrayAdapter<String>) spDate.getAdapter()).getPosition(valueFixtures));
            spDate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    callEventBusFixtures();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        }
    }

    private void callEventBusResult() {
        if (this.pageType != 0) {
            Log.d(getClass().toString(), "valueResult is selected: " + valueResult);
            valueResult = spDate.getSelectedItem().toString();
            BusProvider.getInstance().post(produceDateResultEvent());
        }
    }

    private void callEventBusFixtures() {
        if (this.pageType != 0) {
            Log.d(getClass().toString(), "valueFixtures is selected: " + valueFixtures);
            valueFixtures = spDate.getSelectedItem().toString();
            BusProvider.getInstance().post(produceDateFixturesEvent());
        }
    }

    @Produce
    public DateResultChangedEvent produceDateResultEvent() {
        // Provide an initial value for location based on the last known position.
        Log.d(getClass().toString(), "DateResultChangedEvent is called: " + valueResult);
        return new DateResultChangedEvent(valueResult);

    }

    @Produce
    public DateFixturesChangedEvent produceDateFixturesEvent() {
        // Provide an initial value for location based on the last known position.
        Log.d(getClass().toString(), "DateFixturesChangedEvent is called: " + valueFixtures);
        return new DateFixturesChangedEvent(valueFixtures);
    }
}

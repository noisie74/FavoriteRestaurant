package com.mikhail.project2;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    TextView restaurantsCountHeader;
    ListView restaurantsList;
    Intent secondActivityIntent;
    Cursor cursor;
    CursorAdapter simpleCursorAdapter;
    Cursor newCursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        restaurantsCountHeader = (TextView) findViewById(R.id.restaurants_count);
        restaurantsList = (ListView) findViewById(R.id.restaurantsList);

        secondActivityIntent = getIntent();

        onNewIntent(getIntent());

        int id = getIntent().getIntExtra("id", -1);
        int prices[] = getIntent().getIntArrayExtra("price");
        float ratingSelection = getIntent().getFloatExtra("rating", 0.0f);
        String deliverySelection = getIntent().getStringExtra("delivery");



        RestaurantsData dbSetup = new RestaurantsData(SecondActivity.this);
        dbSetup.getReadableDatabase();


        cursor = RestaurantsData.getInstance(SecondActivity.this).getRestaurantsList(prices, ratingSelection, deliverySelection);


        final String[] columns = new String[]{RestaurantsData.COL_NAME, RestaurantsData.COL_PRICE, RestaurantsData.COL_RATING, RestaurantsData.COL_DELIVERY};
        int[] viewNames = new int[]{R.id.text, R.id.text1, R.id.text2, R.id.text3,};
        simpleCursorAdapter = new SimpleCursorAdapter(SecondActivity.this, R.layout.custom, cursor, columns, viewNames, 0);


        restaurantsList.setAdapter(simpleCursorAdapter);


        restaurantsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                cursor.moveToPosition(position);
                int getDataFromCursor = cursor.getColumnIndex(RestaurantsData.COL_ID);

                String restaurantIDString = cursor.getString(getDataFromCursor);
                int restaurantID = (Integer.valueOf(restaurantIDString));

                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);


                intent.putExtra("data", restaurantID); //sent key to DetailActivity in order to change header title there
                startActivity(intent);
                Log.d("restaurantIDString", "secondActivityIntent");
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.options_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));


        return true;

//        getMenuInflater().inflate(R.menu.main2, menu);
//        MenuItem myMenu = menu.findItem(R.id.action_settings);
//
//        myMenu.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                return false;
//            }
//        });
//
//        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        Log.d("secondActivity", "clicked");

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {


            String query = intent.getStringExtra(SearchManager.QUERY);


            newCursor = RestaurantsData.getInstance(SecondActivity.this).getSearchResults(query);
//            int index = newCursor.getColumnIndex(RestaurantsData.COL_NAME);
//            String nameOfRestaurants = newCursor.getString(index);

//            newCursor.moveToPosition(index);
            simpleCursorAdapter.swapCursor(newCursor);
//            restaurantsList.setAdapter(simpleCursorAdapter);
//            simpleCursorAdapter.notifyDataSetChanged();



//            if (simpleCursorAdapter == null) {
//                simpleCursorAdapter.changeCursor(newCursor);
//                restaurantsList.setAdapter(simpleCursorAdapter);
//                simpleCursorAdapter.notifyDataSetChanged();
//            } else {
//                simpleCursorAdapter.swapCursor(newCursor);
//                simpleCursorAdapter.notifyDataSetChanged();
//            }
        }
    }

//    int id = getIntent().getIntExtra("id",-1);


//    if(id >= 0){
//        String description = helper.getDescriptionById(id);
//        String type = helper.getTypeById(id);
//        String price = helper.getPriceByID(id);
//
//
//    searchView.setOnQueryTextListener(new
//
//    OnQueryTextListener() {
//
//        @Override
//        public boolean onQueryTextSubmit (String key){
//            //After you press Search/Enter control comes here with entered text in "key"
//            return true;
//        }
//
//        @Override
//        public boolean onQueryTextChange (String key){
//            //Each time you enter/modify a charcter, control comes here with entered text in "key"
//            return true;
//        }
//    }
//
//    );

}

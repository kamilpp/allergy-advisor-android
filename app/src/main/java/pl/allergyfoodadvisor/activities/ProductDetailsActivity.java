package pl.allergyfoodadvisor.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import pl.allergyfoodadvisor.R;
import pl.allergyfoodadvisor.api.pojos.Product;
import pl.allergyfoodadvisor.extras.CommonMethods;
import pl.allergyfoodadvisor.extras.RecyclerViewAllergensAdapter;

public class ProductDetailsActivity extends BaseActivity {

    public static final String EXTRA_PRODUCT = "product_object_to_display";
    private Product mProduct;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        Intent intent = getIntent();
        mProduct = (Product) intent.getSerializableExtra(EXTRA_PRODUCT);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(mProduct.name);

        ((TextView) findViewById(R.id.product_description)).setText(mProduct.description);
        ((TextView) findViewById(R.id.product_producer)).setText(mProduct.producer);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.allergen_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(new RecyclerViewAllergensAdapter(this,
                this.mProduct.allergens));
//        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
//            @Override
//            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
//                int action = e.getAction();
//                switch (action) {
//                    case MotionEvent.ACTION_MOVE:
//                        rv.getParent().requestDisallowInterceptTouchEvent(true);
//                        break;
//                }
//                return false;
//            }
//
//            @Override
//            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
//
//            }
//
//            @Override
//            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
//
//            }
//        });


        Log.e("product", this.mProduct.allergens.get(0).name);

        loadBackdrop();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish(); // go back
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadBackdrop() {
        final ImageView imageView = (ImageView) findViewById(R.id.backdrop);
        Glide.with(this).load(CommonMethods.getRandomCheeseDrawable()).centerCrop().into(imageView);
    }
}
package pl.allergyfoodadvisor.extras;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import pl.allergyfoodadvisor.R;
import pl.allergyfoodadvisor.activities.ProductDetailsActivity;
import pl.allergyfoodadvisor.api.pojos.Allergen;
import pl.allergyfoodadvisor.api.pojos.Product;

public class RecyclerViewAllergensAdapter
        extends RecyclerView.Adapter<RecyclerViewAllergensAdapter.ViewHolder> {

    private final TypedValue mTypedValue = new TypedValue();
    private int mBackground;
    private List<Allergen> mValues;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public String mBoundString;

        public final View mView;
        public final TextView mTextView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTextView = (TextView) view.findViewById(R.id.allergen_textview);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTextView.getText();
        }
    }

    public String getValueAt(int position) {
        return mValues.get(position).name;
    }

    public RecyclerViewAllergensAdapter(Context context, List<Allergen> items) {
        context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
        mBackground = mTypedValue.resourceId;
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.allergen_list_item, parent, false);
        view.setBackgroundResource(mBackground);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Allergen allergen = mValues.get(position);

        holder.mBoundString = allergen.name;
        holder.mTextView.setText(allergen.name);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();

                if (context != null) {
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }
}
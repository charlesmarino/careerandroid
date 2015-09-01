package com.charlesmarino.tustle;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Maps product objects to list fragment
 * Created by kirstiebooras on 8/30/15.
 */
public class ProductAdapter extends ArrayAdapter<Product> {

    private final Product[] mProducts;
    private static LayoutInflater mInflater;
    private static Resources mRes;

    public ProductAdapter(Context context, Product[] products) {
        super(context, -1, products);
        this.mProducts = products;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mRes = context.getResources();
    }

    static class ViewHolder {
        TextView nameTextView;
        TextView descriptionTextView;
        ImageView imageView;
        Button purchaseButton;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.product_card, null);

            viewHolder = new ViewHolder();
            viewHolder.nameTextView = (TextView) convertView.findViewById(R.id.nameText);
            viewHolder.descriptionTextView = (TextView) convertView.findViewById(R.id.descriptionText);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            viewHolder.purchaseButton = (Button) convertView.findViewById(R.id.purchaseButton);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Product product = mProducts[position];

        if (product != null) {
            viewHolder.nameTextView.setText(product.getName());
            viewHolder.descriptionTextView.setText(product.getDescription());

            if (product.getImage() != null) {
                Bitmap bmp = BitmapFactory.decodeByteArray(product.getImage(), 0, product.getImage().length);
                viewHolder.imageView.setImageBitmap(bmp);
            }

            viewHolder.purchaseButton.setText(String.format(mRes.getString(R.string.purchase_button_text),
                    product.getCost()));

            viewHolder.purchaseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // TODO
                }
            });
        }

        return convertView;
    }
}

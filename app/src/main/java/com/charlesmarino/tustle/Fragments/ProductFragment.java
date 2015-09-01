package com.charlesmarino.tustle.Fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.ListFragment;

import com.charlesmarino.tustle.Product;
import com.charlesmarino.tustle.ProductAdapter;
import com.charlesmarino.tustle.R;

import java.io.ByteArrayOutputStream;

/**
 * Fragment showing list of available products
 * Created by kirstiebooras on 8/30/15.
 */
public class ProductFragment extends ListFragment {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Product[] products = getDataFromServer();
        ProductAdapter productAdapter = new ProductAdapter(getActivity().getBaseContext(), products);
        setListAdapter(productAdapter);
    }

    private Product[] getDataFromServer() {
        // TODO pull data from server
        return new Product[]{getDummyProduct(), getDummyProduct(),
                getDummyProduct(), getDummyProduct()};
    }

    private Product getDummyProduct() {
        Product product = new Product();
        product.setName("Charles Marino");
        product.setDescription("Charles, a Facebook engineer, will teach you how to be smart.");
        product.setCost("80");

        // Change image to byte[]
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        product.setImage(byteArray);

        return product;
    }
}

package com.example.staterapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Random;


public class MainActivity extends AppCompatActivity {
    private static final String KEY_HEAD_IMAGE = "head_image";
    private static final String KEY_BODY_IMAGE = "body_image";
    private static final String KEY_LEG_IMAGE = "leg_image";
    private Context mContext;
    private ImageView headView, bodyView, legView;
    ImageButton headButton,bodyButton,legButton;
    private LinearLayout layout;
    private int headResourceId, bodyResourceId, legResourceId;
    private ImageView selectedImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext=this;
         layout = findViewById(R.id.testLayout);
        headView = findViewById(R.id.headview);
        bodyView = findViewById(R.id.bodyview);
        legView = findViewById(R.id.legview);
        Button randomButton = findViewById(R.id.random);
        headResourceId = getDrawableResource("head10");
        bodyResourceId = getDrawableResource("body10");
        legResourceId = getDrawableResource("legs3");

        // Set the default images
        headView.setImageResource(headResourceId);
        bodyView.setImageResource(bodyResourceId);
        legView.setImageResource(legResourceId);
        randomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Generate random numbers from 1 to 12 for head, body, and legs
                int randomHead = new Random().nextInt(12) + 1;
                int randomBody = new Random().nextInt(12) + 1;
                int randomLegs = new Random().nextInt(12) + 1;

                // Set the randomly selected drawables to the corresponding ImageViews
                headResourceId = getDrawableResource("head" + randomHead);
                bodyResourceId = getDrawableResource("body" + randomBody);
                legResourceId = getDrawableResource("legs" + randomLegs);

                headView.setImageResource(headResourceId);
                bodyView.setImageResource(bodyResourceId);
                legView.setImageResource(legResourceId);

                headView.setBackgroundColor(Color.TRANSPARENT);
                bodyView.setBackgroundColor(Color.TRANSPARENT);
                legView.setBackgroundColor(Color.TRANSPARENT);
            }
        });


        // Loop through your images and create image buttons for head
        for (int i = 1; i <= 12; i++) {
            final int finalI = i;
            ImageButton headButton = createImageButton("head" + i);
            headButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Get the drawable resource ID of the clicked head image button
                    headResourceId = getResources().getIdentifier("head" + finalI, "drawable", getPackageName());
                    // Update the headView ImageView with the clicked head image
                    headView.setImageResource(headResourceId);
                }
            });
            layout.addView(headButton);
        }

        // Loop through your images and create image buttons for body
        for (int i = 1; i <= 12; i++) {
            final int finalI = i;
            ImageButton bodyButton = createImageButton("body" + i);
            bodyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Get the drawable resource ID of the clicked body image button
                    bodyResourceId = getResources().getIdentifier("body" + finalI, "drawable", getPackageName());
                    // Update the bodyView ImageView with the clicked body image
                    bodyView.setImageResource(bodyResourceId);
                }
            });
            layout.addView(bodyButton);
        }

        // Loop through your images and create image buttons for legs
        for (int i = 1; i <= 12; i++) {
            final int finalI = i;
            ImageButton legButton = createImageButton("legs" + i);
            legButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Get the drawable resource ID of the clicked legs image button
                    legResourceId = getResources().getIdentifier("legs" + finalI, "drawable", getPackageName());
                    // Update the legView ImageView with the clicked legs image
                    legView.setImageResource(legResourceId);
                }
            });
            layout.addView(legButton);
        }
        if (savedInstanceState != null) {
            headResourceId = savedInstanceState.getInt(KEY_HEAD_IMAGE);
            bodyResourceId = savedInstanceState.getInt(KEY_BODY_IMAGE);
            legResourceId = savedInstanceState.getInt(KEY_LEG_IMAGE);

            headView.setImageResource(headResourceId);
            bodyView.setImageResource(bodyResourceId);
            legView.setImageResource(legResourceId);
        }
        // Set OnClickListener for headView
        headView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.removeAllViews();
                generateButtons("head");
                highlightImageView(headView);
            }
        });

        // Set OnClickListener for bodyView
        bodyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.removeAllViews();
                generateButtons("body");
                highlightImageView(bodyView);
            }
        });

        // Set OnClickListener for legView
        legView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.removeAllViews();
                generateButtons("legs");
                highlightImageView(legView);
            }
        });
    }
    private void generateButtons(String part) {
        // Loop through your images and create image buttons
        for (int i = 1; i <= 12; i++) {
            final int finalI = i;
            ImageButton legButton = createImageButton(part + i);
            legButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int resourceId = getResources().getIdentifier(part + finalI, "drawable", getPackageName());
                    switch (part) {
                        case "head":
                            headResourceId = resourceId;
                            headView.setImageResource(headResourceId);
                            break;
                        case "body":
                            bodyResourceId = resourceId;
                            bodyView.setImageResource(bodyResourceId);
                            break;
                        case "legs":
                            legResourceId = resourceId;
                            legView.setImageResource(legResourceId);
                            break;
                    }
                }
            });
            layout.addView(legButton);
        }
    }
    private void highlightImageView(ImageView imageView) {
        // Remove highlight from previously selected ImageView
        if (selectedImageView != null) {
            selectedImageView.setBackgroundColor(Color.parseColor("#00000000"));
        }
        // Highlight the clicked ImageView
        selectedImageView = imageView;
        selectedImageView.setBackgroundColor(ContextCompat.getColor(this, R.color.highlight_color));
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_HEAD_IMAGE, headResourceId);
        outState.putInt(KEY_BODY_IMAGE, bodyResourceId);
        outState.putInt(KEY_LEG_IMAGE, legResourceId);
    }
    private int getDrawableResource(String name) {
        return getResources().getIdentifier(name, "drawable", getPackageName());
    }
    private ImageButton createImageButton(String imageName) {
        // Create image button with fixed size
        ImageButton imageButton = new ImageButton(mContext);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(250, 250); // Adjust size as needed
        imageButton.setLayoutParams(params);
        imageButton.setScaleType(ImageView.ScaleType.FIT_CENTER);
        // Set image for the button
        int resourceId = getResources().getIdentifier(imageName, "drawable", getPackageName());
        Drawable drawable = getResources().getDrawable(resourceId);
        imageButton.setImageDrawable(drawable);

        // You can set onClickListener here if you want to handle click events

        return imageButton;
    }
}


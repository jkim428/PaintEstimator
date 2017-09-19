package edu.orangecoastcollege.cs273.jkim428.paintestimator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Member variables for the Views
    private EditText mLengthEditText;
    private EditText mWidthEditText;
    private EditText mHeightEditText;

    private EditText mDoorsEditText;
    private EditText mWindowsEditText;

    private TextView mGallonsTextView;

    // Member variable for the Model
    private InteriorRoom mRoom;

    // Member variable for SharedPreferences
    private SharedPreferences mPrefs;

    private void initializeViews()
    {
        mLengthEditText = (EditText) findViewById(R.id.lengthEditText);
        mWidthEditText = (EditText) findViewById(R.id.widthEditText);
        mHeightEditText = (EditText) findViewById(R.id.heightEditText);

        mDoorsEditText = (EditText) findViewById(R.id.doorsEditText);
        mWindowsEditText = (EditText) findViewById(R.id.windowsEditText);

        mGallonsTextView = (TextView) findViewById(R.id.gallonsTextView);
        // TODO: Finish initialization
    }

    private void loadSharedPreferences()
    {
        mPrefs = getSharedPreferences("edu.orangecoastcollege.cs273.jkim428.PaintEstimator", MODE_PRIVATE);
        if (mPrefs != null)
        {
            // Load all the room information
            mRoom.setDoors(mPrefs.getInt("doors", 0));
            mDoorsEditText.setText(String.valueOf(mRoom.getDoors()));

            mRoom.setHeight(mPrefs.getFloat("height", 0.0f));
            mHeightEditText.setText(String.valueOf(mRoom.getHeight()));

            // TODO: Keep going

        }
    }

    private void saveSharedPreferences()
    {
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.clear();
        editor.putFloat("length", mRoom.getLength());
        editor.putFloat("width", mRoom.getWidth());
        editor.putFloat("height", mRoom.getHeight());
        // TODO: Keep going...
        // Save the changes to the SharedPreferences file
        editor.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        loadSharedPreferences();

    }

    protected void computeGallons(View v)
    {
        // Update model first, then calculate
        mRoom.setLength(Float.parseFloat(mLengthEditText.getText().toString()));
        mRoom.setWidth(Float.parseFloat(mWidthEditText.getText().toString()));
        mRoom.setHeight(Float.parseFloat(mWidthEditText.getText().toString()));
        // TODO: Finish the rest!

        mGallonsTextView.setText(getString(R.string.interior_surface_area_text) + mRoom.totalSurfaceArea()
                + "\n" + getString(R.string.gallons_needed_text) + mRoom.gallonsOfPaintRequired());

        saveSharedPreferences();
    }

    protected void gotoHelp(View v)
    {
        // Constructs an EXPLICIT Intent to go to HelpActivity
        // Intent: specify where to start (context) and where we're going (next Activity)
        Intent helpIntent = new Intent(this, HelpActivity.class);
        helpIntent.putExtra("gallons", mRoom.gallonsOfPaintRequired());
        startActivity(helpIntent);
    }
}

package com.example.fileexplorer;
  
import java.io.File;
import java.io.FileOutputStream;

import android.os.Bundle; 
import android.os.Environment;
import android.app.Activity; 
import android.content.Context;
import android.content.Intent; 
import android.util.Log;
import android.view.View;
import android.widget.EditText; 
import android.widget.Toast;

public class FileexplorerActivity extends Activity {
 
	private static final int REQUEST_PATH = 1;
 
	String curFileName;
	String curPath;
	
	String filename = "sekerettandiscor.wow";
	String string = "Hello world!";
	FileOutputStream outputStream;
	
	EditText edittext;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fileexplorer); 
        edittext = (EditText)findViewById(R.id.editText);
    }
 
    public void getfile(View view){ 
    	Intent intent1 = new Intent(this, FileChooser.class);
        startActivityForResult(intent1,REQUEST_PATH);
    }
    
    public void createfile(View view){
    	if(isExternalStorageReadable()){
    		if(isExternalStorageWritable()){
    			getAlbumStorageDir(filename);
    		}else{
    			 Toast.makeText(this, "Cannot write in this storage", Toast.LENGTH_SHORT);
    		}
    	}else{
    		 Toast.makeText(this, "Cannot read tis storage", Toast.LENGTH_SHORT);
    	}
    	
    }
    
    public File getAlbumStorageDir(String albumName) {
        // Get the directory for the user's public pictures directory. 
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), albumName);
        Toast.makeText(this, "Directory created", Toast.LENGTH_SHORT);
        if (!file.mkdirs()) {
            //Log.e(LOG_TAG, "Directory not created");
            Toast.makeText(this, "Directory not created", Toast.LENGTH_SHORT);
        }
        return file;
    }
   
 // Listen for results.
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        // See which child activity is calling us back.
    	if (requestCode == REQUEST_PATH){
    		if (resultCode == RESULT_OK) { 
    			curPath = data.getStringExtra("GetPath");
    			curFileName = data.getStringExtra("GetFileName");
            	edittext.setText(curPath+curFileName);
    		}
    	 }
    }
    
    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
            Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }
}

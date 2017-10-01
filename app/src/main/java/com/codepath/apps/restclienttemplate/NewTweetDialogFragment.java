package com.codepath.apps.restclienttemplate;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by chenrangong on 9/30/17.
 */

public class NewTweetDialogFragment extends DialogFragment {

    public interface EditSaveListener{
        public void onEditSave(String tweetBody, NewTweetDialogFragment newTweetialogFragment);
    }

    public interface CancelListener{
        public void onCancel(NewTweetDialogFragment newTweetDialogFragment);
    }

    TwitterClient client;
    EditText mEditContent;
    Button saveBtw;
    Button cancelBtn;
    ImageView currentUserImage;
    TextView currentUserName;
    String userName;
    String image;

    public EditSaveListener editSaveListener;
    public CancelListener cancelListener;

    public NewTweetDialogFragment(){

    }

    public static NewTweetDialogFragment newInstance(String username, String image){
        NewTweetDialogFragment frag = new NewTweetDialogFragment();
        frag.userName = username;
        frag.image = image;
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_edit_item, container);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);

        // request a window without the title
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        client = TwitterApp.getRestClient();

        mEditContent = (EditText) view.findViewById(R.id.rvNewTweet);
        mEditContent.requestFocus();

        currentUserImage = (ImageView) view.findViewById(R.id.currentUserImage);
        currentUserName = (TextView) view.findViewById(R.id.currentUserName);

        currentUserName.setText(userName);
        Glide.with(this).load(image).into(currentUserImage);

        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        saveBtw = (Button) view.findViewById(R.id.btnSave);
        saveBtw.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String content = mEditContent.getText().toString();
                editSaveListener.onEditSave(content, NewTweetDialogFragment.this);
            }
        });

        cancelBtn = (Button) view.findViewById(R.id.btnCancel);
        cancelBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                cancelListener.onCancel(NewTweetDialogFragment.this);
            }
        });
    }

}

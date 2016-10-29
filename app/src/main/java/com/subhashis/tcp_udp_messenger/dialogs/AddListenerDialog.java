package com.subhashis.tcp_udp_messenger.dialogs;

import android.app.Activity;
import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.subhashis.tcp_udp_messenger.R;

/**
 * Created by Subhashis on 29-10-2016.
 */
public class AddListenerDialog extends DialogFragment {
    Communicator communicator;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        communicator=(Communicator) activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //getDialog().setTitle("Add Listener");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.add_listener_dialog_layout,null);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //getDialog().setTitle("Add Listener");
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return v;
    }

    public interface Communicator{
        void onPassMessage(String s);
    }
}

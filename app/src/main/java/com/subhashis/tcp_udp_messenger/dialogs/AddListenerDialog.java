package com.subhashis.tcp_udp_messenger.dialogs;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.subhashis.tcp_udp_messenger.R;

/**
 * Created by Subhashis on 29-10-2016.
 */
public class AddListenerDialog extends DialogFragment {
    Communicator communicator;
    LinearLayout externalLayout,internalLayout;
    Button ok,cancel;
    Spinner spinner;
    EditText ip,port;
    Activity parentActivity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        parentActivity=activity;
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
        externalLayout=(LinearLayout) v.findViewById(R.id.externalLayout);
        externalLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        internalLayout=(LinearLayout) v.findViewById(R.id.internalLayout);
        internalLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Empty OnClickListener for stopping the callstack
                // to reach the externalLayout
                // if the click is performed within this view.
            }
        });
        ok=(Button) v.findViewById(R.id.ok);
        cancel=(Button) v.findViewById(R.id.cancel);
        ip=(EditText) v.findViewById(R.id.ip);
        port=(EditText) v.findViewById(R.id.port);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(performAction()) dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return v;
    }

    private boolean performAction() {


        String ipAddr=ip.getText().toString();
        if(ipAddr.isEmpty() || port.getText().toString().isEmpty())
        {
            Toast.makeText((Context)parentActivity,"Invalid IP or Port",Toast.LENGTH_SHORT).show();
            return false;
        }
        int portNumber= Integer.valueOf(port.getText().toString());


        return false;
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);

    }

    public interface Communicator{
        void onPassMessage(String s);
    }
}

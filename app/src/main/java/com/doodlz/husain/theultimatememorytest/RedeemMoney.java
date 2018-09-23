package com.doodlz.husain.theultimatememorytest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RedeemMoney extends AppCompatActivity {

    private DatabaseReference leaderBoard,myStash;
    private ArrayList<Leader> topLeaders=new ArrayList<>();
    private String currentFU;
    Button addtoStash;
    int currentScore=0;
    int totalStash=0;
    TextView leaderTV, allcoinsTV,latestCoinsTV;
    String leaders;
    int numOfLeaders;
    TextView leader1,leader2,leader3,leader1points,leader2points,leader3points;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redeem_money);

        Bundle extras = getIntent().getExtras();
        if (extras != null) { currentScore = extras.getInt("score");
            Log.d("RedeemMoney","score:"+" "+currentScore);}

        leaderTV=(TextView)findViewById(R.id.leader1TV);
        allcoinsTV =(TextView)findViewById(R.id.allcoinsTV);
        latestCoinsTV=(TextView)findViewById(R.id.latestcoin);
        addtoStash=(Button) findViewById(R.id.coinsToStash);
        leader1=(TextView)findViewById(R.id.leader1TV);
        leader2=(TextView)findViewById(R.id.leader2TV);
        leader3=(TextView)findViewById(R.id.leader3TV);
        leader1points=(TextView)findViewById(R.id.leader1TVScore);
        leader2points=(TextView)findViewById(R.id.leader2TVScore);
        leader3points=(TextView)findViewById(R.id.leader3TVScore);

        latestCoinsTV.setText(String.valueOf(currentScore));

        currentFU= FirebaseAuth.getInstance().getCurrentUser().getUid();

        leaderBoard= FirebaseDatabase.getInstance().getReference().child("LeaderBoard");
        myStash= FirebaseDatabase.getInstance().getReference().child("UserPoints");

        addtoStash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                latestCoinsTV.setText("0");
                myStash.child(currentFU).child("points").setValue(String.valueOf(totalStash+currentScore));
                currentScore=0;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();


        leaderBoard.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                numOfLeaders=(int)dataSnapshot.getChildrenCount();
                Log.d("RedeemMoneyab","numberOFLeaders"+numOfLeaders);
                for(DataSnapshot leader:dataSnapshot.getChildren()){

                    Leader  leaderOfTheFreeWorld = leader.getValue(Leader.class);
                   // Toast.makeText(RedeemMoney.this,"leader .."+leaderOfTheFreeWorld.getPoints(),Toast.LENGTH_SHORT).show();
                    topLeaders.add(leaderOfTheFreeWorld);

                }
                for(int i=0;i<numOfLeaders;i++){

                    if(i==0){ leader1points.setText(topLeaders.get(i).getPoints());
                             leader1.setText(topLeaders.get(i).getUserName());}
                    if(i==1){leader2points.setText(topLeaders.get(i).getPoints());
                            leader2.setText(topLeaders.get(i).getUserName());}
                    if(i==2){ leader3points.setText(topLeaders.get(i).getPoints());
                        leader3.setText(topLeaders.get(i).getUserName());}

                }
//                if(topLeaders.get(0)!=null){ leader1.setText(topLeaders.get(0).getUserName());}

//
//                if(topLeaders.get(1)!=null){ leader2.setText(topLeaders.get(1).getUserName());}

//
//                if(topLeaders.get(2)!=null){ leader3.setText(topLeaders.get(2).getUserName());}

//
//                if(topLeaders.get(0)!=null){ leader1points.setText(topLeaders.get(0).getPoints());}
//                if(topLeaders.get(1)!=null){ leader2points.setText(topLeaders.get(1).getPoints());}
//                if(topLeaders.get(2)!=null){ leader3points.setText(topLeaders.get(2).getPoints());}


 }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        myStash.child(currentFU).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Leader currentUser = dataSnapshot.getValue(Leader.class);
                totalStash=Integer.parseInt(currentUser.getPoints());
                allcoinsTV.setText(currentUser.getPoints());

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    public void checkOutToCash(View view) {
        startActivity(new Intent(RedeemMoney.this,PayTmCash.class));}


}


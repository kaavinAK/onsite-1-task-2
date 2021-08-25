package com.example.task2b;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    List<Integer> colors = new ArrayList<>();
      RecyclerView rv;
      box[] boxes=new box[6];
      Timer countdown;
      TextView timer;
      TextView resultview;
      int time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultview=findViewById(R.id.result);
        rv=findViewById(R.id.recycleview);
        timer=findViewById(R.id.timer);
        colors.add(Color.RED);
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.GREEN);
        colors.add(Color.BLUE);
        colors.add(Color.BLUE);
        Collections.shuffle(colors);
        for(int i=0;i<6;i++)
        {
            boxes[i]=new box(Color.RED,0);
        }

        rv.setLayoutManager(new LinearLayoutManager(this));
        CustomAdapter ca = new CustomAdapter(colors,boxes);
        rv.setAdapter(ca);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(rv);


        countdown = new Timer();
        countdown.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run(){
                  int result=check(boxes);
                  time=Integer.valueOf(timer.getText().toString());
                  if(time!=0)
                  {
                      time=time-1;
                      timer.setText(String.valueOf(time));


                  }
                  else
                  {
                      resultview.setText("you lost");
                      if(countdown!=null)
                      {
                          countdown.cancel();
                      }
                  }
                  if(result==0)
                  {
                      resultview.setText("you won");
                      if(countdown!=null)
                      {
                          countdown.cancel();
                      }
                  }
                  if(result==-1)
                  {
                      Log.d("game result", "run: "+"you lost ");
                  }

            }
        },0,1000);
    }
    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.START | ItemTouchHelper.END,0) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            int fromposition = viewHolder.getAdapterPosition();
            int toposition = target.getAdapterPosition();
            int tempcolor;
            int tempposition;
            Collections.swap(colors,fromposition,toposition);
              tempcolor= boxes[fromposition].color;

              boxes[fromposition].color=boxes[toposition].color;
            boxes[toposition].color=tempcolor;
            tempposition= boxes[fromposition].positiony;

            boxes[fromposition].positiony=boxes[toposition].positiony;
            boxes[toposition].positiony=tempposition;


          //  Collections.swap(Arrays.asList(boxes),fromposition,toposition);
            recyclerView.getAdapter().notifyItemMoved(fromposition,toposition);
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

        }
    };
    public int check(box[] boxes)
    {
        Log.d("inside boxes", "check: ");

        //float[] positions = new float[6];
        int result;
//        for(int i=0;i<6;i++)
//        {
//            positions[i]=boxes[i].positiony;
//            Log.d("positions", "color: "+boxes[i].color + "position "+ boxes[i].positiony);
//        }
      //  Arrays.sort(positions);
        Stack<box> boxStack = new Stack<box>();
//        for(int i=0;i<6;i++)
//        {
//            for(int w=0;w<6;w++)
//            {
//                if(positions[i]==boxes[w].positiony)
//                {
//                //    Log.d("positions stack", i+"---- "+boxes[i].positiony);
//                 //   Log.d("about box", "color : "+boxes[i].color+" position : "+ boxes[i].positiony);
//                    boxStack.add(boxes[w]);
//                }
//            }
//
//        }
        for(int i=0;i<6;i++)
        {
            boxStack.add(boxes[i]);
        }
        box box1;
        box box2;
        while (true)
        {
            box1=boxStack.peek();
            boxStack.pop();
            box2=boxStack.peek();
            boxStack.pop();
            Log.d("popped", "check: "+box1.positiony+ "   "+box1.color);
            Log.d("popped", "check: "+box2.positiony+"    "+box2.color);
            if(box1.color!=box2.color)
            {
             //  Log.d("result", "check: "+"no boyeno");
                result=-1;
                break;
            }
            if(boxStack.empty())
            {
                result=0;
                break;
            }
        }
        return result;

    }
}
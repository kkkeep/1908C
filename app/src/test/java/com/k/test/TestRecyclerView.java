package com.k.test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.m.k.mvp.utils.Logger;
import com.m.k.seetaoism.R;
import com.m.k.seetaoism.databinding.TestActivityRecyclerBinding;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TestRecyclerView extends AppCompatActivity {


    TestActivityRecyclerBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = TestActivityRecyclerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.testRecycler.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Person> people = new ArrayList<>();

        people.add(new Person("1","zhangsan"));
        people.add(new Person("2","lisi"));
        people.add(new Person("3","wangwu"));

        MyAdapter myAdapter = new MyAdapter(people);
        myAdapter.submitList(people);

        binding.testRecycler.setAdapter(myAdapter);

        binding.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Person> people = new ArrayList<>();

                people.add(new Person("1","zhangsan"));
                people.add(new Person("2","lisi"));
                people.add(new Person("3","wangwu"));
                people.add(new Person("4","zhaoliu"));


                ((MyAdapter) binding.testRecycler.getAdapter()).submitList(people);

            }
        });

    }



    public class MyAdapter extends ListAdapter<Person, MyAdapter.MyHolder> {


        private ArrayList<Person> list;

        protected MyAdapter(ArrayList<Person> people) {

            super(new DiffUtil.ItemCallback<Person>() {
                @Override
                public boolean areItemsTheSame(@NonNull Person oldItem, @NonNull Person newItem) {
                    return oldItem.equals(newItem);
                }

                @Override
                public boolean areContentsTheSame(@NonNull Person oldItem, @NonNull Person newItem) {
                    return oldItem.getName().equals(newItem.getName());
                }
            });

            list = people;
        }



        public void setList(ArrayList<Person> list) {
            this.list = list;
            notifyItemMoved(1,2);
          //  notifyItemRangeChanged(1,2);
        }

        @NonNull
        @Override
        public  MyAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            Logger.d();
            TextView textView  = new TextView(parent.getContext());
            textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            return new MyHolder(textView);
        }



        @Override
        public void onBindViewHolder(@NonNull MyHolder holder, int position) {
            Logger.d();
            ((TextView)holder.itemView).setText(getCurrentList().get(position).getId() + "  =   " +getCurrentList().get(position).getName() );
        }


       /* @Override
        public int getItemCount() {
            return list == null ? 0 : list.size();
        }*/

        public class MyHolder extends RecyclerView.ViewHolder{

            public MyHolder(@NonNull View itemView) {
                super(itemView);
            }
        }
    }

}

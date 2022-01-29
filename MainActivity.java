package com.example.schoolmedicalobservation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class  MainActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private RecyclerView recyclerView;
    private ArrayList<StudentsInformations> studentArrayList;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("studentsInformations");
        recyclerView = findViewById(R.id.student_recycler_view);
        studentArrayList = new ArrayList<>();
        recyclerView.setAdapter(new MainActivity.StudentAdapter(studentArrayList));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 1, RecyclerView.VERTICAL, false));

        Query query = databaseReference.orderByKey();//.startAt(input).endAt(input+"\uf8ff");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    studentArrayList.clear();
                    for (DataSnapshot user : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"
                        StudentsInformations usersBean = user.getValue(StudentsInformations.class);
                        studentArrayList.add(usersBean);
                    }

                    recyclerView.getAdapter().notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Database error", Toast.LENGTH_LONG).show();
            }
        });

    }

    public static class StudentViewHolder extends RecyclerView.ViewHolder {
        public TextView studentNameText;
        public Button viewStudent;


        public StudentViewHolder(View view) {
            super(view);
            studentNameText = (TextView) itemView.findViewById(R.id.student_name);
            viewStudent = itemView.findViewById(R.id.view_student_btn);


        }
    }

    public class StudentAdapter extends RecyclerView.Adapter<MainActivity.StudentViewHolder> {
        // Set numbers of Tiles in RecyclerView.
        private List<StudentsInformations> studentList = new ArrayList<>();


        public StudentAdapter(List<StudentsInformations> studentList) {

            this.studentList = studentList;
        }

        @Override
        public MainActivity.StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_layout, parent, false);
            return new MainActivity.StudentViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MainActivity.StudentViewHolder holder, int position) {
            final StudentsInformations student = studentList.get(position);
            holder.studentNameText.setText(student.getName());
            holder.viewStudent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), ViewStudent.class);
                    intent.putExtra("student", student);
                    startActivity(intent);
                }
            });

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), ViewStudent.class);
                    intent.putExtra("student", student);
                    startActivity(intent);
                }
            });

        }

        @Override
        public int getItemCount() {
            return studentList.size();

        }
    }


}
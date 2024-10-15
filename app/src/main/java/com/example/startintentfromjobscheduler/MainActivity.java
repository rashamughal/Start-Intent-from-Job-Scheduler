package com.example.startintentfromjobscheduler;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button button=findViewById(R.id.btnScheduleJob);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scheduleJob();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void scheduleJob() {
        //componenet name is a class which represents the jobservice, here object represents MyJobService will do a job
        ComponentName componentName = new ComponentName(this, MyJobService.class); //MyJobService.class
        // specifies the JobService class that will handle the job.

        //JobInfo.Builder is used to build a JobInfo object that contains the job’s configuration and requirements.
        //JOB_ID is a unique identifier for the job (e.g., 1). ComponentName defined earlier, indicating which JobService will handle the job.
        JobInfo.Builder builder = new JobInfo.Builder(1, componentName)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)  // Requires Wi-Fi
                .setPersisted(true);  // Job persists across device reboots,This means that if the device restarts,
        // the job will still be scheduled and executed later.

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            builder.setRequiresBatteryNotLow(true);  // Only run when battery is not low
//        }

        //getSystemService(Context.JOB_SCHEDULER_SERVICE) retrieves the JobScheduler system service,
        // which is responsible for managing job scheduling.
        JobScheduler jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        if (jobScheduler != null) {
            //jobBuilder.build() creates a JobInfo object with the defined settings.
            int result = jobScheduler.schedule(builder.build());//jobScheduler.schedule(jobBuilder.build()) schedules the job using the JobScheduler.
            //resultCode will indicate whether the job was scheduled successfully or if there was an error
            if (result == JobScheduler.RESULT_SUCCESS) {
                Log.d("TAG","Job scheduled successfully");
            } else {
                Log.d("TAG","Job scheduling failed");
            }
        }
    }

}
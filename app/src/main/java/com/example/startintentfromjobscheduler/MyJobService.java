package com.example.startintentfromjobscheduler;

import android.app.Service;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class MyJobService extends JobService {

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        // Job starts in the background

        // Start an Activity from the JobService
        Intent intent = new Intent(this, TargetActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  // Required to start an activity from background
        startActivity(intent);

        // Job finished
        jobFinished(jobParameters, false);
        return true;//iReturn true if the job is long-running, false if it's short
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }


}

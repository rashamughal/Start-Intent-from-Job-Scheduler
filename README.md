JobScheduler Intent Starter
This Android project demonstrates how to schedule a background job using JobScheduler and trigger an activity (TargetActivity) with a 
countdown timer when the job is executed.

Features

JobScheduler: Schedules background jobs with specific requirements (e.g., only runs on Wi-Fi).

JobService: Handles the background task when triggered by the JobScheduler.

CountDown Timer: TargetActivity displays a 10-second countdown timer and updates the UI every second.
Edge-to-Edge Support: The project supports edge-to-edge UI layout, handling system bars using WindowInsetsCompat.

Components
1. MainActivity
This is the launcher activity that allows the user to schedule a job using the JobScheduler. When a button is clicked, the app
schedules a job to be executed under certain conditions (Wi-Fi only). The job persists across device reboots.

Key Methods:
scheduleJob(): This method creates a JobInfo object and schedules it with the JobScheduler. The job requires an unmetered network (Wi-Fi) and persists across reboots.

Code Snippet:
java

JobInfo.Builder builder = new JobInfo.Builder(1, new ComponentName(this, MyJobService.class))
    .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
    .setPersisted(true);

2. MyJobService
MyJobService is the background service that runs when the job is triggered by the JobScheduler. Once executed, it starts TargetActivity using an explicit intent.

Code Snippet:
java

Intent intent = new Intent(this, TargetActivity.class);
intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
startActivity(intent);

3. TargetActivity
This activity is launched by MyJobService when the job starts. It includes a CountDownTimer that counts down from 10 seconds, updating a TextView each second.

Key Components:
CountDownTimer: Handles the countdown logic, updating the UI every second.
Edge-to-Edge UI: Ensures proper padding for system bars.

Code Snippet:
java code
countDownTimer = new CountDownTimer(10000, 1000) {
    @Override
    public void onTick(long millisUntilFinished) {
        timerText.setText("Countdown: " + millisUntilFinished / 1000);
    }

    @Override
    public void onFinish() {
        timerText.setText("Countdown finished!");
    }
};
countDownTimer.start();

How to Run

Clone this repository.
Open the project in Android Studio.

Build and run the project on an emulator or device with Android 8.0 (API level 26) or higher.

On the main screen, tap the "Schedule Job" button to schedule the job.

The job will trigger when the conditions are met (Wi-Fi is connected), launching TargetActivity with a countdown timer.

Prerequisites
Android Studio 4.0 or higher
Android SDK (API Level 26 or higher)
Java Development Kit (JDK 8 or higher)

Dependencies
AndroidX: The project uses AndroidX libraries for compatibility and UI handling.

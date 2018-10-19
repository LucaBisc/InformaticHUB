package it.unibas.biscione.informaticHUB;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import it.unibas.biscione.informaticHUB.control.ControlAddNews;
import it.unibas.biscione.informaticHUB.control.ControlCategoryCourse;
import it.unibas.biscione.informaticHUB.control.ControlCohort;
import it.unibas.biscione.informaticHUB.control.ControlContent;
import it.unibas.biscione.informaticHUB.control.ControlExam;
import it.unibas.biscione.informaticHUB.control.ControlForum;
import it.unibas.biscione.informaticHUB.control.ControlInfo;
import it.unibas.biscione.informaticHUB.control.ControlLoginEsse3;
import it.unibas.biscione.informaticHUB.control.ControlLoginMoodle;
import it.unibas.biscione.informaticHUB.control.ControlMain;
import it.unibas.biscione.informaticHUB.control.ControlPage;
import it.unibas.biscione.informaticHUB.control.ControlSection;
import it.unibas.biscione.informaticHUB.model.Model;

public class Application extends android.app.Application {

    public static final String TAG = Application.class.getName();

    private static Application singleton;

    private ControlMain controlMain = new ControlMain();
    private ControlLoginMoodle controlLoginMoodle = new ControlLoginMoodle();
    private ControlCategoryCourse controlCategoryCourse = new ControlCategoryCourse();
    private ControlContent controlContent = new ControlContent();
    private ControlForum controlForum = new ControlForum();
    private ControlPage controlPage = new ControlPage();
    private ControlExam controlExam = new ControlExam();
    private ControlAddNews controlAddNews = new ControlAddNews();
    private ControlCohort controlCohort = new ControlCohort();
    private ControlSection controlSection = new ControlSection();
    private ControlLoginEsse3 controlLoginEsse3 = new ControlLoginEsse3();
    private ControlInfo controlInfo = new ControlInfo();
    private Model model = new Model();

    public Model getModel() {
        return model;
    }

    public ControlCohort getControlCohort() {
        return controlCohort;
    }

    public ControlExam getControlExam() {
        return controlExam;
    }

    public ControlAddNews getControlAddNews() {
        return controlAddNews;
    }

    public ControlLoginMoodle getControlLoginMoodle() {
        return controlLoginMoodle;
    }

    public ControlLoginEsse3 getControlLoginEsse3() {
        return controlLoginEsse3;
    }

    public ControlInfo getControlInfo() {
        return controlInfo;
    }

    public ControlMain getControlMain() {
        return controlMain;
    }

    public ControlCategoryCourse getControlCategoryCourse() {
        return controlCategoryCourse;
    }

    public Activity getCurrentActivity() {
        return currentActivity;
    }

    private Activity currentActivity = null;

    public static Application getInstance() {
        return singleton;
    }

    public ControlContent getControlContent() {
        return controlContent;
    }

    public ControlForum getControlForum() {
        return controlForum;
    }

    public ControlPage getControlPage() {
        return controlPage;
    }

    public ControlSection getControlSection() {
        return controlSection;
    }

    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "Application class created.");
        singleton = (Application) getApplicationContext();
        singleton.registerActivityLifecycleCallbacks(new ActivityManager());
    }

    ////////////////////////////////////////////////////////////////////////

    private class ActivityManager implements ActivityLifecycleCallbacks {
        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            //Log.i(TAG, "onActivityCreated: " + activity);
        }

        @Override
        public void onActivityStarted(Activity activity) {
            //Log.d(TAG, "onActivityStarted: " + activity);
        }

        @Override
        public void onActivityResumed(Activity activity) {
            Log.d(TAG, "currentActivity initialized: " + activity);
            currentActivity = activity;

        }

        @Override
        public void onActivityPaused(Activity activity) {
            //Log.d(TAG, "onActivityPaused: " + activity);
        }

        @Override
        public void onActivityStopped(Activity activity) {
            if (currentActivity == activity) {
                Log.d(TAG, "currentActivity stopped: " + activity);
                currentActivity = null;
            }

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            //Log.d(TAG, "onActivitySaveInstaceState: " + activity);
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            //Log.i(TAG, "onActivityDestroyed: " + activity);
        }
    }
}

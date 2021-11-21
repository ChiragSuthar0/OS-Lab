package com.example.demo;

// Java program to implement Shortest Job first with Arrival
// Time
import java.util.*;

class SJF {

    int[] Finish;
    int[] ProcessID;

    public String Main(int numberOfProcess, ArrayList<Integer> BT, ArrayList<Integer> AT)
    {
        int[] pid = new int[numberOfProcess];
        int[] at = new int[numberOfProcess]; // at means arrival time
        int[] bt = new int[numberOfProcess]; // bt means burst time
        int[] ct = new int[numberOfProcess]; // ct means complete time
        int[] ta = new int[numberOfProcess]; // ta means turn around time
        int[] wt = new int[numberOfProcess];  //wt means waiting time
        int[] f = new int[numberOfProcess];  // f means it is flag it checks process is completed or not
        int st=0, tot=0;
        float avgwt=0, avgta=0;

        for(int i=0;i<numberOfProcess;i++)
        {
            at[i] = AT.get(i);
            bt[i] = BT.get(i);
            pid[i] = i+1;
            f[i] = 0;
        }
        boolean a = true;
        while(true)
        {
            int c=numberOfProcess, min=999;
            if (tot == numberOfProcess) // total no of process = completed process loop will be terminated
                break;
            for (int i=0; i<numberOfProcess; i++)
            {
                /*
                 * If i'th process arrival time <= system time and its flag=0 and burst<min
                 * That process will be executed first
                 */
                if ((at[i] <= st) && (f[i] == 0) && (bt[i]<min))
                {
                    min=bt[i];
                    c=i;
                }
            }
            /* If c==numberOfProcess means c value can not updated because no process arrival time< system time so we increase the system time */
            if (c==numberOfProcess)
                st++;
            else
            {
                ct[c]=st+bt[c];
                st+=bt[c];
                ta[c]=ct[c]-at[c];
                wt[c]=ta[c]-bt[c];
                f[c]=1;
                tot++;
            }
        }

        Finish = ct;
        ProcessID = pid;

        StringBuilder r = new StringBuilder();

        r.append("\npid\t\tarrival\t\tburst\t\tcomplete\t\tturn\t\twaiting\n");

        for(int i=0;i<numberOfProcess;i++)
        {
            avgwt+= wt[i];
            avgta+= ta[i];
            r.append(pid[i]+"\t\t\t"+at[i]+"\t\t\t"+bt[i]+"\t\t\t"+ct[i]+"\t\t\t"+ta[i]+"\t\t\t"+wt[i]+"\n");
        }
        r.append(("\naverage tat is "+ (avgta/numberOfProcess)));
        r.append(("\naverage wt is "+ (avgwt/numberOfProcess)));

        return r.toString();

    }
}

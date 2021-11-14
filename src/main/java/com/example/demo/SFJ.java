package com.example.demo;

// Java program to implement Shortest Job first with Arrival
// Time
import java.util.*;

class SJF {
    public String Main(int numberOfProcess, ArrayList<Integer> BT, ArrayList<Integer> AT)
    {
        int n = numberOfProcess;
        int pid[] = new int[n];
        int at[] = new int[n]; // at means arrival time
        int bt[] = new int[n]; // bt means burst time
        int ct[] = new int[n]; // ct means complete time
        int ta[] = new int[n]; // ta means turn around time
        int wt[] = new int[n];  //wt means waiting time
        int f[] = new int[n];  // f means it is flag it checks process is completed or not
        int st=0, tot=0;
        float avgwt=0, avgta=0;

        for(int i=0;i<n;i++)
        {
            System.out.println ("enter process " + (i+1) + " arrival time:");
            at[i] = AT.get(i).intValue();
            System.out.println ("enter process " + (i+1) + " brust time:");
            bt[i] = BT.get(i).intValue();
            pid[i] = i+1;
            f[i] = 0;
        }
        boolean a = true;
        while(true)
        {
            int c=n, min=999;
            if (tot == n) // total no of process = completed process loop will be terminated
                break;
            for (int i=0; i<n; i++)
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
            /* If c==n means c value can not updated because no process arrival time< system time so we increase the system time */
            if (c==n)
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
//        System.out.println("\npid  arrival burst  complete turn waiting");
//        for(int i=0;i<n;i++)
//        {
//            avgwt+= wt[i];
//            avgta+= ta[i];
//            System.out.println(pid[i]+"\t"+at[i]+"\t"+bt[i]+"\t"+ct[i]+"\t"+ta[i]+"\t"+wt[i]);
//        }
//        System.out.println ("\naverage tat is "+ (float)(avgta/n));
//        System.out.println ("\naverage wt is "+ (float)(avgwt/n));

        StringBuilder r = new StringBuilder();

        r.append("\npid\t\tarrival\t\tburst\t\tcomplete\t\tturn\t\twaiting\n");

        for(int i=0;i<n;i++)
        {
            avgwt+= wt[i];
            avgta+= ta[i];
            r.append(pid[i]+"\t\t\t"+at[i]+"\t\t\t"+bt[i]+"\t\t\t"+ct[i]+"\t\t\t"+ta[i]+"\t\t\t"+wt[i]+"\n");
        }
        r.append(("\naverage tat is "+ (float)(avgta/n)));
        r.append(("\naverage wt is "+ (float)(avgwt/n)));

        return r.toString();

    }
}

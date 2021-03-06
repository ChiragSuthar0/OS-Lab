package com.example.demo;

import java.util.ArrayList;

public class NonPreemptivePriorityCPUSchedulingAlgorithm
{

    int[] burstTime;
    int[] priority;
    int[] arrivalTime;
    String[] processId;
    int numberOfProcess;

    void getProcessData(int numberOfProcess, ArrayList<Integer> BT, ArrayList<Integer> AT, ArrayList<Integer> p)
    {
//        System.out.print("Enter the number of Process for Scheduling           : ");
        this.numberOfProcess = numberOfProcess;
        burstTime = new int[numberOfProcess];
        priority = new int[numberOfProcess];
        arrivalTime = new int[numberOfProcess];
        processId = new String[numberOfProcess];
        String st = "P";
        for (int i = 0; i < numberOfProcess; i++)
        {
            processId[i] = st.concat(Integer.toString(i));
//            System.out.print("Enter the burst time   for Process - " + (i) + " : ");
            burstTime[i] = BT.get(i).intValue();
//            System.out.print("Enter the arrival time for Process - " + (i) + " : ");
            arrivalTime[i] = AT.get(i).intValue();
//            System.out.print("Enter the priority     for Process - " + (i) + " : ");
            priority[i] = p.get(i).intValue();
        }
    }

    void sortAccordingArrivalTimeAndPriority(int[] at, int[] bt, int[] prt, String[] pid)
    {

        int temp;
        String stemp;
        for (int i = 0; i < numberOfProcess; i++)
        {

            for (int j = 0; j < numberOfProcess - i - 1; j++)
            {
                if (at[j] > at[j + 1])
                {
                    //swapping arrival time
                    temp = at[j];
                    at[j] = at[j + 1];
                    at[j + 1] = temp;

                    //swapping burst time
                    temp = bt[j];
                    bt[j] = bt[j + 1];
                    bt[j + 1] = temp;

                    //swapping priority
                    temp = prt[j];
                    prt[j] = prt[j + 1];
                    prt[j + 1] = temp;

                    //swapping process identity
                    stemp = pid[j];
                    pid[j] = pid[j + 1];
                    pid[j + 1] = stemp;

                }
                //sorting according to priority when arrival timings are same
                if (at[j] == at[j + 1])
                {
                    if (prt[j] > prt[j + 1])
                    {
                        //swapping arrival time
                        temp = at[j];
                        at[j] = at[j + 1];
                        at[j + 1] = temp;

                        //swapping burst time
                        temp = bt[j];
                        bt[j] = bt[j + 1];
                        bt[j + 1] = temp;

                        //swapping priority
                        temp = prt[j];
                        prt[j] = prt[j + 1];
                        prt[j + 1] = temp;

                        //swapping process identity
                        stemp = pid[j];
                        pid[j] = pid[j + 1];
                        pid[j + 1] = stemp;

                    }
                }
            }

        }
    }


    int[] Finish;
    String[] ProcessID;

    String priorityNonPreemptiveAlgorithm()
    {
        int finishTime[] = new int[numberOfProcess];
        int bt[] = burstTime.clone();
        int at[] = arrivalTime.clone();
        int prt[] = priority.clone();
        String pid[] = processId.clone();
        int waitingTime[] = new int[numberOfProcess];
        int turnAroundTime[] = new int[numberOfProcess];

        sortAccordingArrivalTimeAndPriority(at, bt, prt, pid);

        //calculating waiting & turn-around time for each process
        finishTime[0] = at[0] + bt[0];
        turnAroundTime[0] = finishTime[0] - at[0];
        waitingTime[0] = turnAroundTime[0] - bt[0];

        for (int i = 1; i < numberOfProcess; i++)
        {
            finishTime[i] = bt[i] + finishTime[i - 1];
            turnAroundTime[i] = finishTime[i] - at[i];
            waitingTime[i] = turnAroundTime[i] - bt[i];
        }
        float sum = 0;
        for (int n : waitingTime)
        {
            sum += n;
        }
        float averageWaitingTime = sum / numberOfProcess;

        sum = 0;
        for (int n : turnAroundTime)
        {
            sum += n;
        }
        float averageTurnAroundTime = sum / numberOfProcess;

        Finish = finishTime;
        ProcessID = pid;

        //print on TextArea the order of processes along with their finish time & turn around time

        String r = "Priority Scheduling Algorithm : \n";
        r = r + String.format("%20s%20s%20s%20s%20s%20s%20s\n", "ProcessId", "BurstTime", "ArrivalTime", "Priority", "FinishTime", "WaitingTime", "TurnAroundTime");
        for (int i = 0; i < numberOfProcess; i++) {
            r = r + String.format("%25s%25d%25d%25d%25d%25d%25d\n", pid[i], bt[i], at[i], prt[i], finishTime[i], waitingTime[i], turnAroundTime[i]);
        }
        r = r + String.format("%100s%20f%20f\n", "Average", averageWaitingTime, averageTurnAroundTime);

        return r;

    }
}

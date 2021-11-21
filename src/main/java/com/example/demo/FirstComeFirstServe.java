package com.example.demo;

import java.util.ArrayList;
import java.util.Scanner;

public class FirstComeFirstServe
{
    int[] burstTime;
    int[] arrivalTime;
    String[] processId;
    int numberOfProcess;

    void getProcessData(int numberOfProcess, ArrayList<Integer> BT, ArrayList<Integer> AT)
    {
        this.numberOfProcess = numberOfProcess;
        burstTime = new int[numberOfProcess];
        arrivalTime = new int[numberOfProcess];
        processId = new String[numberOfProcess];
        String st = "P";

        for (int i = 0; i < numberOfProcess; i++)
        {
            processId[i] = st.concat(Integer.toString(i));
            burstTime[i] = BT.get(i);//input.nextInt();
            arrivalTime[i] = AT.get(i);
        }
    }

    void sortAccordingArrivalTime(int[] at, int[] bt, String[] pid)
    {
        boolean swapped;
        int temp;
        String stemp;
        for (int i = 0; i < numberOfProcess; i++)
        {
            swapped = false;
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

                    //swapping process id
                    stemp = pid[j];
                    pid[j] = pid[j + 1];
                    pid[j + 1] = stemp;

                    //enhanced bubble sort
                    swapped = true;
                }
            }
            if (!swapped)
            {
                break;
            }
        }
    }

    int[] Finish;
    String[] ProcessId;

    String firstComeFirstServeAlgorithm()
    {
        int[] finishTime = new int[numberOfProcess];
        int[] bt = burstTime.clone();
        int[] at = arrivalTime.clone();
        String[] pid = processId.clone();
        int[] waitingTime = new int[numberOfProcess];
        int[] turnAroundTime = new int[numberOfProcess];
        sortAccordingArrivalTime(at, bt, pid);

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
        ProcessId = pid;

        String r =  "FCFS Scheduling Algorithm : \n"
                + String.format("%20s%20s%20s%20s%20s%20s\n", "ProcessId", "BurstTime", "ArrivalTime", "FinishTime", "WaitingTime", "TurnAroundTime");

        for (int i = 0; i < numberOfProcess; i++)
        {
            r = r + String.format("%25s%25d%25d%25d%25d%25d\n", pid[i], bt[i], at[i], finishTime[i], waitingTime[i], turnAroundTime[i]);
        }

        r = r + String.format("%100s%24f%25f\n", "Average", averageWaitingTime, averageTurnAroundTime);

        return r;
    }
}

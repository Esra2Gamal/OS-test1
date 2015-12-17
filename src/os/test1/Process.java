import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class process implements Runnable {

    public String process_Name;
    public int arrival, duration, remaining, priority;
    static Scanner input = new Scanner(System.in);

    public process() {
    }

    ;
    public process(String processName) {
        this.process_Name = processName;

    }

    public process(String processName, int arrival) {
        this.process_Name = processName;
        this.arrival = arrival;
    }

    public process(String processName, int arrival, int duration) {
        this.process_Name = processName;
        this.arrival = arrival;
        this.duration = duration;
    }

    public void setName(String processName) {
        this.process_Name = processName;
    }

    public void setArriavl(int arrival) {
        this.arrival = arrival;

    }

    public void setDuration(int arrival) {
        this.duration = duration;

    }

    public String getName() {
        return process_Name;
    }

    public int getarrival() {
        return arrival;
    }

    public int getDuration() {
        return duration;
    }

    void initialize() {
        System.out.print("Enter Process Name: ");
        process_Name = input.next();
        System.out.print("Enter Arrival Time: ");
        arrival = input.nextInt();
        System.out.print("Enter Duration of the process: ");
        duration = input.nextInt();
        remaining = duration;

    }

    @Override
    public void run() {
        System.out.println("the process " + this.process_Name + " is now running");
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter the num of process: ");
        int n = Integer.parseInt(br.readLine());
        scheduler ss = new scheduler(n);
        int waiting[] = new int[n];
        int turnaround[] = new int[n];
        int response[] = new int[n];
        for (int i = 0; i < n; i++) {
            process p = new process();
            p.initialize();
            ss.processes[i] = p;
        }
        System.out.println("Please select your scheduling technique or Exit the scheduler : ");
        System.out.println("1. First In First Out [FIFO] ");
        System.out.println("2. Shortest Job First [SJF] ");
        System.out.println("3. Round-Robin [RR] ");
        System.out.println("0. ExIT ");
        int option;
        option = Integer.parseInt(br.readLine());

        while (option > 3) {
            System.out.println("Please Enter a valid option : ");
            option = Integer.parseInt(br.readLine());
        }
        if (option == 1) {
            int finish = 0;
            for (int i = 0; i < n; i++) {
                finish += ss.processes[i].duration;
                waiting[i] = finish - ss.processes[i].arrival - ss.processes[i].duration;
            }
            for (int i = 0; i < n; i++) {
                turnaround[i] = waiting[i] + ss.processes[i].duration;
            }

        } else if (option == 2) {
            int finish = 0, sum, time;
            for (int i = 0; i < n; i++) {
                waiting[i] = 0;
                turnaround[i] = 0;
            }

            for (int i = 0; i < n; i++) {
                time = ss.processes[i].arrival;
                if (n - i > 1) {

                    if (i == 0) {
                        while (time < ss.processes[i + 1].arrival) {
                            ss.processes[i].remaining--;
                            finish++;
                            time++;
                        }
                    } else {
                        while (time < ss.processes[i + 1].arrival) {
                            if (ss.processes[i].remaining < ss.processes[i - 1].remaining) {
                                ss.processes[i].remaining--;
                            } else {
                                ss.processes[i - 1].remaining--;
                            }
                            finish++;
                            time++;
                        }
                        if (ss.processes[i - 1].remaining == 0 && turnaround[i - 1] == 0) {
                            turnaround[i - 1] = finish - ss.processes[i - 1].arrival;
                        }

                    }

                    if (ss.processes[i].remaining == 0 && turnaround[i] == 0) {
                        turnaround[i] = finish - ss.processes[i].arrival;
                    }

                } else {
                    int minprocess, minindex = 0;
                    do {
                        minprocess = 1000000;
                        for (int j = 0; j < n; j++) {
                            if (ss.processes[j].remaining < minprocess && ss.processes[j].remaining != 0) {
                                minprocess = ss.processes[j].remaining;
                                minindex = j;
                            }
                        }
                        finish += ss.processes[minindex].remaining;
                        ss.processes[minindex].remaining = 0;
                        turnaround[minindex] = finish - ss.processes[minindex].arrival;
                        sum = 0;
                        for (int j = 0; j < n; j++) {
                            sum += ss.processes[j].remaining;
                        }
                    } while (sum != 0);
                }
            }
            for (int i = 0; i < n; i++) {
                waiting[i] = turnaround[i] - ss.processes[i].duration;
            }

        } else if (option == 3) {
            int quantum, sum;

            response[0] = 0;
            System.out.print("Enter the Quantum : ");
            quantum = Integer.parseInt(br.readLine());
            Queue<Integer> qs = new LinkedList<Integer>();
            for (int i = 0; i < n; i++) {
                waiting[i] = 0;
                turnaround[i] = 0;
            }
            int finish = 0, rep = 0;
            do {

                for (int i = 0; i < n; i++) {
                    if (rep == 0) {
                        if (ss.processes[i].remaining > quantum) {
                            ss.processes[i].remaining -= quantum;
                            finish += quantum;

                        } else {
                            finish += ss.processes[i].remaining;
                            ss.processes[i].remaining = 0;
                        }
                        response[i] = finish;
                        if (ss.processes[i].remaining > 0) {
                            qs.add(i);

                        }
                        if (n - i > 1) {
                            while (finish < ss.processes[i + 1].arrival) {
                                int ind = qs.poll();

                                if (ss.processes[ind].remaining > quantum) {
                                    ss.processes[ind].remaining -= quantum;
                                    finish += quantum;
                                } else {
                                    finish += ss.processes[ind].remaining;
                                    ss.processes[ind].remaining = 0;
                                }
                                if (ss.processes[ind].remaining != 0) {
                                    qs.add(ind);
                                }

                            }
                        }
                    } else {

                        while (!qs.isEmpty()) {
                            int ind = qs.poll();

                            if (ss.processes[ind].remaining > quantum) {
                                ss.processes[ind].remaining -= quantum;
                                finish += quantum;
                            } else {
                                finish += ss.processes[ind].remaining;
                                ss.processes[ind].remaining = 0;
                            }
                            if (ss.processes[ind].remaining > 0) {
                                qs.add(ind);
                            } else {
                                turnaround[ind] = finish - ss.processes[ind].arrival;
                            }
                        }
                    }

                    if (turnaround[i] == 0 && ss.processes[i].remaining == 0) {
                        turnaround[i] = finish - ss.processes[i].arrival;
                    }

                }
                sum = 0;
                for (int i = 0; i < n; i++) {
                    sum = sum + ss.processes[i].remaining;
                }
                rep++;
            } while (sum != 0);
            for (int i = 0; i < n; i++) {
                waiting[i] = turnaround[i] - ss.processes[i].duration;
            }

        } else {
            System.out.println("scheduler is Terminated.");
        }
        if (option == 1 || option == 2 || option == 3) {
            double AvgWait, AvgturnAround, AvgResponse;
            int sumwait = 0, sumturn = 0, sumResponse = 0;
            DecimalFormat twoDigits = new DecimalFormat("0.00");
            for (int j = 0; j < n; j++) {

                sumwait += waiting[j];
            }
            for (int j = 0; j < n; j++) {
                sumturn += turnaround[j];
            }
            if (option == 1) {
                sumResponse = sumwait;
            } else if (option == 2) {
                sumResponse = sumwait - waiting[0];
            } else {
                sumResponse += ss.processes[0].arrival - 0;
                for (int i = 1; i < n; i++) {
                    sumResponse += response[i - 1] - ss.processes[i].arrival;
                }
            }
            AvgWait = sumwait / (double) n;
            AvgturnAround = sumturn / (double) n;
            AvgResponse = sumResponse / (double) n;
            System.out.println("process Name | Arrival time | Duration | waiting time | turnaround time ");
            for (int i = 0; i < n; i++) {

                System.out.println("      " + ss.processes[i].process_Name + "           " + ss.processes[i].arrival + "             "
                        + ss.processes[i].duration + "            " + waiting[i] + "               " + turnaround[i]);
            }
            System.out.println("Average Waiting Time = " + twoDigits.format(AvgWait) + " msecs.");
            System.out.println("Average Turn around Time = " + twoDigits.format(AvgturnAround) + " msecs.");
            System.out.println("Average Response Time = " + twoDigits.format(AvgResponse) + " msecs.");
        }
    }
}

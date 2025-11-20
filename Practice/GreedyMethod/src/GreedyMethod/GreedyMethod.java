package GreedyMethod;

import java.util.*;

class GreedyJob{
	char id ;
	int profit;
	int deadline;
	
	GreedyJob(char id , int profit , int deadline){
		this.id = id ; 
		this.profit = profit;
		this.deadline = deadline;
	}

}

public class GreedyMethod {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// take user input for number of jobs
		System.out.println("Enter the number of job you want to insert: ");
		int n = sc.nextInt();
		
		
		ArrayList<GreedyJob> jobs = new ArrayList<>();
		
		
		
		// ask user to input the details of jobs 
		for(int i = 0; i < n ; i++) {
			System.out.println("Enter job id(char) : ");
			char id = sc.next().charAt(0);
			
			System.out.println("Enter job profit: ");
			int profit = sc.nextInt();
			
			System.out.println("Enter job deadline: ");
			int deadline = sc.nextInt();

			System.out.println("DEBUG → stored deadline = " + deadline);

			jobs.add(new GreedyJob(id, profit, deadline));

		}
		
		// sort the jobs according to profit 
		
		jobs.sort((j1, j2) -> j2.profit - j1.profit);
		
		
		// find max deadline to create slots and result array
		
		int maxdeadline = 0 ;
		for (GreedyJob j : jobs) {
			maxdeadline = Math.max(maxdeadline, j.deadline);
		}
		
		
		boolean[] slot = new boolean[maxdeadline];
		char[] result = new char[maxdeadline];
		
		for(GreedyJob job : jobs) {
			for(int i = job.deadline-1 ; i >=0 ; i++) {
				if(!slot[i]) {
					slot[i] = true;
					result[i] = job.id;
					break;
				}
			}
		}
		
	 
		System.out.println("The maximum profit sequence is ");
		for (char c : result) {
			if(c != '\0') {
				System.out.print(c +" ");
			}
		}
		sc.close();
	}

}
